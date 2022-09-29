package com.example.murange.Controller;

import com.example.murange.Domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    // 회원 등록
    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody @Validated User user) {

        return new ResponseEntity(HttpStatus.OK);
    }

    // 회원 조회
    @GetMapping("/user/{user-id}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "user-id") String userId
    ) {
        User user = null;
        return new ResponseEntity(user, HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/user/{user-id}")
    public ResponseEntity deleteUser(
            @PathVariable(value = "user-id") String userId
    ) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
