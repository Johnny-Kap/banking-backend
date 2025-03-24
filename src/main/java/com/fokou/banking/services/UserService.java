package com.fokou.banking.services;

import com.fokou.banking.dto.AuthenticationRequest;
import com.fokou.banking.dto.AuthenticationResponse;
import com.fokou.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto user);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
