package com.app.librarymanagement.service.impl;

import com.app.librarymanagement.dto.PublisherDTO;

import com.app.librarymanagement.entity.Publisher;

import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.repo.PublisherRepo;
import com.app.librarymanagement.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepo publisherRepo;

    private static final Logger logger = LoggerFactory.getLogger(PublisherServiceImpl.class);

    @Override
    public Publisher addPublisher(PublisherDTO publisherSaveDTO) {

        Publisher publisher = new Publisher(publisherSaveDTO.getName());
        return publisherRepo.save(publisher);

    }

    @Override
    public List<PublisherDTO> getAllPublisher() {
        List<Publisher> getPublishers = publisherRepo.findAll();
        System.out.println(getPublishers);
        List<PublisherDTO> publisherDTOList = new ArrayList<>();
        getPublishers.forEach(publisher -> publisherDTOList.add(new PublisherDTO(publisher.getPublisherId(), publisher.getName())));
        System.out.println(publisherDTOList);
        return publisherDTOList;
    }
    @Override
    public void updatePublisher(PublisherDTO publisherDTO) {
        if (publisherRepo.existsById(publisherDTO.getPublisherId())) {
            Optional<Publisher> publisherEntity = publisherRepo.findById(publisherDTO.getPublisherId());
            if(publisherEntity.isPresent()) {
                var publisher = publisherEntity.get();
                publisher.setName(publisherDTO.getName());
                publisherRepo.save(publisher);
                logger.info("Publisher details saved for id={}", publisher.getPublisherId());
            }
        } else {
            logger.info("Publisher Not Exist!");
        }
    }

    @Override
    public void deletePublisher(int id) {
        Optional<Publisher> publisherOptional = publisherRepo.findById(id);
        if (publisherOptional.isPresent()) {
            publisherRepo.deleteById(id);
            logger.info("Publisher with ID={} deleted successfully.", id);
        } else {
            throw new AuthorNotFoundException("PublisherId" + id + "Does not exist.");
        }
    }
}

