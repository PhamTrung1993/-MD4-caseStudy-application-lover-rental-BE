package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.model.UserPrinciple;
import com.codegym.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements com.codegym.service.user.IUserService, UserDetailsService {

    @Autowired
    private IUserRepository userRepository;


    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User save(User User) {
        return userRepository.save(User);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(username);
        if (userOptional.isPresent()){
            return UserPrinciple.build(userOptional.get());
        }
        return null;
    }
    @Override
    public User findByUserName(String username) {
        User user = userRepository.findByUserName(username).get();
        return user;
    }
}
