package fit.iuh.vn.frontend;

import fit.iuh.vn.backend.models.User;
import fit.iuh.vn.backend.repositories.UserRepository;
import fit.iuh.vn.backend.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
   private UserService userService;
    @GetMapping("/open_signin")
    public String open_signin() {
        return "signin";
    }
    @PostMapping("/sign_in")
    public ModelAndView Signin(@RequestParam("email") String email, @RequestParam("password") String password, HttpServletRequest request) {

       ModelAndView mav=new ModelAndView("redirect:/");
        if(email.trim().isEmpty() || password.trim().isEmpty()) {
            mav.addObject("mess","khong duoc de trong!");
            mav.setViewName("user/sign-in");
        }
        User user=userRepository.findUserByEmail(email);
        if(user==null) {
            mav.addObject("mess","Tai khoan khong ton tai!");
            mav.setViewName("user/sign-in");
        }
        boolean Ischeck= BCrypt.checkpw(password,user.getPasswordHash());
        if(!Ischeck) {
            mav.addObject("mess","Mat khau khong dung!");
            mav.setViewName("user/sign-in");
        }
        HttpSession session=request.getSession();
        session.setAttribute("account",user);
        return mav;


    }
    @GetMapping("/signout")
    public String signout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
