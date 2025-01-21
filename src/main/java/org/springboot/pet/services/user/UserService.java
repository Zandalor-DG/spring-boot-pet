package org.springboot.pet.services.user;

import lombok.RequiredArgsConstructor;
import org.springboot.pet.dto.user.UpdateUserDto;
import org.springboot.pet.entity.User;
import org.springboot.pet.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public User createUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UpdateUserDto user, long id) throws Exception {
        User searchUser = userRepository.findById(id).orElseThrow(() -> new Exception("No dara FOUND"));

        searchUser.setFirstName(user.getNewFirstName());
        searchUser.setSecondName(user.getNewSecondName());
        searchUser.setPassword(encoder.encode(user.getNewPassword()));

        return userRepository.save(searchUser);
    }

    @Override
    public User getUserById(long id) throws UsernameNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("User not found: " + String.valueOf(id)));
    }

    @Override
    public void deleteUser(long id) throws Exception {
        userRepository.deleteById(getUserById(id).getId());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(username);

            return new UserInfoDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }
}
