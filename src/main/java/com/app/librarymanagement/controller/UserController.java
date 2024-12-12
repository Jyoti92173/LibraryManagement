package com.app.librarymanagement.controller;

import com.app.librarymanagement.dto.AuthorDTO;
import com.app.librarymanagement.dto.UserDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.User;
import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.exception.UserNotFoundException;
import com.app.librarymanagement.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(path = "/save")
    public ResponseEntity <User> saveAuthor(@RequestBody UserDTO userSaveDTO){
        try {
            return new ResponseEntity<>(userService.addUser(userSaveDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while saving user details, err={}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/getAllUser")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        try {
            List<UserDTO> allUsers = userService.getAllUser();
            return ResponseEntity.ok(allUsers);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO){
        try {
            userService.updateAuthor(userDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("User Details could not save due to exception err={}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable(value = "id") int id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (UserNotFoundException ex) {
            logger.error("User does not exist, id={}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Error occurred while deleting User with ID {}: {}", id, ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
