package com.app.librarymanagement.service;

import com.app.librarymanagement.dto.AuthorDTO;
import com.app.librarymanagement.dto.PublisherDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.Publisher;

import java.util.List;

public interface AuthorService {

    Author addAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthor() throws Exception;

    void updateAuthor(AuthorDTO authorDTO);

    void deleteAuthor(int id);


}
