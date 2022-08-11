package com.java.restapidemo.controller;


import com.java.restapidemo.entity.User;
import com.java.restapidemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String sayHello(){
        return "Say Hello!!";
    }

    @RequestMapping(value = "/all/users", method = RequestMethod.GET)
    public List<User> getAllUser(){
//        List<User> userList = new ArrayList();
//        User user1 = new User();
//        user1.setName("User 1");
//        user1.setAge(10);
//
//        User user2 = new User();
//        user2.setName("User 2");
//        user2.setAge(20);
//
//        userList.add(user1);
//        userList.add(user2);
//        return userList;
        return  userRepository.findAll();
    }

    @PostMapping("/add/user")
    public void addUser(@RequestBody User user){
        userRepository.save(user);
    }

    @PostMapping("/remove/user")
    public void removeUser(@RequestBody User user){
//        userRepository.delete(user);
        userRepository.deleteById(user.getId());
    }

    @PatchMapping("/update/user")
    public void updateUser(@RequestBody User user){
        Optional<User> updateUser = userRepository.findById(user.getId());
        updateUser.get().setAge(user.getAge());
        updateUser.get().setName(user.getName());
        userRepository.save(updateUser.get());
    }
}
