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
        logger.info("Come on!!!");
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todoDetails) {
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

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteTodo(@PathVariable Long id) {
        todoRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
