package com.fokou.banking.controllers;


import com.fokou.banking.dto.AddressDto;
import com.fokou.banking.dto.ContactDto;
import com.fokou.banking.services.ContactService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
@RequiredArgsConstructor
@Tag(name = "contact")
public class ContactController {

    private final ContactService service;

    @PostMapping("/")
    public ResponseEntity<Integer> save(@RequestBody ContactDto contactDto){
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ContactDto>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{contact-id}")
    public ResponseEntity<ContactDto> findById(@PathVariable("contact-id") Integer contactId){
        return ResponseEntity.ok(service.findById(contactId));
    }

    @GetMapping("/users/{user-id}")
    public ResponseEntity<List<ContactDto>> findAllByUserId(@PathVariable("user-id") Integer id){
        return ResponseEntity.ok(service.findAllByUserId(id));
    }

    @DeleteMapping("/{contact-id}")
    public ResponseEntity<Void> delete(@PathVariable("contact-id") Integer contactId){
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }
}
