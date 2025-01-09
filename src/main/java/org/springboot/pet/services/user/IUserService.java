package org.springboot.pet.services.user;

import org.springboot.pet.dto.user.GetUserDto;
import org.springboot.pet.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService extends UserDetailsService {
    GetUserDto loadUserByUserEmail(String userEmail) throws UsernameNotFoundException;
    User addUser(User user);
}
