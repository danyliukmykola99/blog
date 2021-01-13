package danyliuk.mykola.rest;

import danyliuk.mykola.dto.PostRequestDTO;
import danyliuk.mykola.services.PostService;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mykola Danyliuk
 */
@RestController
@RequestMapping("/api/v1/poster")
@PreAuthorize("hasRole('ROLE_POSTER')")
@AllArgsConstructor
public class PosterResource {

  private PostService postService;

  @GetMapping(path = "/posts")
  public ResponseEntity <?> getPostsByCurrentUser(){
    return ResponseEntity.ok(postService.findAllByCurrentUser());
  }

  @PostMapping(path = "/posts")
  public ResponseEntity <?> createPost(
      @Valid @RequestBody @Schema PostRequestDTO request
  ){
    postService.createPost(request);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PatchMapping(path = "/posts/{postID}")
  public ResponseEntity <?> editPost(
      @PathVariable Integer postID,
      @Valid @RequestBody @Schema PostRequestDTO request
  ){
    postService.editPost(request, postID);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
