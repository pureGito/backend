
# Spring Boot JWT service and activation code  

A simple example for user registration, login and activation codes



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

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`db_username`

`db_password`

`email_username`

`email_password`

`jwt_code`







## Feedback

If you have any feedback, please reach out to us at puregito@gmail.com


## License

[MIT](https://github.com/pureGito/backend/blob/master/LICENSE)


## Author

- [@pureGito](https://github.com/pureGito)

