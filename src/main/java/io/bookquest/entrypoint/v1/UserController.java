package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

import static java.net.URI.create;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody UserEntrypoint user) {
        userService.createAccount(user);
        return ResponseEntity
                .created(create("/api/v1/users"))
                .build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody UserEntrypoint userEntrypoint) {
        userService.login(userEntrypoint.username(), userEntrypoint.senha());
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<Void> getUser(@RequestParam("username") String username) {
        return ResponseEntity.ok().build();
    }
}
