package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public void add(User user);
    public List<User> getAllUsers();
    public User getUser(Long id);
    public void deleteUser(Long id);
    public void editUser(User user);
}
