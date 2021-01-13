package danyliuk.mykola.rest;

import danyliuk.mykola.services.PostService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mykola Danyliuk
 */
@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminResource {

  private PostService postService;

  @GetMapping(path = "/posts")
  public ResponseEntity <?> getNotApprovedPosts(){
    return ResponseEntity.ok(postService.findNotApproved());
  }

  @PostMapping(path = "/posts/{postID}/approve")
  public ResponseEntity <?> approvePost(
      @PathVariable Integer postID
  ){
    postService.approvePost(postID);
    return new ResponseEntity<>(HttpStatus.OK);
  }


}
