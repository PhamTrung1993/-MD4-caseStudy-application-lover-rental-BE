package com.codegym.controller.user;

import com.codegym.model.User;
import com.codegym.service.user.IUserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserCRUDService userCRUDService;
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id){
        Optional<User> userOptional=userCRUDService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userCRUDService.save(user), HttpStatus.CREATED);
    }
    @RequestMapping(method =RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Optional<User> trainerOptional=userCRUDService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userCRUDService.delete(id);
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.NO_CONTENT);
    }
}
