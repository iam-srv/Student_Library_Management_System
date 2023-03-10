package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import com.example.Student_Library_Management_System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public String addBook(@RequestBody BookRequestDto bookRequestDto){

        return bookService.addBook(bookRequestDto);
    }

    @GetMapping("/getall")
    public List<Book> getAllBooks(){

        return bookService.getAllBooks();
    }


}
