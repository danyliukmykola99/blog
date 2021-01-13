package danyliuk.mykola.repositories;

import danyliuk.mykola.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mykola Danyliuk
 */
public interface PostRepository extends JpaRepository<Post, Integer> {

}
