package jwtspringproduct.jwtspringproduct.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword.toString(),BCrypt.gensalt(8));
    }
    @Override
    public boolean matches(CharSequence plainTextPassword, String passwordInDatabase) {
        return BCrypt.checkpw(plainTextPassword.toString(),passwordInDatabase);
    }
}