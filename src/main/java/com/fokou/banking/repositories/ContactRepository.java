package com.fokou.banking.repositories;

import com.fokou.banking.dto.ContactDto;
import com.fokou.banking.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByUserId(Integer userId);
}
