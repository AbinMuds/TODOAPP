package com.example.todoapplication.controller;

import com.example.todoapplication.domain.Task;
import com.example.todoapplication.domain.User;
import com.example.todoapplication.repository.TaskRepository;
import com.example.todoapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @GetMapping
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestParam Long id, @RequestBody User user){
        if( id != user.getId()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userRepository.save(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id){
        userRepository.deleteById(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getTaskByUserId(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            return taskRepository.findByUser(optionalUser.get());
        }
        return new ArrayList<Task>();
    }

}
