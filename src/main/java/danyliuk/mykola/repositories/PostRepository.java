package danyliuk.mykola.repositories;

import danyliuk.mykola.domain.Post;
import danyliuk.mykola.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Mykola Danyliuk
 */
public interface PostRepository extends JpaRepository<Post, Integer> {
  List<Post> findByApprovedIsTrue();
  List<Post> findByApprovedIsFalse();
  List<Post> findByPoster(User poster);
  @Query("select P from Post P where upper(concat(P.title, P.body)) like CONCAT('%',?1,'%')")
  List<Post> findBySearchText(String searchText);

}
