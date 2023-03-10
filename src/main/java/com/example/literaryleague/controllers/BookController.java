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

//    @GetMapping("/books")
//    public String showAllBooks(Model model){
//        model.addAttribute("books", bookDao.findAll());
//        return "books/allBooks";
//    }

//    @GetMapping("/books/{id}")
//    public String viewBook(@PathVariable long id, Model model){
//        Book book = bookDao.findBookById(id);
//        model.addAttribute("book", book);
//        return "books/viewBook";
//    }

    @GetMapping("/books")
    public String createBookForm(Model model){
        model.addAttribute("book", new Book());
        return "books/allBooks";
    }

//    @PostMapping("/books/save")
//    public String saveBook(
//            @RequestParam(name = "title") String title
//    ){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Book origBook = bookDao.findBookById();
//        Book book = new Book();
//        if(origBook == null || user.getId() == origBook.getUser().getId()){
//            book.setUser(user);
//            bookDao.save(book);
//        }
//        return "redirect: /books";
//    }

    @GetMapping("/books/save")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "/books/save";
    }
    @PostMapping("/books/save")
    public String createBook(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author,
            @RequestParam(name = "bookId") String bookId
    ) {
        System.out.println(author);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Book origBook = bookDao.findBookByIsbn(bookId);
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(bookId);
        // if(origBook == null || user.getId() == origBook.getUser().getId()){
            book.setUser(user);
            bookDao.save(book);
        //}
        return "redirect:/books";
    }


}
