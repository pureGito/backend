package puregito.backend.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import puregito.backend.entity.RoleEntity;
import puregito.backend.entity.UserEntity;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class UserDetailsImp implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String email;
    @JsonIgnore
    private String password;
    private RoleEntity role;

    public UserDetailsImp(
        Long id,
        String username,
        String email,
        String password,
        Boolean enabled,
        RoleEntity role
    ){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserDetailsImp build(UserEntity user){

        return new UserDetailsImp(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword(),
            user.getEnabled(),
            user.getRole()

        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.getName().name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
