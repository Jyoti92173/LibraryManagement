package com.app.librarymanagement.service.impl;

import com.app.librarymanagement.dto.AuthorDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.repo.AuthorRepo;
import com.app.librarymanagement.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepo authorRepo;

    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);

    @Override
    public Author addAuthor(AuthorDTO authorSaveDTO) {
        Author author = new Author(authorSaveDTO.getName(), authorSaveDTO.getPhoneNo());
        return authorRepo.save(author);
    }


    @Override
    public List<AuthorDTO> getAllAuthor() throws Exception {
        List<Author> getAuthors = authorRepo.findAll();
        System.out.println(getAuthors);
        List<AuthorDTO> authorDTOList = new ArrayList<>();
        getAuthors.forEach(author -> authorDTOList.add(new AuthorDTO(author.getAuthorId(), author.getName(),author.getPhoneNo())));
        System.out.println(authorDTOList);
        return authorDTOList;
    }

    @Override
    public void updateAuthor(AuthorDTO authorDTO) {
            if (authorRepo.existsById(authorDTO.getAuthorId())) {
                Optional<Author> authorEntity = authorRepo.findById(authorDTO.getAuthorId());
                if(authorEntity.isPresent()) {
                    var author = authorEntity.get();
                    author.setName(authorDTO.getName());
                    authorRepo.save(author);
                    logger.info("Author details saved for id={}", author.getAuthorId());
                }
            } else {
                logger.info("Author Not Exist!");
            }
        }

    @Override
    public void deleteAuthor(int id) {
        Optional<Author> authorOptional = authorRepo.findById(id);
        if (authorOptional.isPresent()) {
            authorRepo.deleteById(id);
            logger.info("Author with ID={} deleted successfully.", id);
        } else {
            f
        }
    }


}
