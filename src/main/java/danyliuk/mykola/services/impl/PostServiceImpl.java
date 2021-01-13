package danyliuk.mykola.services.impl;

import danyliuk.mykola.domain.Post;
import danyliuk.mykola.domain.User;
import danyliuk.mykola.dto.PostRequestDTO;
import danyliuk.mykola.dto.PostResponseDTO;
import danyliuk.mykola.dto.PostSearchDTO;
import danyliuk.mykola.exceptions.NotFoundException;
import danyliuk.mykola.repositories.PostRepository;
import danyliuk.mykola.services.PostService;
import danyliuk.mykola.services.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Mykola Danyliuk
 */
@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

  private final PostRepository postRepository;
  private final UserService userService;

  @Override
  public List<PostResponseDTO> findNotApproved() {
    return postRepository.findByApprovedIsFalse().stream().map(Post::toResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List <PostResponseDTO> findApproved() {
    return postRepository.findByApprovedIsTrue().stream().map(Post::toResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List <PostResponseDTO> findApprovedWithFullTextSearch(PostSearchDTO request) {
    String searchText = request.getSearchText();
    return postRepository.findBySearchText(searchText).stream().map(Post::toResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public List<PostResponseDTO> findAllByCurrentUser() {
    User current = userService.getCurrentUser();
    return postRepository.findByPoster(current).stream().map(Post::toResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public void approvePost(Integer postID) {
    Post post = postRepository.findById(postID).orElseThrow(() -> new NotFoundException("Post is not found"));
    post.setApproved(true);
    postRepository.save(post);
  }

  @Override
  public void createPost(PostRequestDTO request) {
    User current = userService.getCurrentUser();
    Post post = Post.builder()
        .poster(current)
        .title(request.getTitle())
        .body(request.getBody())
        .approved(false)
        .build();
    postRepository.save(post);
  }

  @Override
  public void editPost(PostRequestDTO request, Integer postID) {
    Post post = postRepository.findById(postID).orElseThrow(() -> new NotFoundException("Post is not found"));
    post.setTitle(request.getTitle());
    post.setBody(request.getBody());
    postRepository.save(post);
  }
}
