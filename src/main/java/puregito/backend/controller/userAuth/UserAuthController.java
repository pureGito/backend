package puregito.backend.controller.userAuth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import puregito.backend.dto.jwt.JwtResponse;
import puregito.backend.dto.mailDto.ActivateCodeDto;
import puregito.backend.dto.userDto.UserLoginDto;
import puregito.backend.dto.userDto.UserRegisterDto;
import puregito.backend.security.JwtUtils;
import puregito.backend.security.UserDetailsImp;
import puregito.backend.service.emailService.ActivationService;
import puregito.backend.service.userService.UserAuthService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class UserAuthController {

    @Autowired
    private UserAuthService userAuthService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private ActivationService activationService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto registerDto) {

        return ResponseEntity.ok(userAuthService.registerUser(registerDto));

    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto loginDto) {
    log.debug("loginDto {}",loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getInput(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token)
                .body(new JwtResponse(token,userDetails.getId(),userDetails.getUsername(),userDetails.getEmail(),roles));

    }

    @PostMapping("/activate")
    public ResponseEntity<?> activateUser(@RequestBody ActivateCodeDto activateToken) {

        return ResponseEntity.ok(userAuthService.activateUser(activateToken));
    }





}
