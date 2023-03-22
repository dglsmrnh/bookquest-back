package io.bookquest.entrypoint.v1;

import io.bookquest.entrypoint.v1.dto.UserEntrypoint;
import io.bookquest.usecase.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.net.URI;
import java.util.List;
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
    public ResponseEntity<UserEntrypoint> getUser(@PathVariable("username") String username) {
        UserEntrypoint user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/{username}/images")
    public ResponseEntity<Object> saveAvatar(@PathVariable("username") String username,
            @RequestParam(value = "file") MultipartFile file) {
        String key = userService.uploadFile(file, username);
        return ResponseEntity.ok(Map.of("s3_key", key));
    }

    @GetMapping("/users/{username}/images")
    public ResponseEntity<Object> getAvatar(@PathVariable("username") String username) {
        String key = userService.getAvatarPicture(username);
        return ResponseEntity.ok(Map.of("image_encoded", key));
    }
}
