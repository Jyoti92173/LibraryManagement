package com.app.librarymanagement.service;

import com.app.librarymanagement.dto.BookDTO;
import com.app.librarymanagement.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {


     void updateBook(BookDTO bookDTO) ;


    List<BookDTO> getAllBook() ;
    

    Book addBook(BookDTO bookSaveDTO);

    void deleteBook(int id);
}
