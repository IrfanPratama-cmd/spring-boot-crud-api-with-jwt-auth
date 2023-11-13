package jwtspringproduct.jwtspringproduct.controller;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jwtspringproduct.jwtspringproduct.config.JwtService;
import jwtspringproduct.jwtspringproduct.model.User;

@RequestMapping("/api/v1")
@RestController
public class TestController {

    @GetMapping("/test")
    public String exampleEndpoint(@AuthenticationPrincipal User user) {
        // Pastikan header "Authorization" berisi token JWT dengan format "Bearer <token>"
        // String jwtToken = authorizationHeader.replace("Bearer ", "");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UUID userId = user.getId();

        // String userId = authentication.getName();

        return "User ID: " + userId;

        // if (userId != null) {
        //     // Lakukan tindakan berdasarkan user ID yang berhasil diekstrak
        //     return "User ID: " + userId;
        // } else {
        //     // Tangani jika token tidak valid atau kadaluwarsa
        //     return "Token JWT tidak valid atau telah kedaluwarsa.";
        // }
    }

}
