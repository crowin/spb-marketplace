package ru.oreshnikova.marketplace.config;

import ru.oreshnikova.marketplace.entity.Role;
import ru.oreshnikova.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static ru.oreshnikova.marketplace.config.WebSecurityConfig.passwordEncoder;

@Service
public class BasicUserDetailsService implements UserDetailsService {
    @Value("${spring.security.user.name}")
    private String adminUserName;
    @Value("${spring.security.user.password}")
    private String adminPassword;
    @Value("${spring.security.user.roles}")
    private String adminRole;
    private UserRepository userRepository;

    public BasicUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(adminUserName.equals(username)) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(adminUserName).password(passwordEncoder().encode(adminPassword)).roles(adminRole).build();
        }

        var user = userRepository.findByName(username);
        if (user.isPresent()) {
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.get().getName()).password(user.get().getPassword()).roles(user.get().getRoles().stream().map(Role::getName).toArray(String[]::new))
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
