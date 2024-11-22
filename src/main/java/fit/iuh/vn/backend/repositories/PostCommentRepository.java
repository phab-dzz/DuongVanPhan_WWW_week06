package fit.iuh.vn.backend.repositories;

import fit.iuh.vn.backend.models.PostComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long>{
    public Page<PostComment> findPostCommentByPublishedIsTrueAndPostId(long postId, Pageable pageable);
}
