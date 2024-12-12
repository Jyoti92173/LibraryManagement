package com.app.librarymanagement.controller;

import com.app.librarymanagement.dto.AuthorDTO;
import com.app.librarymanagement.dto.BookDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.Book;
import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/book")
public class BookController {
    @Autowired
    private BookService bookService;
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @PostMapping(path = "/save")
    public ResponseEntity <Book> saveBook(@RequestBody BookDTO bookSaveDTO){
        try {
            return new ResponseEntity<>(bookService.addBook(bookSaveDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while saving book details, err={}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getAllBook")
    public ResponseEntity<List<BookDTO>> getAllBook(){
        try {
            List<BookDTO>  allBooks = bookService.getAllBook();
            return ResponseEntity.ok(allBooks);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateBook(@RequestBody BookDTO bookDTO){
        try {
            bookService.updateBook(bookDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Books Details could not save due to exception err={}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(value = "id") int id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthorNotFoundException ex) {
            logger.error("Book does not exist, id={}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Error occurred while deleting book with ID {}: {}", id, ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
