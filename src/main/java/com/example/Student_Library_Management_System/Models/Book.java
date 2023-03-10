package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enum.Genre;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int pages;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    // book is the child of author

    private double rating;
    @ManyToOne //mapping books to Author
    @JoinColumn // Add an Extra Attribute
    private Author author;

    @ManyToOne
    @JoinColumn
    private Card card;

    //bidirectional mapping b/w the Transaction
    @OneToMany(mappedBy = "book" ,cascade = CascadeType.ALL)
    private List<Transaction> ListOfTransactions = new ArrayList<>();


    private boolean Issued;

    public Book() {
    }

    public List<Transaction> getListOfTransactions() {
        return ListOfTransactions;
    }

    public void setListOfTransactions(List<Transaction> listOfTransactions) {
        ListOfTransactions = listOfTransactions;
    }

    public boolean isIssued() {
        return Issued;
    }

    public void setIssued(boolean issued) {
        Issued = issued;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
