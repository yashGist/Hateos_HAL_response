package com.controller;
//import this static line manually
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.bean.Users;
import com.dao.UserDaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping("/users")
    public List<Users> findAllUsers() {
        return userDaoService.getUsers();
    }

    @GetMapping("/user/{id}")
    public EntityModel<Users> findOnerUser(@PathVariable int id) {
        EntityModel<Users> entityModel = EntityModel.of(userDaoService.getUser(id));
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findOnerUser(id));
        entityModel.add(linkTo.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/user")
    public ResponseEntity<Users> createUser(@Valid @RequestBody Users user) {
        Users savedUser = userDaoService.saveUsers(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").
                buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userDaoService.deleteById(id);

    }
}