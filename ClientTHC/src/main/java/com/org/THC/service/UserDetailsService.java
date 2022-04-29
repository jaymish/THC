package com.org.THC.service;

import com.org.THC.THCApplication;
import com.org.THC.model.User;
import com.org.THC.model.Users;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${url.to.serverthc}")
    private String serverURL;
    private static final Logger logger = LogManager.getLogger(THCApplication.class);

    String url="home/";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=restTemplate.postForObject(serverURL+url+"login", username, Users.class);
        User user = new User();
        user.setId(users.getId());
        user.setUsername(users.getUsername());
        user.setPassword(users.getPassword());
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return user;

    }

    public String saveUser(String name,String password){
        logger.info("Service:User "+name+" trying to save username and password using microservices");
        Users user=new Users();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode(password);
        user.setUsername(name);
        user.setPassword(pwd);
        String result=restTemplate.postForObject(serverURL+url+"saveUser", user, String.class);
        //repository.save(user);
        return result;
    }
}
