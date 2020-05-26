package com.zabello.rest;

import com.zabello.model.User;
import com.zabello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersRestController {

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<User>> getUsers(){

        List<User> users = service.getAll();
        if (users.isEmpty())
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<User>> updateUsers(@RequestBody List<User> users){

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        service.updateAll(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<User>> deleteUsers(@RequestBody List<User> users){

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        service.deleteAll(users);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
