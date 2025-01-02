package puregito.backend.service.userService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import puregito.backend.dto.mailDto.ActivateCodeDto;
import puregito.backend.dto.userDto.UserRegisterDto;
import puregito.backend.entity.ActivateCode;
import puregito.backend.entity.RoleEntity;
import puregito.backend.entity.Roles;
import puregito.backend.entity.UserEntity;
import puregito.backend.exception.ExistByException;
import puregito.backend.repository.ActivateCodeRepository;
import puregito.backend.repository.RoleEntityRepository;
import puregito.backend.repository.UserEntityRepository;
import puregito.backend.security.JwtUtils;
import puregito.backend.service.emailService.ActivationService;

@Service
public class UserAuthService {
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ActivationService activationService;
    @Autowired
    private ActivateCodeRepository activateCodeRepository;

    Logger logger = LoggerFactory.getLogger(UserAuthService.class);

    public RoleEntity setRole() {
        return roleEntityRepository.findByName(Roles.ROLE_USER).get();
    }

    public String registerUser(UserRegisterDto registerDto) {
        UserEntity user = new UserEntity();

        if (userEntityRepository.existsByEmail(registerDto.getEmail())) {
            throw new ExistByException("Email already exists");
        }
        if (userEntityRepository.existsByUsername(registerDto.getUsername())) {
            throw new ExistByException("Username already exists");
        }
        if (userEntityRepository.existsByPhone(registerDto.getPhone())) {
            throw new ExistByException("Phone already exists");
        }
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setUsername(registerDto.getUsername());
        user.setEnabled(false);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(setRole());

        userEntityRepository.save(user);
        createActivateCode(user);

        activationService.sendActivationEmail(user.getEmail(),"Activation code","Your activation code is "+activateCodeRepository.findByUser_Id(user.getId()).get().getCode() );


        return "User registered successfully";
    }

    public String createActivateCode(UserEntity user) {
        ActivateCode activateCode = new ActivateCode();
        activateCode.setUser(user);
        activateCode.setCode(activationService.generateCode());
        activateCodeRepository.save(activateCode);
        return activateCode.getCode();
    }

    public String activateUser(ActivateCodeDto activateCodeDto ) {
        int i = 5;
       while (i > 0) {
           if(activateCodeRepository.findByCode(activateCodeDto.getCode()).isPresent()) {
               UserEntity user = new UserEntity();
               user = activateCodeRepository.findByCode(activateCodeDto.getCode()).get().getUser();
               user.setEnabled(true);
               userEntityRepository.save(user);
               activateCodeRepository.delete(activateCodeRepository.findByCode(activateCodeDto.getCode()).get());
               return "User activated successfully";
           } else {
               i--;
           }
       }
       activateCodeRepository.delete(activateCodeRepository.findByCode(activateCodeDto.getCode()).get());
        return "Activation code is not valid";
    }


}
