package ru.ncedu.schek.cracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * Created by Admin on 02.04.2019.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class ChatController {

    @RequestMapping("/chat")
    public String chat(HttpServletRequest request, org.springframework.ui.Model model) throws URISyntaxException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || username.isEmpty()) {
            return "redirect:/loginchat";
        }
        model.addAttribute("username", username);
        return "chat.html";
    }

    @RequestMapping(path = "/loginchat", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }

    @RequestMapping(path = "/loginchat", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "loginchat";
        }
        request.getSession().setAttribute("username", username);

        return "redirect:/chat";
    }
    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/loginchat";
    }
}
