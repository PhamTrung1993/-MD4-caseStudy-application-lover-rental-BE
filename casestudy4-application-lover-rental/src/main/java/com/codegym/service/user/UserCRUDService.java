package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.repository.user.IUserCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCRUDService implements IUserCRUDService {
    @Autowired
    IUserCRUDRepository userCRUDRepository;
    @Override
    public Iterable<User> findAll() {
        return null;
    }
    @Override
    public Optional<User> findById(Long id) {
            return userCRUDRepository.findById(id);
        }
    @Override
    public User save(User model) {
        return userCRUDRepository.save(model);
    }
    @Override
    public void delete(Long id) {
        userCRUDRepository.deleteById(id);
    }
}
