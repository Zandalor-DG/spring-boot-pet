package org.springboot.pet.services.user.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springboot.pet.dto.user.CreateUserDto;
import org.springboot.pet.dto.user.GetUserDto;
import org.springboot.pet.entity.User;
import org.springboot.pet.exception.DataGetException;
import org.springboot.pet.exception.DataRestrictionException;
import org.springboot.pet.mapper.UserMapper;
import org.springboot.pet.repository.UserRepository;
import org.springboot.pet.services.user.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private String userName;
    private String password;
    private List<GrantedAuthority> authorities;

//    public UserServiceImpl(User user) {
//        this.userName = user.getFirst_name();
//        this.password = user.getPassword();
//    }
//    private final PasswordEncoder passwordEncoder;


    @Override
    public GetUserDto getUser(Long id) throws DataGetException {
        User user = userRepository.findById(id).orElseThrow(() -> new DataGetException("Юзер"));
        return userMapper.mapEntityToDto(user);
    }

    @Override
    @Transactional
    public GetUserDto createUser(CreateUserDto createUserDto) throws DataRestrictionException {
        User user = new User(createUserDto.getEmail(), createUserDto.getPassword());
        User savedUser = userRepository.save(user);
        return userMapper.mapEntityToDto(savedUser);
    }
}
