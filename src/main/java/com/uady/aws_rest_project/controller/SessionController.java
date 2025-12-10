package com.uady.aws_rest_project.controller;

import com.uady.aws_rest_project.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/alumnos/{studentId}/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @PathVariable Integer studentId,
            @RequestBody Map<String, String> body) {

        String password = body.get("password");

        try {
            String sessionString = sessionService.login(studentId, password);

            Map<String, String> response = new HashMap<>();
            response.put("sessionString", sessionString);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "Credenciales invalidas"));
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> verify(@RequestBody Map<String, String> body) {
        String sessionString = body.get("sessionString");
        boolean isValid = sessionService.verifySession(sessionString);

        if (isValid) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Session Valid"));
        } else {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", "Session invalid"));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(@RequestBody Map<String, String> body) {
        String sessionString = body.get("sessionString");

        sessionService.logout(sessionString);

        return ResponseEntity.ok(Collections.singletonMap("message", "Logout Successful"));
    }

}
