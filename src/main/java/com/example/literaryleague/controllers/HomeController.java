package com.example.literaryleague.controllers;

import com.example.literaryleague.models.Book;
import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.repositories.BookClubRepository;
import com.example.literaryleague.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final BookRepository bookDao;

    private final BookClubRepository bcDao;

    public HomeController(BookRepository bookDao, BookClubRepository bcDao) {
        this.bookDao = bookDao;
        this.bcDao = bcDao;
    }

    @GetMapping("/")
    public String homePage(Model model) {
//        List<BookClub> bookClubs = bcDao.findAll();
//        model.addAttribute("bookClubs", bookClubs);

        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);

        List<BookClub> last4Clubs = bcDao.findTop4ByOrderByCreatedAtDesc();
        model.addAttribute("last4", last4Clubs);

        return "index"; }

    @GetMapping("/aboutus")
    public String aboutUsPage(){
        return "aboutUs";
    }
}
