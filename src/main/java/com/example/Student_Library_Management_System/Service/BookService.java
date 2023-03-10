package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Repository.AuthorRepository;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;
    public String addBook(BookRequestDto bookDto){

        Book book = new Book();

        int authorId = bookDto.getAuthorId();

        Author author = authorRepository.findById(authorId).get();

        book.setAuthor(author);
        book.setName(bookDto.getName());
        book.setGenre(bookDto.getGenre());
        book.setPages(bookDto.getPages());
        book.setIssued(false);

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);

        author.setBooksWritten(currentBooksWritten);

        authorRepository.save(author);// no need of adding into the book repo it will automatically saved by cascading effect

        return "Book Added Successfully";
    }

    public List<Book> getAllBooks(){
        return  bookRepository.findAll();
    }


}
