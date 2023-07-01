package org.vidhyaratha.employeeassetmanagement.service;

import org.vidhyaratha.employeeassetmanagement.model.Role;

import java.util.List;

public interface RoleService {

    public Role findRoleByName(String name);




    public void saveRole(Role role);
//    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(String empId);
}