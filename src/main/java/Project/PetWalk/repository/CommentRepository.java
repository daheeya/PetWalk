package Project.PetWalk.repository;

import Project.PetWalk.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    String query = "select c from CommentEntity c where c.postEntity.idx = :idx";
    @Query(value = query)
    List<CommentEntity> getAllCommentByPostIdx(@Param(value = "idx") Long idx);
}
