package com.kaizen.Library.services;

import com.kaizen.Library.DTOS.UserDTO;
import com.kaizen.Library.domains.user.Status;
import com.kaizen.Library.repositories.UserRepository;
import com.kaizen.Library.domains.user.User;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean validateUser(User client) {
        if (client.getStatus() == Status.INACTIVE) {
            return false;
        }else return true;

        //if (item.getQuantity() <= 0){
         //   throw new Exception("QUANTIDADE DO LIVRO EM FALTA");
        //}
    }

    public User createUser(UserDTO client){
        User newUser = new User(client);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {return userRepository.findAll();}
    public void saveUser(User user) {this.userRepository.save(user);}

    public User findUserbyId(Long id) throws Exception {
        return this.userRepository.findUserById(id).orElseThrow(() -> new RuntimeException("CLIENT NOT FOUND"));
    }
}
