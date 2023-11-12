package com.tennis.authorizationserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String roleName;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY,cascade =  CascadeType.PERSIST)
    private List<UserAccount> persons;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
