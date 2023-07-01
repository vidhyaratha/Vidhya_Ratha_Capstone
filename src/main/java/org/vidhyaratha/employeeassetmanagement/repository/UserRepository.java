package org.vidhyaratha.employeeassetmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "Select * from user where email = :email", nativeQuery = true)
    public User findUserByEmail(String email);

    public User findUserByEmpId(String empId);

}
