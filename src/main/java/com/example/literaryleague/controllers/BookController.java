package com.example.literaryleague.controllers;

import com.example.literaryleague.models.Book;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookRepository;
import com.example.literaryleague.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {
    private final UserRepository userDao;
    private final BookRepository bookDao;

    public BookController(UserRepository userDao, BookRepository bookDao){
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    public String showAllBooks(Model model){
        model.addAttribute("books", bookDao.findAll());
        return "books/allBooks";
    }

    @GetMapping("/books/{id}")
    public String viewBook(@PathVariable long id, Model model){
        Book book = bookDao.findBookById(id);
        model.addAttribute("book", book);
        return "books/viewBook";
    }

    @GetMapping("/books/create")
    public String createBookForm(Model model){
        model.addAttribute("book", new Book());
        return "books/createBook";
    }

    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book origBook = bookDao.findBookById(book.getId());
        if(origBook == null || user.getId() == origBook.getUser().getId()){
            book.setUser(user);
            bookDao.save(book);
        }
        return "redirect: /books";
    }
}
