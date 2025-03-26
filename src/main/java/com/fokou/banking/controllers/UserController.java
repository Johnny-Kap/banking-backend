package com.fokou.banking.controllers;


import com.fokou.banking.dto.UserDto;
import com.fokou.banking.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {

   private final UserService service;

   @PostMapping("/")
   public ResponseEntity<Integer> save(
           @RequestBody UserDto userDto
   ){
       return ResponseEntity.ok(service.save(userDto));
   }

   @GetMapping("/")
   public ResponseEntity<List<UserDto>> findAll(){
       return ResponseEntity.ok(service.findAll());
   }

   @GetMapping("/{user-id}")
   public ResponseEntity<UserDto> findById(@PathVariable("user-id") Integer id){
       return ResponseEntity.ok(service.findById(id));
   }

   @PatchMapping("/validate/{user-id}")
   public ResponseEntity<Integer> validateAccount(@PathVariable("user-id") Integer userId){
       return ResponseEntity.ok(service.validateAccount(userId));
   }

    @PatchMapping("/invalidate/{user-id}")
    public ResponseEntity<Integer> invalidateAccount(@PathVariable("user-id") Integer id){
        return ResponseEntity.ok(service.invalidateAccount(id));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") Integer id){
       service.delete(id);
       return ResponseEntity.accepted().build();
    }

}
