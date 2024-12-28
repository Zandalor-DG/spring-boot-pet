package org.springboot.pet.services.user;

import org.springboot.pet.dto.user.CreateUserDto;
import org.springboot.pet.dto.user.GetUserDto;
import org.springboot.pet.entity.User;
import org.springboot.pet.exception.DataGetException;
import org.springboot.pet.exception.DataRestrictionException;

public interface UserService {
    GetUserDto getUser(Long id) throws DataGetException;

    GetUserDto createUser(CreateUserDto createUserDto) throws DataRestrictionException;
}
