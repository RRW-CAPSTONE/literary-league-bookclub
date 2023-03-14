package com.example.literaryleague.controllers;

import com.example.literaryleague.models.Book;
import com.example.literaryleague.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final BookRepository bookDao;

    public HomeController(BookRepository bookDao) {
        this.bookDao = bookDao;
    }

    @GetMapping("/")
    public String homePage(Model model) {

        List<Book> books = bookDao.findAll();
        model.addAttribute("books", books);

        return "index"; }

    @GetMapping("/aboutus")
    public String aboutUsPage(){
        return "aboutUs";
    }
}
