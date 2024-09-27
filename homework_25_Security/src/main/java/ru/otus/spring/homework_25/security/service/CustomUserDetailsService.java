package ru.otus.spring.homework_25.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.otus.spring.homework_25.domain.Users;
import ru.otus.spring.homework_25.repository.UserRepository;
import ru.otus.spring.homework_25.service.UserService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.findUser(username).orElseThrow();
        Set<GrantedAuthority> grantedAuthority = Set.of(new SimpleGrantedAuthority("ROLE_"+user.getRole().toUpperCase()));
        return new User(user.getName(), user.getPassword(), grantedAuthority);
    }
}