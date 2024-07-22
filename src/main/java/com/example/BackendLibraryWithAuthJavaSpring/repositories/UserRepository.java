package com.example.BackendLibraryWithAuthJavaSpring.repositories;

import com.example.BackendLibraryWithAuthJavaSpring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByLogin(String login);
    User findUserRoleByLogin(String login);
}
