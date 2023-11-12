package com.tennis.authorizationserver.service;

import com.tennis.authorizationserver.model.Role;
import com.tennis.authorizationserver.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public Role findByRoleName(String role) {
        return roleRepository.findByRoleName(role);
    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
