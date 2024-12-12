package com.app.librarymanagement.service;

import com.app.librarymanagement.dto.UserDTO;
import com.app.librarymanagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {

    User addUser(UserDTO userSaveDTO);

    List<UserDTO> getAllUser();

    void updateAuthor(UserDTO userDTO);

    void deleteUser(int id);
}
