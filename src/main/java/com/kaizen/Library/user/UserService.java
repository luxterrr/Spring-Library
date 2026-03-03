package com.kaizen.Library.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserModel> getAll() {return userRepository.findAll();}
    public UserModel save(UserModel userModel) {return userRepository.save(userModel);}
    public void delete(Long id) {userRepository.deleteById(id);}
}
