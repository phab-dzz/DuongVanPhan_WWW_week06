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
import org.mindrot.jbcrypt.BCrypt;
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
//
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 100; i++) {
//                // Tạo user với passwordHash
//                String rawPassword = "password"+i;
//                String salt = BCrypt.gensalt(12);
//                String passwordHash = BCrypt.hashpw(rawPassword, salt);
//
//                User user = new User(
//                        "firstName#" + i,
//                        "middleName#" + i,
//                        "lastName#" + i,
//                        rnd.nextLong(1111111111L, 9999999999L) + "",
//                        "email_" + i + "@gmail.com",
//                        passwordHash,
//                        LocalDate.now(),
//                        LocalDate.now(),
//                        "intro#" + i,
//                        "profile#" + i
//                );
//
//                // Tạo post
//                Post post = new Post(
//                        user,
//                        "Title#" + i,
//                        "metaTitle#" + i,
//                        "summary#" + i,
//                        true,
//                        LocalDate.now(),
//                        LocalDate.now(),
//                        LocalDate.now(),
//                        "content#" + i
//                );
//
//                // Tạo comment cho post
//                PostComment postComment = new PostComment(
//                        post,
//                        "title#" + i,
//                        true,
//                        LocalDate.now(),
//                        LocalDate.now(),
//                        "content#" + i
//                );
//                postComment.setUser(user);
//                // Lưu vào repository
//                userRepository.save(user);
//                postRepository.save(post);
//                postCommentRepository.save(postComment);
//
//                System.out.println("Added User: " + user.getEmail() + " with Post: " + post.getTitle());
//            }
//        };
//    }


}
