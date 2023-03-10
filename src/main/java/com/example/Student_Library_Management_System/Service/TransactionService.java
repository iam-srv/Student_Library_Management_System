package com.example.Student_Library_Management_System.Service;

import com.example.Student_Library_Management_System.DTOs.IssueBookDto;
import com.example.Student_Library_Management_System.Enum.CardStatus;
import com.example.Student_Library_Management_System.Enum.TransactionStatus;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transaction;
import com.example.Student_Library_Management_System.Repository.BookRepository;
import com.example.Student_Library_Management_System.Repository.CardRepository;
import com.example.Student_Library_Management_System.Repository.TransactionRepository;
import org.hibernate.type.TrueFalseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String issueBook(IssueBookDto issueBookDto) throws Exception{

      int bookId = issueBookDto.getBookId();
      int cardId = issueBookDto.getCardId();


      Card card =  cardRepository.findById(cardId).get();
      Book book = bookRepository.findById(bookId).get();

      Transaction transaction = new Transaction();

      // setting the attributes , do some validations ,
        // set the foreign keys and the bidirectional settings
         // save the parent ;

  // setting the attributes
    transaction.setBook(book);
    transaction.setCard(card);
    transaction.setTransactionId(UUID.randomUUID().toString());
    transaction.setIssueOperation(true);
    transaction.setTransactionStatus(TransactionStatus.PENDING);


    // Validations
    if(book ==  null || book.isIssued() == true){
        transaction.setTransactionStatus(TransactionStatus.FAILED);
        transactionRepository.save(transaction);
        throw new Exception("Book is not available");
    }

    if(card == null || (card.getCardStatus()!= CardStatus.ACTIVATED)){

        transaction.setTransactionStatus(TransactionStatus.FAILED);
        transactionRepository.save(transaction);
        throw new Exception("Card is not valid");
    }

     transaction.setTransactionStatus(TransactionStatus.SUCCESS);

       // set attributes of book
        book.setIssued(true);
        // b/w the book  and transaction : bidirectional
        List<Transaction> listOfTransactionForBook = book.getListOfTransactions();
        listOfTransactionForBook.add(transaction);
        book.setListOfTransactions(listOfTransactionForBook);

        // for card
        List<Book> issuedBooksForCard = card.getBooksIssued();
        issuedBooksForCard.add(book);

        List<Transaction> transactionsListForCard = card.getTransactionList();
        transactionsListForCard.add(transaction);
        card.setTransactionList(transactionsListForCard);

        // saving the parent , here card is the parent of the book and transaction
        cardRepository.save(card);// automatically by cascading book  and transaction will be saved.

        return "Book Issued successful";
    }

    public String getTransactions(int bookId,int cardId){

        List<Transaction> transactionsList = transactionRepository.getTransactionsForBookAndCard(bookId,cardId);

        String transactionId = transactionsList.get(0).getTransactionId();

        return transactionId;
    }
}
