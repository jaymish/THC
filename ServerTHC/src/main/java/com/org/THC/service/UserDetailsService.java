package com.org.THC.service;

import com.org.THC.THCApplication;
import com.org.THC.model.User;
import com.org.THC.model.Users;
import com.org.THC.repo.UserRepository;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    @Autowired
    private UserRepository repository;
    //private static final Logger logger = LogManager.getLogger(THCApplication.class);

    public Users loadUserByUsername(String username){
        User user=repository.findByUsername(username);
        Users users=new Users();
        users.setUsername(user.getUsername());
        users.setPassword(user.getPassword());
        users.setId(user.getId());
        return users;
    }

    public String saveUser(Users user){
//        if (repository.existsByUsername(saveUser.getUsername())){
//            return "Username Already exist";
//        }
        User saveUser=new User();
        saveUser.setUsername(user.getUsername());
        saveUser.setPassword(user.getPassword());
        //logger.info("Service:User "+user.getUsername()+" trying to save");
        repository.save(saveUser);
        return "User Successfully added";
    }
}
