package com.app.librarymanagement.controller;

import com.app.librarymanagement.dto.PublisherDTO;
import com.app.librarymanagement.entity.Publisher;
import com.app.librarymanagement.exception.PublisherNotFoundException;
import com.app.librarymanagement.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;
    private static final Logger logger = LoggerFactory.getLogger(PublisherController.class);

    @PostMapping(path = "/save")
    public ResponseEntity<Publisher> savePublisher(@RequestBody PublisherDTO publisherSaveDTO){
        try {
            return new ResponseEntity<>(publisherService.addPublisher(publisherSaveDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Exception occurred while saving author details, err={}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/getAllPublisher")
    public ResponseEntity<List<PublisherDTO>> getAllPublisher(){
        try {
            List<PublisherDTO> allPublishers = publisherService.getAllPublisher();
            return ResponseEntity.ok(allPublishers);
        } catch (Exception e) {
            logger.error("Error fetching publishers", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/update")
    public ResponseEntity<Void> updatePublisher(@RequestBody PublisherDTO publisherDTO){
        try {
            publisherService.updatePublisher(publisherDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Publisher Details could not save due to exception err={}", ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable(value = "id") int id) {
        try {
            publisherService.deletePublisher(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (PublisherNotFoundException ex) {
            logger.error("Publisher does not exist, id={}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            logger.error("Error occurred while deleting author with ID {}: {}", id, ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // Unexpected errors
        }
    }


}
