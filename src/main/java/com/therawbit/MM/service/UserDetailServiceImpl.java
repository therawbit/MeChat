package com.therawbit.MM.service;

import com.therawbit.MM.entity.User;
import com.therawbit.MM.entity.UserDetailsImpl;
import com.therawbit.MM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl  implements UserDetailsService {
    private final UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Invalid Credentail"));
        return new UserDetailsImpl(user);
    }
}
