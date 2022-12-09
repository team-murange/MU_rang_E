package com.example.murange.Controller;

import com.example.murange.Domain.User;
import com.example.murange.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setSuffix(".html");
        return resolver;
    }

    // 페이지
    @GetMapping(value = {"/", "/main" })
    String main() {
        return "main";
    }

    @GetMapping(value = {"/loginForm" })
    String login() {
        return "loginForm";
    }

    @GetMapping("/fail")
    String fail() {
        return "fail";
    }

    @GetMapping("/profile")
    String profile() {
        return "profile";
    }

    @GetMapping("/detection")
    String detection() {
        return "detection";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(HttpServletRequest httpServletRequest) {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        String encPwd = bCryptPasswordEncoder.encode(password);

        User user = User.builder().role("ROLE_USER").username(username).password(encPwd).email(email).build();

        userRepository.save(user);
        return "redirect:/loginForm";
    }

//    @GetMapping(value ="/main")
//    ModelAndView mainModel(Authentication authentication) {
//        User user = Optional.ofNullable(userRepository.findByUsername(authentication.getName()))
//                .map(u -> User.builder().username(u.getUsername()).build())
//                .orElseThrow(IllegalArgumentException::new);
//
//        ModelAndView modelAndView = new ModelAndView("/main");
//        modelAndView.addObject("user", user);
//
//        return modelAndView;
//    }
}