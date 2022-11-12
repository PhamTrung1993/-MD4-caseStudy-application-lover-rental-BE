package com.codegym.service.avatar;


import com.codegym.model.Avatar;
import com.codegym.repository.avatar.IAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AvatarService implements IAvatarService{

    @Autowired
    IAvatarRepository avatarRepository;

    @Override
    public Iterable<Avatar> findAll() {
        return avatarRepository.findAll();
    }

    @Override
    public Optional<Avatar> findById(Long id) {
        return avatarRepository.findById(id);
    }

    @Override
    public Avatar save(Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    @Override
    public void delete(Long id) {
        avatarRepository.deleteById(id);
    }

    @Override
    public Optional<Avatar> findByProvider_id(Long id) {
        return avatarRepository.findByProvider_Id(id);
    }
}
