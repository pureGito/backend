package puregito.backend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import puregito.backend.entity.UserEntity;
import puregito.backend.repository.UserEntityRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {
    @Autowired
    private UserEntityRepository userEntityRepository;

    Logger logger = LoggerFactory.getLogger(UserDetailsServiceImp.class);



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username: {}",username);

        UserEntity user = userEntityRepository.findByUsernameOrEmail
                (username, username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        logger.info("user: {}",user);
        return UserDetailsImp.build(user);
    }





}
