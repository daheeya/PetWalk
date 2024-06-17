package Project.PetWalk.controller;

import Project.PetWalk.dto.PostDto;
import Project.PetWalk.dto.PostLikeDto;
//import com.example.butler.service.LikeService;
import Project.PetWalk.service.PostService;
//import com.example.butler.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    //private final LikeService postLikeService;
    //private final UserService userService;
    private final PostService postService;

    @PostMapping("/post/write") //게시물 작성
    public PostDto writePost(@RequestBody PostDto dto) {
        return postService.writePost(dto);
    }



    @GetMapping("/post/like/add") //게시물 좋아요
    public PostDto addLike(@RequestBody PostLikeDto dto) {
        log.info(dto.toString());
        return postService.addPostLike(dto);
    }



//    @GetMapping("/user/{nick}/profile/img") //유저 프로필 이미지 조회
//    public List<String> findUserProfileImg(@PathVariable(name = "nick" ) String nick) {
//        return userService.findUserProfileImg(nick);
//    }

}
