package com.example.literaryleague.controllers;

import com.example.literaryleague.models.Book;
import com.example.literaryleague.models.User;
import com.example.literaryleague.repositories.BookRepository;
import com.example.literaryleague.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private final UserRepository userDao;
    private final BookRepository bookDao;

    public BookController(UserRepository userDao, BookRepository bookDao){
        this.userDao = userDao;
        this.bookDao = bookDao;
    }

    @GetMapping("/books")
    public String createBookForm(Model model){
        List<Book> books = bookDao.findAllByOrderByTitleAsc();
        model.addAttribute("book", new Book());
        model.addAttribute("books", books);
        return "books/allBooks";
    }

    @PostMapping("/books/save")
    public String saveBook(@ModelAttribute Book book){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Book origBook = bookDao.findBookById(book.getId());
        if(origBook == null || user.getId() == origBook.getUser().getId()){
            book.setUser(user);
            bookDao.save(book);
        }
        return "redirect:/books";
    }


}
