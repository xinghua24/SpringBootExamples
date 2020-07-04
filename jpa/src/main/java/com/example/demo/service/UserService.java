package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    public List<User> findAll() {
        return userRepo.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    public List<User> findByDateRange(LocalDate start, LocalDate end) {
        return userRepo.findByDateRange(start, end);
    }

    @Transactional(rollbackOn = IllegalArgumentException.class)
    public void testTransaction() {
        User user = userRepo.findById(1L).orElse(null);
        user.setUsername("Name shouldn't be changed!!!!");
        userRepo.save(user);
        throw new IllegalArgumentException("ILLEGAL OPERATION");
    }
}
