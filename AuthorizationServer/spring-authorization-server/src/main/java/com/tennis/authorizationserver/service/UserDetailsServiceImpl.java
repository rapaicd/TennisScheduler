package com.tennis.authorizationserver.service;

import com.tennis.authorizationserver.exception.BadRequestException;
import com.tennis.authorizationserver.exception.UserNotFoundException;
import com.tennis.authorizationserver.model.Role;
import com.tennis.authorizationserver.model.UserAccount;
import com.tennis.authorizationserver.repository.UserAccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.tennis.authorizationserver.config.DefaultSecurityConfig.passwordEncode;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;

    public UserDetailsServiceImpl(UserAccountRepository userAccountRepository, RoleService roleService) {
        this.userAccountRepository = userAccountRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository.findByEmail(email);
        if (Objects.isNull(userAccount)) {
            throw new UsernameNotFoundException("User not found.");
        }

        return userAccount;
    }

    public void register(String email, String password) {
        UserAccount userAccount = new UserAccount();
        userAccount.setEmail(email);
        userAccount.setPassword(password);
        userAccount.setEnabled(true);
        userAccount.setRole(getRole());
        userAccountRepository.save(userAccount);
    }

    private Role getRole() {
        Role role = roleService.findByRoleName("ROLE_user");
        if (role == null){
            role = Role.builder()
                    .roleName("ROLE_user")
                    .build();
            roleService.save(role);
        }

        return role;
    }

    public void changePassword(String oldPassword,String newPassword, String email) {
        UserAccount existingPerson = userAccountRepository.findByEmail(email);

        if (passwordEncode().matches(oldPassword, existingPerson.getPassword())) {
            existingPerson.setPassword(passwordEncode().encode(newPassword));
            userAccountRepository.save(existingPerson);
        }else
            throw new BadRequestException("The new password must be the same as the new password!");
    }

    public String getAdminEmail() {
        System.out.println("ISPISI");
        UserAccount admin = userAccountRepository.findAdminEmail();
        if (admin == null)
            throw new UserNotFoundException("Admin is not registered.");
        return admin.getEmail();
    }
}
