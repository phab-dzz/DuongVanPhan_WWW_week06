package fit.iuh.vn.backend.services;

import fit.iuh.vn.backend.models.User;
import fit.iuh.vn.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Page<User> findAll(int pageNo, int sizeNo, String sortBy, String sortDerection){
        Sort sort = Sort.by(Sort.Direction.fromString(sortDerection), sortBy);
        Pageable pageable = PageRequest.of(pageNo, sizeNo, sort);
        return userRepository.findAll(pageable);
    }
}
