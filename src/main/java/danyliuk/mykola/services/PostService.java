package danyliuk.mykola.services;

import danyliuk.mykola.dto.PostRequestDTO;
import danyliuk.mykola.dto.PostResponseDTO;
import danyliuk.mykola.dto.PostSearchDTO;
import java.util.List;

/**
 * @author Mykola Danyliuk
 */
public interface PostService {
  List<PostResponseDTO> findNotApproved();
  List<PostResponseDTO> findApproved();
  List<PostResponseDTO> findApprovedWithFullTextSearch(PostSearchDTO request);
  List<PostResponseDTO> findAllByCurrentUser();
  void approvePost(Integer postID);
  void createPost(PostRequestDTO request);
  void editPost(PostRequestDTO request, Integer postID);
}
