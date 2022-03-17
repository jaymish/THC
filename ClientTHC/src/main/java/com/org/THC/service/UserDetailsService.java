package com.org.THC.service;

import com.org.THC.model.User;
import com.org.THC.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    String url="http://localhost:8081/home/";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users=restTemplate.postForObject(url+"login", username, Users.class);
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
        Users user=new Users();
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bcryptPasswordEncoder.encode(password);
        user.setUsername(name);
        user.setPassword(pwd);
        String result=restTemplate.postForObject(url+"saveUser", user, String.class);
        //repository.save(user);
        return result;
    }
}
