package Project.PetWalk.controller;

import Project.PetWalk.dto.PostDto;
import Project.PetWalk.service.PostService;
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

    @GetMapping("/post/write")
    public String postDto() {
        return "PostTest";
    }

    @PostMapping("/post/write/content")
    public String postTest(@ModelAttribute PostDto postDto, Model model) {
        postService.writePost(postDto);
        log.info("{}", postDto);
        model.addAttribute("dto", postDto);
        return "afterwrite";
    }

    @GetMapping("/post/write/content")
    public String getPostContent(@RequestParam("postIdx") Long postIdx, Model model) {
        PostDto post = postService.getPostById(postIdx);
        if (post == null) {
            throw new RuntimeException("Post not found with id: " + postIdx);
        }
        model.addAttribute("post", post);
        return "PostDtoPage";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        List<PostDto> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "Main";
    }
    @PostMapping("/post/update")
    public String updatePost(PostDto postDto) {
        postService.updatePost(postDto);
        return "redirect:/post/" + postDto.getIdx();
    }
}