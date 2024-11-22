package fit.iuh.vn.backend.services;

import fit.iuh.vn.backend.models.PostComment;
import fit.iuh.vn.backend.repositories.PostCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostCommentService {
    @Autowired
    private PostCommentRepository postCommentRepository;
    private PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer;

    public Optional<PostComment>  hiddendComment(Long commentID){
        PostComment comment = postCommentRepository.findById(commentID).orElse(null);
        if(comment == null){
            return Optional.empty();
        }
        comment.setPublished(false);
        postCommentRepository.save(comment);
        return Optional.of(comment);
    }
    public Page<PostComment> getAllPotComment(int PageNo, int size, String sortBy, String sortDirection,long postID)
    {
        Sort sort= Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable= PageRequest.of(PageNo,size,sort);
        return postCommentRepository.findPostCommentByPublishedIsTrueAndPostId(postID,pageable);

    }
}
