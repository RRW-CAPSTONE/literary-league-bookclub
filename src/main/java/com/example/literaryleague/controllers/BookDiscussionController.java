package com.example.literaryleague.controllers;

import com.example.literaryleague.models.Book;
import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.models.BookDiscussion;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookClubRepository;
import com.example.literaryleague.repositories.BookDiscussionRepository;
import com.example.literaryleague.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookDiscussionController {
    private final BookClubRepository bcDao;
    private final UserRepository userDao;
    private final BookDiscussionRepository bdDao;

    public BookDiscussionController(BookClubRepository bcDao, UserRepository userDao, BookDiscussionRepository bdDao) {
        this.bcDao = bcDao;
        this.userDao = userDao;
        this.bdDao = bdDao;
    }

    @GetMapping("/clubs/{clubId}/discussions")
    public String showAllDiscussions(@PathVariable long clubId, Model model) {
        List<BookDiscussion> discussions = bdDao.findBookDiscussionsByBookClub_Id(clubId);
        model.addAttribute("discussions", discussions);
        model.addAttribute("clubId", clubId);
        return "discussions/allDiscussions";
    }

    @GetMapping("/clubs/{clubId}/discussions/{discussionId}")
    public String viewDiscussion(@PathVariable long clubId, @PathVariable long discussionId, Model model) {
        BookDiscussion discussion = bdDao.findBookDiscussionById(discussionId);
        model.addAttribute("discussion", discussion);
        model.addAttribute("clubId", clubId);
        return "discussions/viewDiscussion";
    }

    @GetMapping("/clubs/{clubId}/discussions/create")
    public String createDiscussionForm(@PathVariable long clubId, Model model) {
        model.addAttribute("discussion", new BookDiscussion());
        model.addAttribute("clubId", clubId);
        return "discussions/createDiscussion";
    }

    @PostMapping("/clubs/{clubId}/discussions/save")
    public String saveDiscussion(@PathVariable long clubId, @ModelAttribute BookDiscussion discussion) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        BookClub club = bcDao.findBookClubById(clubId);

        discussion.setUser(user);
        discussion.setBookClub(club);

        bdDao.save(discussion);

        return "redirect:/clubs/" + clubId + "/discussions";
    }

    @GetMapping("/clubs/{clubId}/discussions/{discussionId}/edit")
    public String editDiscussionForm(@PathVariable long clubId, @PathVariable long discussionId, Model model) {
        BookDiscussion discussion = bdDao.findBookDiscussionById(discussionId);
        model.addAttribute("discussion", discussion);
        model.addAttribute("clubId", clubId);
        return "discussions/createDiscussion";
    }

    @PostMapping("/clubs/{clubId}/discussions/{discussionId}/delete")
    public String deleteDiscussion(@PathVariable long clubId, @PathVariable long discussionId) {
        BookDiscussion discussion = bdDao.findBookDiscussionById(discussionId);
        if (discussion != null && discussion.getBookClub().getId() == clubId) {
            bdDao.delete(discussion);
        }
        return "redirect:/clubs/" + clubId + "/discussions";
    }


    @PostMapping("/clubs/{clubId}/discussions/{discussionId}/comments")
    public String addComment(@PathVariable long clubId, @PathVariable long discussionId, @RequestParam String commentText) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userDao.findByUsername(auth.getName());
        BookDiscussion discussion = bdDao.findBookDiscussionById(discussionId);
        discussion.addComment(commentText, user);

        bdDao.save(discussion);

        return "redirect:/clubs/" + clubId + "/discussions/" + discussionId;
    }

}