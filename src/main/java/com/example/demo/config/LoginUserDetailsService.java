package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.MUser;
import com.example.demo.domain.service.EcService;

@Service
public class LoginUserDetailsService implements UserDetailsService {

    @Autowired
    private EcService ecService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    	// ログインユーザー取得
    	Optional<MUser> loginUserOptional = ecService.getLoginUser(userId);
        return loginUserOptional.map(loginUser -> new LoginUserDetails(loginUser))
                .orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}
