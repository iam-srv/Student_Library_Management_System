package com.example.Student_Library_Management_System.Controllers;

import com.example.Student_Library_Management_System.DTOs.AuthorEntryDTO;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

  @Autowired
    AuthorService authorService;

    @PostMapping("/add")
    public String addAuthor(@RequestBody AuthorEntryDTO authorEntryDTO){
      return authorService.createAuthor(authorEntryDTO);
    }

  @GetMapping("/getAuthor")
  public AuthorResponseDto getAuthor(@RequestParam("authorId")Integer authorId){
    return authorService.getAuthor(authorId);
  }
}
