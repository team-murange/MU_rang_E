package com.example.murange.Controller;

import com.example.murange.Config.auth.PrincipalDetails;
import com.example.murange.Domain.User;
import com.example.murange.Dto.UserJoinRequest;
import com.example.murange.Dto.UserResponseDto;
import com.example.murange.Service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public Long user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return principalDetails.getUser().getId();
    }

//    @PostMapping("/join")
//    public void createUser(UserJoinRequest userJoinRequest, HttpServletResponse response) throws IOException {
//        userService.createUser(userJoinRequest);
//        response.sendRedirect("/login");
//    }

    @ApiOperation(value = "회원 가입")
    @PostMapping("/user")
    public ResponseEntity saveUser(@RequestBody @Validated UserJoinRequest userJoinRequest) {

        userService.createUser(userJoinRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(value = "회원 조회")
    @GetMapping("/user/{user-id}")
    public ResponseEntity<User> getUser(
            @PathVariable(value = "user-id") Long userId
    ) {
        User user = userService.findUser(userId);
        UserResponseDto userResponseDto = new UserResponseDto(user);
        return new ResponseEntity(userResponseDto, HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/user/{user-id}")
    public ResponseEntity deleteUser(
            @PathVariable(value = "user-id") String userId
    ) {
        return new ResponseEntity(HttpStatus.OK);
    }
}
