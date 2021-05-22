package com.springboot.mongodb.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mongodb.domain.TodoDTO;
import com.springboot.mongodb.repository.TodoRepository;

@RestController
public class TodoController {

	@Autowired
	private TodoRepository todoRepo;

	@GetMapping("/todos")
	public ResponseEntity<?> getAllTodo() {

		List<TodoDTO> todos = todoRepo.findAll();

		if (todos.size() > 0) {
			return new ResponseEntity<List<TodoDTO>>(todos, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No todo are available", HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/todos")
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo) {
		try {
			todo.setCreatedAt(new Date(System.currentTimeMillis()));
			todoRepo.save(todo);
			return new ResponseEntity<TodoDTO>(todo, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
