package com.example.todoapplication.controller;

import com.example.todoapplication.domain.Task;
import com.example.todoapplication.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;
    @GetMapping
    public List<Task> getAllTask(){
        return taskRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Task> getTaskById(@PathVariable Long id){
        return taskRepository.findById(id);
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @PutMapping
    public ResponseEntity updateTask(@RequestParam("id") Long id, @RequestBody Task task) {
        if (id != task.getId()){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(taskRepository.save(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskRepository.deleteById(id);
    }

}
