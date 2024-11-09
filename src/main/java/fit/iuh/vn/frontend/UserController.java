package fit.iuh.vn.frontend;

import fit.iuh.vn.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/login")
    public String login(String mobile, String password){
        if(userRepository.findByMobileAndPasswordHash(mobile,password)){
            return "redirect:/post/list";
        }
        return "/loginAccount";
    }
}
