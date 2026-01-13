package com.example.kdubackendassessment1sarvesh.service;

import com.example.kdubackendassessment1sarvesh.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
        private final Map<String, User> userMap=new HashMap<>();
        private final PasswordEncoder encoder=new BCryptPasswordEncoder();

        public CustomUserDetailsService(){
            userMap.put("sarvesh",new User("sarvesh",encoder.encode("sarvesh"),"ROLE_MANAGER"));
            userMap.put("john",new User("john",encoder.encode("john"),"ROLE_DRIVER"));
        }

        @Override
        public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
            User user=userMap.get(UserName);
            if(user==null){
                throw new UsernameNotFoundException("Username "+UserName+" does not exist");
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),
                    Collections.emptyList()
            );
        }



}
