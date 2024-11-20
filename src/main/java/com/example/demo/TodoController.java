package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class TodoController {
    private static final Logger logger = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping
    public List<Todo> getTodos() {
        logger.info("Get all !!!!!!!!!!!!");
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        logger.info("Create !!!!!!!!!!!!");
        return todoRepository.save(todo);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
        logger.info("Modify !!!!!!!!!!!!");
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            Todo todoToUpdate = todo.get();
            todoToUpdate.setTitle(todoDetails.getTitle());
            todoToUpdate.setCompleted(todoDetails.isCompleted());
            final Todo updatedTodo = todoRepository.save(todoToUpdate);
            return ResponseEntity.ok(updatedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deleteTodo(@PathVariable String id) {
        logger.info("Delete !!!!!!!!!!!!");
        logger.info("Delete !!");
        logger.info(id.toString());
        logger.info("Delete !!");
        long number = Long.parseLong(id);
        todoRepository.deleteById(number);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
