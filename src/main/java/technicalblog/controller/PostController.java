package technicalblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import technicalblog.model.Post;
import technicalblog.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("posts")
    public  String getUserPosts(Model model){

       List<Post> posts = postService.getAllPosts();

       model.addAttribute("posts",posts);

        return "posts";
    }

    @RequestMapping("/posts/newpost")
    public String newPost(){
        return "posts/create";
    }

    @RequestMapping(value = "/posts/create",method = RequestMethod.POST)
    public String createPost(Post newPost){
        postService.createPost(newPost);
        return "redirect:/posts";
    }

    @RequestMapping(value="/editPost",method = RequestMethod.GET)
    public String editPost(@RequestParam(name="postID") Integer postID,Model model){
        Post post = postService.getPost(postID);
        model.addAttribute("post",post);
        return "redirect:/edit";
    }

    @RequestMapping(value = "/editPost",method = RequestMethod.PUT)
    public String editPostSubmit(@RequestParam(name="postID") Integer postID,Post updatedPost){
        post.setId(postID);
        postService.updatePost(updatedPost);
        return "redirect:/posts";
    }

    @RequestMapping(value = "/deletePost",method = RequestMethod.DELETE)
    public String deletePostSubmit(@RequestParam(name="postID") Integer postId){
        postService.deletePost(postId);
        return "redirect:/posts";
    }

}
