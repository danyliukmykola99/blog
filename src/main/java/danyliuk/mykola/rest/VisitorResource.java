package danyliuk.mykola.rest;

import danyliuk.mykola.dto.PostSearchDTO;
import danyliuk.mykola.services.PostService;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mykola Danyliuk
 */
@RestController
@RequestMapping("/api/v1/visitor")
@AllArgsConstructor
public class VisitorResource {

  private PostService postService;

  @GetMapping(path = "/posts")
  public ResponseEntity <?> getApproved(){
    return ResponseEntity.ok(postService.findApproved());
  }

  @PostMapping(path = "/posts")
  public ResponseEntity <?> searchApprovedPostsWithFilters(
      @Valid @RequestBody @Schema PostSearchDTO request
  ){
    return ResponseEntity.ok(postService.findApprovedWithFullTextSearch(request));
  }

}
