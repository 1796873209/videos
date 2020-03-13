package com.dou.videos.controller;

import com.dou.videos.entity.Comments;
import com.dou.videos.entity.Likes;
import com.dou.videos.entity.User;
import com.dou.videos.entity.Videos;
import com.dou.videos.service.CommentsService;
import com.dou.videos.service.LikesService;
import com.dou.videos.service.UserService;
import com.dou.videos.service.VideosService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * controller层
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    VideosService videosService;

    @Autowired
    LikesService likesService;

    @Autowired
    CommentsService commentsService;


    //跳转到登录页面
    @RequestMapping("/dologin")
    public String doLogin(){
        return "login";
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public boolean register(User user){
        int t=userService.insert(user);
        if (t==1){     //对应前端的回调函数
            return true;
        }
        return false;
    }

    //登录
     @RequestMapping("/index")
    public String login(User user, Model model, HttpServletRequest request){
        //用户名和密码不为空
         if(user.getUname()!=null && user.getPassword()!=null){
             //获取要登陆的用户信息
             User user1=userService.login(user.getUname(),user.getPassword());
             if (user1!=null){    //用户已注册

                 //将登陆信息放到session中
                 HttpSession session=request.getSession();
                 session.setAttribute("uname",user1.getUname());
                 session.setAttribute("password",user1.getPassword());

                 //向前端传递数据
                 model.addAttribute("user",user1);

                 return "index";
             }else {
                 return "fail";
             }
         }
         return "fail";
     }


     @RequestMapping("/")
     public String index(Model model, HttpServletRequest request){
         //获取session中的用户名和密码
         HttpSession session=request.getSession();
         String name=(String) session.getAttribute("uname");
         String password=(String)session.getAttribute("password");

         //根据用户名和密码获取用户信息
         User user=userService.login(name,password);

         //向前端传递数据
         model.addAttribute("user",user);
        return "upload";
     }


     //上传
     @RequestMapping("/upload")
     @ResponseBody
     public boolean upload(MultipartFile file,HttpServletRequest request){
         //获取session中的用户名和密码
         HttpSession session=request.getSession();
         String name=(String) session.getAttribute("uname");
         String password=(String)session.getAttribute("password");
         User user=userService.login(name,password);
         Videos videos=new Videos();


         //获取文件名
         String fileName=file.getOriginalFilename();

         //指定路径
         String filePath="E:\\idea-workplace\\springboot\\videos\\src\\main\\resources\\static\\upload\\";

         //重组文件名
         File file1=new File(filePath+fileName);
         try{
             //保存
             file.transferTo(file1);

             //将数据传到数据库
             String subject=request.getParameter("subject");
             videos.setUname(name);
             videos.setSubject(subject);
             videos.setVname(fileName);
             videos.setSubmitTime(new Date());
             videos.setUid(String.valueOf(user.getUid()));

             int t=videosService.insert(videos);
             if (t==1){
                 return true;
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
         return false;
     }


     //展示
    @RequestMapping("/videoList")
    public String getAll(Model model,HttpServletRequest request){
        //获取列表
        List<Videos> videosList=videosService.getAll();
        List<Comments> commentsList=commentsService.getAll();

        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("uname");
        String password=(String)session.getAttribute("password");
        User user=userService.login(name,password);

        //传递到前端
        model.addAttribute("videoList",videosList);
        model.addAttribute("comments",commentsList);
        model.addAttribute("user",user);

        return "videoList";
    }


    //删除
    @RequestMapping("/delete/{vid}")
    public String delete(@PathVariable String vid,Model model){
        int t=videosService.delete(vid);
        if (t==1){
            //更新页面
            List<Videos> videosList=videosService.getAll();
            model.addAttribute("videoList",videosList);

            return "videoList";
        }else {
            return "fail";
        }
    }


    //跳转到搜索页面
    @RequestMapping("/dofind")
    public String get(){
        return "find";
    }

    //搜索
    @RequestMapping("/find")
    public String find(HttpServletRequest request,Model model){
        //获取前端文本的值
        String search=request.getParameter("search");

        //获取信息
        List<Videos> videosList=videosService.getBy(search);
        if (videosList!=null){   //输入的值和数据库匹配
            //传到前端
            model.addAttribute("videoList",videosList);
            return "find1";
        }else {
            return "fail";
        }
    }


    //点赞
    @RequestMapping("/like")
    @ResponseBody
    public String[] praise(Likes likes) throws Exception{
        //定义数组
         String a[]=new String[2];
         Likes likes1=likesService.find(likes);
        String[] result=new String[2];
        if (likes1==null){   //没有点赞记录
            //在数据库插入记录
            likesService.addLike(likes);

            //根据视频id获取视频信息
            Videos videos=videosService.findById(likes.getVid());

            //点赞数加1
            if (videos.getPraise()==0) {
                videos.setPraise(videos.getPraise() + 1);
                videosService.update(videos);

                a[0] = String.valueOf(videos.getPraise()) + "";
                a[1] = videos.getVid() + "";
                result = a;
            }
            return result;
         }else {   //已经点赞
            //从数据库删除一条记录
            likesService.deleteLike(likes.getVid());
            //根据视频id获取视频信息
            Videos videos=videosService.findById(likes.getVid());
            //点赞减1
            if(videos.getPraise()>0) {
                videos.setPraise(videos.getPraise() - 1);
                videosService.update(videos);

                a[0] = videos.getPraise() + "";
                a[1] = videos.getVid() + "";
                result = a;
            }
             return result;
         }

    }


    //评论
    @RequestMapping("/comment/{vid}")
    public String addComment(@PathVariable Integer vid, Comments comments,HttpServletRequest request,Model model){
        //登录用户进行评论操作
        HttpSession session=request.getSession();
        String name=(String) session.getAttribute("uname");
        String password=(String)session.getAttribute("password");
        User user=userService.login(name,password);
        model.addAttribute("user",user);

        //设值
        comments.setUname(user.getUname());
        comments.setUid(user.getUid());
        comments.setVid(vid);
        commentsService.insertComment(comments);

        //从前端获值
        String content=request.getParameter("content");
        Videos videos=videosService.findById(String.valueOf(vid));
        videos.setComment(content);
        videosService.update1(videos);

        return "success";
    }
}
