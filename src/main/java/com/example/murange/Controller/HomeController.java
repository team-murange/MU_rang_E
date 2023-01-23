package com.example.murange.Controller;

import com.example.murange.Config.oauth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/user")
    public @ResponseBody Long user(){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");
        return loginUser.getId();
    }

    @GetMapping(value = {"", "main" })
    public String main(Model model){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");

        if(loginUser != null){
            model.addAttribute("user", loginUser);
        }
        return "main";
    }

    @GetMapping("profile")
    public String profile(Model model){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");
        model.addAttribute("user", loginUser);
        return "profile";
    }

    @GetMapping("detection")
    public String detection(Model model){
        SessionUser loginUser = (SessionUser) httpSession.getAttribute("loginUser");
        model.addAttribute("user",loginUser);
        return "detection";
    }
}