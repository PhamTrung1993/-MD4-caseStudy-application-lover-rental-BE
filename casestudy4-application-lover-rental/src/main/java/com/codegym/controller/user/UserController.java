package com.codegym.controller.user;

<<<<<<< HEAD
import com.codegym.model.DTO.UserDTO;
import com.codegym.model.Role;
=======
import com.codegym.model.Provider;
import com.codegym.model.ProviderForm;
>>>>>>> 5abd48f0145f8208bb26ca7bbb03cc0a1467b1af
import com.codegym.model.User;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserCRUDService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IRoleService roleService;

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserCRUDService userCRUDService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<User> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userCRUDService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

<<<<<<< HEAD
    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setVip(userDTO.getVip());
        Role role = roleService.findById(userDTO.getRoleId()).get();
        user.setRole(role);
        return new ResponseEntity<>(userCRUDService.save(user), HttpStatus.CREATED);
    }
    @DeleteMapping( "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        Optional<User> trainerOptional=userService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.NO_CONTENT);
=======
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userCRUDService.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> trainerOptional = userCRUDService.findById(id);
        if (!trainerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userCRUDService.delete(id);
        return new ResponseEntity<>(trainerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userCRUDService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(id);
        userCRUDService.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
>>>>>>> 5abd48f0145f8208bb26ca7bbb03cc0a1467b1af
    }
    @GetMapping("/lists")
    public ResponseEntity<Iterable<User>> getAllUser(){
        Iterable<User> users = userService.findAll();
        return  new ResponseEntity<>(users,HttpStatus.OK);
    }
}
