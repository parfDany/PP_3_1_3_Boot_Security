package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.entities.Role;

import java.util.List;
@Service
public interface RoleService {

    public void add(Role role);
    public List<Role> getAllRole();
    public Role getRole(Long id);
    public void deleteRole(Long id);
    public void editRole(Role role);
}
