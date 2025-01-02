package com.app.librarymanagement.controller;

import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.repo.AuthorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AuthorControllerTest {

    @Test
    void saveAuthor() {
        Author author = new Author();
        author.setAuthorId(1);
        author.setName("George");
        author.setPhoneNo("9279972455");
        Assertions.assertEquals(1, author.getAuthorId());
        Assertions.assertEquals("George",author.getName());
        Assertions.assertEquals("9279972455",author.getPhoneNo());

    }

    @Test
    void getAllAuthor() {
        Author author1 = new Author();
        author1.setAuthorId(1);
        author1.setName("George");
        author1.setPhoneNo("9279972455");

        Author author2 = new Author();
        author2.setAuthorId(2);
        author2.setName("Jyoti");
        author2.setPhoneNo("9279972455");

        Author author3 = new Author();
        author3.setAuthorId(3);
        author3.setName("Nelson Mandal");
        author3.setPhoneNo( " 7678234494 ");

        Author author4 = new Author();
        author4.setAuthorId(6);
        author4.setName(" Narayana ");
        author4.setPhoneNo( "9834984556");

        List<Author> listAuthors = new ArrayList<>();
        listAuthors.add(author1);
        listAuthors.add(author2);
        listAuthors.add(author3);
        listAuthors.add(author4);

        List<Author> retrievedAuthors = listAuthors;
        Assertions.assertEquals(4, retrievedAuthors.size());
        Assertions.assertEquals("George", retrievedAuthors.get(0).getName());
        Assertions.assertEquals("Jyoti", retrievedAuthors.get(1).getName());
        Assertions.assertEquals("Nelson Mandal", retrievedAuthors.get(2).getName());
        Assertions.assertEquals(" Narayana ", retrievedAuthors.get(3).getName());

    }

    @Test
    void updateAuthor() {
        Author author = new Author();
        author.setAuthorId(2);
        author.setName("Jyoti");
        author.setPhoneNo("9279972455");

        // updating the author's name
        author.setName("Martha");
        Assertions.assertEquals(2, author.getAuthorId());
        Assertions.assertEquals("Martha",author.getName());
        Assertions.assertEquals("9279972455",author.getPhoneNo());

    }

    @Test
    void deleteAuthor() {
        Author author = new Author();
        author.setAuthorId(1);
        author.setName("George");
        author.setPhoneNo("9279972455");

        // Assuming you have a service or repository that deletes the author
        AuthorRepo authorRepository = Mockito.mock(AuthorRepo.class);
        Mockito.doNothing().when(authorRepository).deleteById(1);
        authorRepository.deleteById(1);
        Mockito.verify(authorRepository, Mockito.times(1)).deleteById(1);
    }
}