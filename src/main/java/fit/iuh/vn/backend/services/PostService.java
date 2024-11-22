package fit.iuh.vn.backend.services;

import fit.iuh.vn.backend.models.Post;
import fit.iuh.vn.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public Page<Post> findAll(int pageNo, int pageSize, String sortBy, String sortDirection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return postRepository.findAllByPublishedIsTrue(pageable);
    }
    public List<Post> findAllBelongToMe(long userID){
        return postRepository.findAllByAuthor_IdAndPublishedIsTrue(userID);
    }
    public Optional<Post> hiddenPost(long postID){
        Post post = postRepository.findById(postID).orElse(null);
        if (post == null){
            return Optional.empty();
        }
        post.setPublished(false);
        postRepository.save(post);
        return Optional.of(post);
    }
}
