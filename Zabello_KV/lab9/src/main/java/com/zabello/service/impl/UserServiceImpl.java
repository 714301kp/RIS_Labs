package com.zabello.service.impl;

import com.zabello.model.User;
import com.zabello.repository.UserRepository;
import com.zabello.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public Optional<User> getByUserId(Long id) {
        return repository.findById(id);
    }

    @Override
    public void save(User user) {
        repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public void updateAll(List<User> users) {
       repository.saveAll(users);
    }

    @Override
    public void deleteAll(List<User> users) {
        repository.deleteAll(users);
    }
}
