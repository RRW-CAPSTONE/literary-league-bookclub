package com.example.literaryleague.controllers;


import com.example.literaryleague.models.Book;
import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.models.BookDiscussion;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookClubRepository;
import com.example.literaryleague.repositories.BookDiscussionRepository;
import com.example.literaryleague.repositories.BookRepository;
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
    private final BookDiscussionRepository bdDao;

    private final BookRepository bookDao;

    public BookClubController(BookClubRepository bcDao, UserRepository userDao, BookDiscussionRepository bdDao, BookRepository bookDao){
        this.bcDao = bcDao;
        this.userDao = userDao;
        this.bdDao = bdDao;
        this.bookDao = bookDao;
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
//    NOT WORKING//
//@GetMapping("/clubs/{id}")
//public String viewClub(@PathVariable Long id, Model model) {
//    BookClub club = bcDao.findBookClubById(id);
//    List<BookDiscussion> bookDiscussions = club.getBookDiscussions();
//    model.addAttribute("club", club);
//    model.addAttribute("bookDiscussions", bookDiscussions);
//    return "clubs/viewClub";
//}

    @GetMapping("/clubs/create")
    public String createClubForm(Model model) {
        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);
        model.addAttribute("club", new BookClub());
        return "clubs/createClub";
    }

    // working!!! allows creating of a club and allows editing of club with proper text in buttons
    @PostMapping("/clubs/save")
    public String saveClub(@ModelAttribute BookClub club, @RequestParam(value = "books") Book book){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book currentBook = bookDao.findBookById(book.getId());
        BookClub origClub = bcDao.findBookClubById(club.getId());
        club.setCurrent_book(currentBook);
        if(origClub == null || user.getId() == origClub.getUser().getId()){

            club.setUser(user);
            bcDao.save(club);
        }
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{id}/edit")
    public String editClubForm(Model model, @PathVariable long id){
        List<Book> books = bookDao.findAll();
        model.addAttribute("club", bcDao.findBookClubById(id));
        model.addAttribute("books", books);
        return "clubs/editClub";
    }


//    @GetMapping("/clubs/{id}/edit")
//    public String editClubForm(Model model, @PathVariable long id){
//        List<Book> books = bookDao.findAll();
//        model.addAttribute("books", books);
//        model.addAttribute("club", bcDao.findBookClubById(id));
//        return "clubs/createClub";
//    }


//    @PostMapping("/clubs/save")
//    public String editClub(@ModelAttribute BookClub club, @RequestParam(value = "books") Book book){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Book currentBook = bookDao.findBookById(book.getId());
//        System.out.println(book);
//        System.out.println(book.getId());
//        System.out.println(currentBook.getId());
//
//        BookClub origClub = bcDao.findBookClubById(club.getId());
//        if(origClub == null || user.getId() == origClub.getUser().getId()){
//            club.setUser(user);
//            club.setCurrent_book(currentBook);
//            bcDao.save(club);
//        }
//        return "redirect:/clubs";
//    }
//



    @PostMapping("/clubs/join")
    public String joinClub(@RequestParam(name = "clubId") long clubId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookClub origClub = bcDao.findBookClubById(clubId);
        origClub.addUser(user);
        bcDao.save(origClub);

        return "redirect:/clubs/" + clubId;
    }
    @GetMapping("/clubs/discussion/create/{id}")
    public String createDiscussionForm(Model model, @PathVariable long id) {
        System.out.println(id);
        model.addAttribute("discussion", new BookDiscussion());
        model.addAttribute("id", id);
        return "clubs/clubDiscussion";
    }
//    @PostMapping("/clubs/discussion/save")
//    public String saveDiscussionClub(@ModelAttribute BookDiscussion discussion){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        discussion.setUser(user);
//        bdDao.save(discussion);
//        return "redirect:/clubs";
//    }


//    BookClub controller
//    @PostMapping("/clubs/discussion/save")
//    public String saveDiscussionClub(@ModelAttribute BookDiscussion discussion, BookClub club,@RequestParam("bc_id") Long id){

    @PostMapping("/clubs/discussion/save")
    public String saveDiscussionClub(@ModelAttribute BookDiscussion discussion, @RequestParam(name = "bc_id") long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(id);
//        BookClub origClub = bcDao.findBookClubById(bookClub.getId());
        BookDiscussion origDiscussion = bdDao.findBookDiscussionById(discussion.getId());
        BookClub club = bcDao.findBookClubById(discussion.getId());
        System.out.println(club);
        discussion.setBookClub(club);

        if(origDiscussion == null || user.getId() == origDiscussion.getUser().getId()){
            discussion.setUser(user);
            bdDao.save(discussion);
        }
        return "redirect:/clubs";
    }
    @PostMapping("/clubs/comment")
    public String commentClub(@RequestParam(name = "clubComment") long clubComment){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BookClub origClub = bcDao.findBookClubById(clubComment);
        origClub.addUser(user);
        bcDao.save(origClub);

        return "redirect:/clubs";
    }


}

