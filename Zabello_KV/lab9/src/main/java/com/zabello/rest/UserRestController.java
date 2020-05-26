package com.zabello.rest;

import com.zabello.model.User;
import com.zabello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Optional<User> user = service.getByUserId(id);

        if (!user.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        HttpHeaders headers = new HttpHeaders();

        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.save(user);

        return new ResponseEntity<>(user, headers, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user) {

        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {

        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
