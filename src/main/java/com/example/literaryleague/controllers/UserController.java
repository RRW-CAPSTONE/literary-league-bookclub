package com.example.literaryleague.controllers;

import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookClubRepository;
import com.example.literaryleague.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    private final UserRepository userDao;

    private final BookClubRepository bcDao;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, BookClubRepository bcDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.bcDao = bcDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    // use this is we go with book club memberships
    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user = userDao.findById(user.getId());
        List<BookClub> bookClubs = user.getBookClubs();
        model.addAttribute("bookClubs", bookClubs);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping("/profile")
    public String editProfile(@ModelAttribute("user") User user, Principal principal) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/profile";
    }
}
