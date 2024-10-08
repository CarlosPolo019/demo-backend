package com.backendprueba.demo.repository;

import com.backendprueba.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.backendprueba.demo.exception.EmailAlreadyExistsException;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
        @Query("SELECT u FROM User u WHERE " +
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) OR " +
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :firstName, '%')) OR " +
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :firstName, '%'))")
    Page<User> findByFirstNameContaining(String firstName, Pageable pageable);
    

    boolean existsByEmail(String email);

}