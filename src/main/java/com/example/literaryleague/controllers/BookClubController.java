package com.example.literaryleague.controllers;


import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookClubRepository;
import com.example.literaryleague.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookClubController {
    private final BookClubRepository bcDao;
    private final UserRepository userDao;

    public BookClubController(BookClubRepository bcDao, UserRepository userDao){
        this.bcDao = bcDao;
        this.userDao = userDao;
    }

    @GetMapping("/clubs")
    public String showAllClubs(Model model){
        List<BookClub> clubs = bcDao.findAll();
        model.addAttribute("clubs", clubs);
        return "clubs/allClubs";
    }

    @GetMapping("/clubs/{id}")
    public String viewClub(@PathVariable long id, Model model){
        BookClub club = bcDao.findBookClubById(id);
        model.addAttribute("club", club);
        return "clubs/viewClub";
    }

    @GetMapping("/clubs/create")
    public String createClubForm(Model model) {
        model.addAttribute("club", new BookClub());
        return "clubs/createClub";
    }

    @PostMapping("/clubs/save")
    public String saveClub(@ModelAttribute BookClub club){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookClub origClub = bcDao.findBookClubById(club.getId());
        if(origClub == null || user.getId() == origClub.getUser().getId()){
            club.setUser(user);
            bcDao.save(club);
        }
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{id}/edit")
    public String editClubForm(Model model, @PathVariable long id){
        model.addAttribute("club", bcDao.findBookClubById(id));
        return "clubs/createClub";
    }

}

