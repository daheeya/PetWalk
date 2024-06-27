package Project.PetWalk.controller;

import Project.PetWalk.dto.CommentDto;
import Project.PetWalk.dto.PostDto;
import Project.PetWalk.dto.UserDto;
import Project.PetWalk.service.PostService;
import Project.PetWalk.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
@Slf4j
public class PageController {
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/post/write")
    public String postDto() {
        return "PostTest";
    }

    @PostMapping("/post/write/content")
    public String afterWrite(@ModelAttribute PostDto postDto, Model model) {
        postService.writePost(postDto);
        log.info("{}", postDto);
        model.addAttribute("afterwrite_dto", postDto);
        return "afterwrite";
    }

    @GetMapping("/post/write/content")
    public String getPostContent(@RequestParam("postIdx") Long postIdx, Model model) {
        PostDto post = postService.getPostById(postIdx);
        model.addAttribute("post", post);
        return "PostDtoPage";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "Main";
    }
    @GetMapping("/post/update")
    public String updatePostGet(PostDto postDto, Model model) {
        //postService.updatePost(postDto);
        model.addAttribute("postDto", postDto);
        return "rewrite";
    }

    @PostMapping("/post/update")
    public String updatePost(PostDto postDto, Model model) {
        postService.updatePost(postDto);
        model.addAttribute("post", postDto);
        return "PostDtoPage";
    }

    @PostMapping("/comments")
    public String writeComment(@ModelAttribute CommentDto commentDto, Model model) {
        postService.writeComment(commentDto);
        model.addAttribute("comment", commentDto);
        return "redirect:/page/post/write/content?postIdx=" + commentDto.getPostIdx();
    }

    @GetMapping("/user/")
    public String getUserById(@RequestParam("userIdx") Long userIdx, Model model) {
        UserDto userDto = userService.getUserById(userIdx);
            model.addAttribute("user", userDto);
            return "myPage";
    }
}
