package org.vidhyaratha.employeeassetmanagement.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vidhyaratha.employeeassetmanagement.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    public Role findRoleByName(String name);

    @Query(value = "select * from role where role.id= (select roleId from employees_roles where empId = :empId)", nativeQuery = true)
    public List<Role> findRoleByEmployee(@Param("empId") String empId);
}