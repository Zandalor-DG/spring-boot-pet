package org.springboot.pet.services.user;

import lombok.RequiredArgsConstructor;
import org.springboot.pet.dto.user.GetUserDto;
import org.springboot.pet.entity.User;
import org.springboot.pet.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public GetUserDto loadUserByUserEmail(String userEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(userEmail);

        return user.map(GetUserDto::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + userEmail));
    }

    @Override
    public User addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        return user.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
