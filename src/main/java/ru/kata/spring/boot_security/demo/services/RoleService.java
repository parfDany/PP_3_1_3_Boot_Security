package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;
@Service
public interface RoleService {
    List<Role> getAllRole();
}
