package org.springboot.pet.services;

import org.springboot.pet.dto.UserDto;
import org.springboot.pet.exception.DataGetException;
import org.springboot.pet.exception.DataRestrictionException;

public interface UserService {
    UserDto findUser(long id) throws DataGetException;

    UserDto updateUser(UserDto userDto) throws DataGetException;

    String createUser(UserDto userDto) throws DataRestrictionException;
}
