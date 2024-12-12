package com.app.librarymanagement.controller;

import com.app.librarymanagement.dto.AuthorDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/v1/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;
    private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @PostMapping(path = "/save")
    public ResponseEntity <Author> saveAuthor(@RequestBody AuthorDTO authorSaveDTO){
        try {
            return new ResponseEntity<>(authorService.addAuthor(authorSaveDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while saving author details, err={}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getAllAuthor")
    public ResponseEntity<List<AuthorDTO>> getAllAuthor(){
        try {
            List<AuthorDTO> allAuthors = authorService.getAllAuthor();
            return ResponseEntity.ok(allAuthors);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateAuthor(@RequestBody AuthorDTO authorDTO){
        try {
            authorService.updateAuthor(authorDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Author Details could not save due to exception err={}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable(value = "id") int id) {
        try {
           authorService.deleteAuthor(id);
           return new ResponseEntity<>(HttpStatus.OK);
        } catch (AuthorNotFoundException ex) {
            logger.error("Author does not exist, id={}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Error occurred while deleting author with ID {}: {}", id, ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
