package com.example.application.controller;

import com.example.application.request.CreateUserRequest;
import com.example.application.request.UpdateUserRequest;
import com.example.domain.user.Address;
import com.example.domain.user.User;
import com.example.infrastructure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User user = new User();
        user.setAge(request.getAge());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        Address address = new Address();
        address.setAddressText(request.getAddressText());

        user.setAddress(address);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable("id") String id){
        return ResponseEntity.ok(userRepository.findById(UUID.fromString(id)));
    }

    @GetMapping({"/", ""})
    public ResponseEntity listUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(null);
    }
}
