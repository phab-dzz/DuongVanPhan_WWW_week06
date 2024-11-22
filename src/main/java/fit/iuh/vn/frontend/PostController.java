package fit.iuh.vn.frontend;

import fit.iuh.vn.backend.models.Post;
import fit.iuh.vn.backend.models.PostComment;
import fit.iuh.vn.backend.models.User;
import fit.iuh.vn.backend.repositories.PostRepository;
import fit.iuh.vn.backend.services.PostCommentService;
import fit.iuh.vn.backend.services.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
   private PostService postService;
    @Autowired
    private PostCommentService postCommentService;

    @GetMapping("list")
    public String listPost(Model model, @RequestParam("page")Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size) {
        int pageNo = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Post> posts = postService.findAll(pageNo-1,pageSize,"publishedAt","desc");
        model.addAttribute("posts", posts);
        int totalPages = posts.getTotalPages();
       if(totalPages>0){
           List<Integer> postList = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());;
           model.addAttribute("PageNumbers", postList);
       }
       return "post/listpost";

    }
    @GetMapping("/{id}")
    public ModelAndView openPostDetail(@PathVariable("id") long postID
            , @RequestParam("cmtPage") Optional<Integer> pageNo
            , @RequestParam("cmtSize") Optional<Integer> pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        Post post = postRepository.findById(postID).orElse(null);
        if (post == null || !post.getPublished()) {
            return new ModelAndView("redirect:/");
        }

        int cmtPageNo = pageNo.orElse(1);
        int cmtPageSize = pageSize.orElse(10);

        Page<PostComment> postCommentPage = postCommentService.getAllPotComment(cmtPageNo - 1, cmtPageSize
                , "publishedAt", "desc", postID);

        modelAndView.addObject("post", post);
        modelAndView.addObject("postCommentPage", postCommentPage);

        int totalPagesCmt = postCommentPage.getTotalPages();
        if (totalPagesCmt > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPagesCmt).boxed().collect(Collectors.toList());
            ;
            modelAndView.addObject("pageCmtNumbers", pageNumbers);
        }
        modelAndView.setViewName("post/PostDetail");
        return modelAndView;
    }

    @GetMapping("/open_add")
    public ModelAndView showAddPostPage(){
        ModelAndView modelAndView = new ModelAndView("post/AddPost");
        Post post = new Post();
        modelAndView.addObject("post", post);

        return modelAndView;
    }
    @PostMapping("/add")
    public String handleAddNewPost(@ModelAttribute("post") Post post
            , @RequestParam("parent") String parent
            , HttpServletRequest request, Model model){

        long parentId = -1;

        if (!parent.trim().isEmpty()){
            try {
                parentId = Long.parseLong(parent);
            } catch (Exception e){
                model.addAttribute("mess", "Phải là số nguyên dương!");
                return "post/AddPost";
            }

            Post objParentPost = postRepository.findById(parentId).orElse(null);

            if (objParentPost == null){
                model.addAttribute("mess", "Không tồn tại Parent ID này!");
                return "post/AddPost";
            }
            post.setParent(objParentPost);
        }

        post.setPublished(true);
        post.setPublishedAt(LocalDate.now());
        post.setCreatedAt(LocalDate.now());
        post.setAuthor((User) request.getSession().getAttribute("account"));

        postRepository.save(post);
        return "redirect:/post/list";
    }

}
