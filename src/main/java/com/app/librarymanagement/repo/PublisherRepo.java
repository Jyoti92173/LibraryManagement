package com.app.librarymanagement.repo;

import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher,Integer> {
}
