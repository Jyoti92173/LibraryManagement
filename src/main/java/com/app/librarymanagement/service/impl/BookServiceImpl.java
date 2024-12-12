package com.app.librarymanagement.service.impl;


import com.app.librarymanagement.dto.BookDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.Book;
import com.app.librarymanagement.entity.Publisher;

import com.app.librarymanagement.exception.BookNotFoundException;
import com.app.librarymanagement.repo.AuthorRepo;
import com.app.librarymanagement.repo.BookRepo;
import com.app.librarymanagement.repo.PublisherRepo;
import com.app.librarymanagement.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BookServiceImpl implements BookService {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private PublisherRepo publisherRepo;

    @Autowired
    private BookRepo bookRepo;

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Override
    public Book addBook(BookDTO bookSaveDTO) {
        // Fetch Author by authorId
        Author author = authorRepo.findById(bookSaveDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found with ID: " + bookSaveDTO.getAuthorId()));

        // Fetch Publisher by publisherId
        Publisher publisher = publisherRepo.findById(bookSaveDTO.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher not found with ID: " + bookSaveDTO.getPublisherId()));

        // Create and save Book entity
        Book book = new Book(bookSaveDTO.getTitle(), bookSaveDTO.getCost(), publisher, author);
        return bookRepo.save(book);
    }



    @Override
    public void updateBook(BookDTO bookDTO) {
        if (bookRepo.existsById(bookDTO.getBookId())) {
            Optional<Book> bookEntity = bookRepo.findById(bookDTO.getBookId());
            if(bookEntity.isPresent()) {
                var book= bookEntity.get();
                book.setTitle(bookDTO.getTitle());
                bookRepo.save(book);
                logger.info("Book details saved for id={}", book.getBookId());
            }
        } else {
            logger.info("Book Not Exist!");
        }
    }

    @Override
    public List<BookDTO> getAllBook() {
        List<Book> Books = bookRepo.findAll();
        System.out.println(Books);
        List<BookDTO> bookDTOList = new ArrayList<>();
        Books.forEach(book -> bookDTOList.add(new BookDTO(book.getBookId(), book.getPublisher().getPublisherId(),book.getAuthor().getAuthorId(),book.getCost(),book.getTitle())));
        System.out.println(bookDTOList);
        return bookDTOList;
    }
    @Override
    public void deleteBook(int id) {
        Optional<Book> bookOptional = bookRepo.findById(id);
        if (bookOptional.isPresent()) {
            bookRepo.deleteById(id);
            logger.info("Book with ID={} deleted successfully.", id);
        } else {
            throw new BookNotFoundException("BookId" + id + "Does not exist.");
        }

    }
}
