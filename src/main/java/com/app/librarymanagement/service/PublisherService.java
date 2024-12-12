package com.app.librarymanagement.service;

import com.app.librarymanagement.dto.PublisherDTO;
import com.app.librarymanagement.entity.Publisher;

import java.util.List;

public interface PublisherService {

    List<PublisherDTO> getAllPublisher();

    Publisher addPublisher(PublisherDTO publisherSaveDTO);

    void updatePublisher(PublisherDTO publisherDTO);

    void deletePublisher(int id);
}
