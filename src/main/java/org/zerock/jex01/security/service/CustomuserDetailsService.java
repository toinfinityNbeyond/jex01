package org.zerock.jex01.security.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Log4j2
public class CustomuserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.warn("CustomuserDetailsService----------------");
        log.warn("CustomuserDetailsService----------------");
        log.warn("CustomuserDetailsService----------------");
        log.warn("CustomuserDetailsService----------------");
        log.warn("CustomuserDetailsService----------------");

        User result = (User) User.builder()
                .username(username)
                .password("$2a$10$S9IWhMaX06oWxWk4BlKL5ObyCarNkSSNpwC7x57aafbatyNl3W8Nm")
                .accountExpired(false)
                .accountLocked(false)
                .authorities("ROLE_MEMBER","ROLE_ADMIN")
                .build();


        return result;
    }
}
