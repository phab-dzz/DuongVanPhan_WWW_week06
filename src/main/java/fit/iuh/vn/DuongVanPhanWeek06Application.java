package fit.iuh.vn;

import fit.iuh.vn.backend.models.Post;
import fit.iuh.vn.backend.models.PostComment;
import fit.iuh.vn.backend.models.User;
import fit.iuh.vn.backend.repositories.PostCommentRepository;
import fit.iuh.vn.backend.repositories.PostRepository;
import fit.iuh.vn.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class DuongVanPhanWeek06Application {

    public static void main(String[] args) {
        SpringApplication.run(DuongVanPhanWeek06Application.class, args);
    }
//    @Autowired
//    private PostRepository postRepository;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private PostCommentRepository postCommentRepository;
//    @Bean
//    CommandLineRunner initData(){
//        return args -> {
//            Random rnd =new Random();
//            for (int i = 2; i < 1000; i++) {
//                User user = new User("firstName#"+i,"middleName#"+i,"lastName#"+i,rnd.nextLong(1111111111L,9999999999L)+"",    "email_"+i+"@gmail.com", rnd.nextLong(11111111L,99999999L)+"", LocalDate.now(), LocalDate.now(), "intro#"+i, "profile#"+i);
//
//                Post post = new Post(user, "Title#"+i,"metaTitle#"+i, "summary#"+i, true, LocalDate.now(), LocalDate.now(), LocalDate.now(), "content#"+i);
//                PostComment postComment = new PostComment(post,"title#"+i, true, LocalDate.now(), LocalDate.now(),"content#"+i);
//                post.setParent(post);
//                postComment.setParent(postComment);
//                userRepository.save(user);
//                postRepository.save(post);
//                postCommentRepository.save(postComment);
//
//                System.out.println("Added: " +post);
//            }
//
//
//        };
//    }

}
