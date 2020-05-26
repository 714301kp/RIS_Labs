package com.zabello.service;

import com.zabello.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> getByUserId(Long id);

    void save(User user);

    void delete(Long id);

    List<User> getAll();

    void updateAll(List<User> users);

    void deleteAll(List<User> users);
}
