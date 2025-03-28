package com.fokou.banking.services;

import com.fokou.banking.dto.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{

    List<ContactDto> findAllByUserId(Integer userId);
}
