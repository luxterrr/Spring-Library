package com.kaizen.Library.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel>getAll() {return userService.getAll();}

    @PostMapping("/{id}")
    public UserModel create(@RequestBody UserModel userModel) {return userService.save(userModel);}

    @DeleteMapping
    public void delete (@PathVariable Long id) {userService.delete(id);}
}
