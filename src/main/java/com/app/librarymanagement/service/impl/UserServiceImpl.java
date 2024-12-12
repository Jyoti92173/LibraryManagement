package com.app.librarymanagement.service.impl;
import com.app.librarymanagement.dto.UserDTO;
import com.app.librarymanagement.entity.Author;
import com.app.librarymanagement.entity.User;
import com.app.librarymanagement.exception.AuthorNotFoundException;
import com.app.librarymanagement.repo.UserRepo;
import com.app.librarymanagement.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User addUser(UserDTO userSaveDTO) {
        User user = new User(userSaveDTO.getName(), userSaveDTO.getEmail());
        return userRepo.save(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> Users = userRepo.findAll();
        System.out.println(Users);
        List<UserDTO> userDTOList= new ArrayList<>();
        Users.forEach(user -> userDTOList.add(new UserDTO(user.getId(), user.getName(),user.getEmail())));
        System.out.println(userDTOList);
        return userDTOList;
    }

    @Override
    public void updateAuthor(UserDTO userDTO) {
        if (userRepo.existsById(userDTO.getId())) {
            Optional<User> userEntity = userRepo.findById(userDTO.getId());
            if(userEntity.isPresent()) {
                var user = userEntity.get();
                user.setName(userDTO.getName());
                userRepo.save(user);
                logger.info("User details saved for id={}", user.getId());
            }
        } else {
            logger.info("User Not Exist!");
        }
    }

    @Override
    public void deleteUser(int id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()) {
            userRepo.deleteById(id);
            logger.info("User with ID={} deleted successfully.", id);
        } else {
            throw new AuthorNotFoundException("UserId" + id + "Does not exist.");
        }
    }
}
