package danyliuk.mykola.services.impl;

import danyliuk.mykola.dto.PostRequestDTO;
import danyliuk.mykola.dto.PostResponseDTO;
import danyliuk.mykola.dto.PostSearchDTO;
import danyliuk.mykola.services.PostService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Mykola Danyliuk
 */
@Service
public class PostServiceImpl implements PostService {

  @Override
  public List <PostResponseDTO> findNotApproved() {
    return null;
  }

  @Override
  public List <PostResponseDTO> findApproved() {
    return null;
  }

  @Override
  public List <PostResponseDTO> findApprovedWithFullTextSearch(PostSearchDTO request) {
    return null;
  }

  @Override
  public List <PostResponseDTO> findAllByCurrentUser() {
    return null;
  }

  @Override
  public void approvePost(Integer postID) {

  }

  @Override
  public void createPost(PostRequestDTO request) {

  }

  @Override
  public void editPost(PostRequestDTO request, Integer postID) {

  }
}
