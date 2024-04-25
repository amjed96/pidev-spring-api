package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.dto.AddUserDTO;
import tn.esprit.pidev.dto.GetUserDTO;
import tn.esprit.pidev.model.UserEntity;
import tn.esprit.pidev.service.UserDetailsServiceImpl;

@RestController
public class UserController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    // GET
    @GetMapping("/user/{id}")
    public GetUserDTO getUser(@PathVariable("id") final Long id) {
        return userDetailsService.getUser(id);
    }
    // POST
    @PostMapping("/user/add")
    public GetUserDTO addUser(@RequestBody AddUserDTO userDTO) {
        return userDetailsService.addUser(userDTO);
    }
    // UPDATE
    @PutMapping("/user/update/{id}")
    public GetUserDTO updateUser(@RequestBody UserEntity userEntity, @PathVariable("id") final Long id) {
        return userDetailsService.updateUser(userEntity,id);
    }
    // DELETE
    @DeleteMapping("/user/delete{id}")
    public void deleteUser(@PathVariable("id") final Long id) {
        userDetailsService.deleteUser(id);
    }
}
