package com.codegym.repository.user;

import com.codegym.model.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserCRUDRepository extends CrudRepository<User, Long> {
}
