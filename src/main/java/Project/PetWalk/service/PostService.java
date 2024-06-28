package Project.PetWalk.service;

import Project.PetWalk.dto.CommentDto;
import Project.PetWalk.dto.CommentLiKeDto;
import Project.PetWalk.dto.PostDto;
import Project.PetWalk.dto.PostLikeDto;
import Project.PetWalk.entity.*;
import Project.PetWalk.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public PostDto writePost(PostDto dto) {  // 게시글 작성 완성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            var post = makeNewPostEntity(userEntity, dto);
            dto.getPostImgList().forEach(path -> {
                var img = postDtoToImgEntity(post, path);
                post.addImg(img);
            });
            dto.getTagList().forEach(keyword -> {
                var tag = makeNewTagEntity(post, keyword);
                post.addTag(tag);
            });
            postRepository.save(post);
        });
        return dto;
    }

    // postImgEntity 생성
    private PostImgEntity postDtoToImgEntity(PostEntity postEntity, String path){
        return PostImgEntity.builder().postEntity(postEntity).path(path).build();
    }

    // PostEntity 생성
    private PostEntity makeNewPostEntity(UserEntity user, PostDto dto){
        return PostEntity.builder().userEntity(user).title(dto.getTitle()).content(dto.getContent()).build();
    }

    //tagEntity 생성
    private TagEntity makeNewTagEntity(PostEntity post,String keyword){
        return TagEntity.builder().postEntity(post).keyword(keyword).build();
    }
    public List<CommentEntity> findCommentsByPostId(Long postIdx) {
        return commentRepository.getAllCommentByPostIdx(postIdx);
    }

    @Transactional
    public CommentDto writeComment(CommentDto dto) { // 댓글 완성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            postRepository.findById(dto.getPostIdx()).ifPresent(postEntity ->{
                var comment = makeNewCommentEntity(userEntity, postEntity, dto);
                commentRepository.save(comment);
            });
        });
        return dto;
    }

    private CommentEntity makeNewCommentEntity(UserEntity user, PostEntity post, CommentDto dto){
        return CommentEntity.builder().userEntity(user).postEntity(post).content(dto.getContent()).build();
    }

    @Transactional
    public PostDto addPostLike(PostLikeDto dto) { // 게시글 좋아요 작성
        userRepository.findById(dto.getUserIdx()).ifPresent(userEntity ->{
            postRepository.findById(dto.getPostIdx()).ifPresent(postEntity ->{
                postLikeRepository.save(makeNewPostLikeEntity(userEntity, postEntity));
            });
        });
        return changePostDto(postRepository.findById(dto.getPostIdx()).get());
    }

    private PostDto changePostDto(PostEntity postEntity){
        return PostDto.builder().likeCount(postEntity.getPostLikeEntities().size())
                .content(postEntity.getContent()).title(postEntity.getTitle())
                .userIdx(postEntity.getUserEntity().getIdx()).build();
    }

    private PostLikeEntity makeNewPostLikeEntity(UserEntity user, PostEntity post){
        return PostLikeEntity.builder().userEntity(user).postEntity(post).build();
    }

    @Transactional
    public CommentDto addCommentLike(CommentLiKeDto commentLiKeDto) { // 댓글 좋아요 작성 완료
        userRepository.findById(commentLiKeDto.getUserIdx()).ifPresent(userEntity ->{
            commentRepository.findById(commentLiKeDto.getCommentIdx()).ifPresent(commentEntity ->{
                commentLikeRepository.save(makeNewCommentLikeEntity(userEntity, commentEntity));
            });
        });
        return changeCommentDto(commentRepository.findById(commentLiKeDto.getCommentIdx()).get());
    }

    private CommentLikeEntity makeNewCommentLikeEntity(UserEntity user, CommentEntity comment){
        return CommentLikeEntity.builder().userEntity(user).commentEntity(comment).build();
    }

    private CommentDto changeCommentDto(CommentEntity commentEntity){
        return CommentDto.builder().likeCount(commentEntity.getCommentLikeEntities().size())
                .content(commentEntity.getContent()).build();
    }

    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities = postRepository.findAll();
        return postEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getAllComments() {
        List<CommentEntity> commentEntities = commentRepository.findAll();
        return commentEntities.stream().map(this::convertToCommentDto).collect(Collectors.toList());
    }

    @Transactional
    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        return convertToDto(postEntity);
    }

    private PostDto convertToDto(PostEntity postEntity) {
        return PostDto.builder().idx(postEntity.getIdx())
                        .title(postEntity.getTitle())
                        .userIdx(postEntity.getUserEntity().getIdx())
                        .content(postEntity.getContent())
                        .createAt(String.valueOf(postEntity.getCreateAt()))
                        .build();
    }

    private CommentDto convertToCommentDto(CommentEntity commentEntity){
        return CommentDto.builder().idx(commentEntity.getIdx())
                .postIdx(commentEntity.getPostEntity().getIdx())
                .userIdx(commentEntity.getUserEntity().getIdx())
                .content(commentEntity.getContent())
                .build();
    }
    public void updatePost(PostDto postDto) {
        Optional<PostEntity> postEntityOptional = postRepository.findById(postDto.getIdx());
        if (postEntityOptional.isPresent()) {
            PostEntity postEntity = postEntityOptional.get();
            postEntity.setTitle(postDto.getTitle());
            postEntity.setContent(postDto.getContent());
            postRepository.save(postEntity);
        }
    }

//    public CommentDto addComment(Long postIdx, Long userIdx, String content) {
//        Optional<PostEntity> postEntity = postRepository.findById(postIdx);
//        if (postEntity.isPresent()) {
//            CommentEntity commentEntity = CommentEntity.builder()
//                    .idx(postEntity.get().getIdx())
//                    .idx(userIdx)
//                    .content(content)
//                    .createdAt(LocalDateTime.now())
//                    .build();
//            commentEntity = commentRepository.save(commentEntity);
//
//            return CommentDto.builder()
//                    .idx(commentEntity.getIdx())
//                    .idx(postEntity.get().getIdx())
//                    .idx(userIdx)
//                    .content(content)
//                    .createdAt(String.valueOf(commentEntity.getCreatedAt()))
//                    .build();
//        } else {
//            throw new RuntimeException("Post not found");
//        }
//    }
}