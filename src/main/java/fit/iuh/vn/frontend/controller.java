package fit.iuh.vn.frontend;

import fit.iuh.vn.backend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/post")
public class controller {
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("posts", postRepository.findAll());


        return "post/listpost";
    }

}
