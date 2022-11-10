package com.codegym.service.role;

import com.codegym.model.Role;
import com.codegym.repository.role.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role Role) {
        return roleRepository.save(Role);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
