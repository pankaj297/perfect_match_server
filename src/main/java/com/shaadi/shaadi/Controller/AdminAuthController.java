package com.shaadi.shaadi.Controller;



import com.shaadi.shaadi.dto.AdminLoginRequest;
import com.shaadi.shaadi.Services.AdminAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginRequest req) {
        boolean ok = adminAuthService.login(req.getUsername(), req.getPassword());
        if (ok) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}