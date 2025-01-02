.
└── backend/
    └── src/
        └── main/
            └── java/
                └── puregito.backend/
                    ├── config/
                    │   └── WebSecurityConfig
                    ├── controller.userAuth/
                    │   └── UserAuthController
                    ├── dto/
                    │   ├── jwt/
                    │   │   └── JwtResponse
                    │   ├── mailDto/
                    │   │   └── ActivateCodeDto
                    │   └── userDto/
                    │       ├── UserLoginDto
                    │       └── UserRegisterDto
                    ├── entity/
                    │   ├── ActivateCode
                    │   ├── RoleEntity
                    │   ├── Roles
                    │   └── UserEntity
                    ├── exception/
                    │   ├── ExistByException
                    │   └── GlobalExceptionHandler
                    ├── repository/
                    │   ├── ActivateCodeRepository
                    │   ├── RoleEntityRepository
                    │   └── UserEntityRepository
                    ├── security/
                    │   ├── JwtAuthFilter
                    │   ├── JwtUtils
                    │   ├── UserDetailsImp
                    │   └── UserDetailsServiceImp
                    ├── service/
                    │   ├── emailService/
                    │   │   ├── ActivationService
                    │   │   └── EmailConfig
                    │   └── userService/
                    │       └── UserAuthService
                    └── BackendApplication
