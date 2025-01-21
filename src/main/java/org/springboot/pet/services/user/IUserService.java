package org.springboot.pet.services.user;

import org.springboot.pet.dto.user.UpdateUserDto;
import org.springboot.pet.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    User createUser(User user);

    User updateUser(UpdateUserDto user, long id) throws Exception;

    User getUserById(long id) throws UsernameNotFoundException;

    void deleteUser(long id) throws Exception;
}
