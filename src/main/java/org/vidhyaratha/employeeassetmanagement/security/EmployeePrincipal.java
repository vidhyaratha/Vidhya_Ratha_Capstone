package org.vidhyaratha.employeeassetmanagement.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.vidhyaratha.employeeassetmanagement.model.Employee;
import org.vidhyaratha.employeeassetmanagement.model.Role;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeePrincipal implements UserDetails {

    private Employee employee;
    private List<Role> roles;

    public EmployeePrincipal(Employee employee, List<Role> roles) {
        super();
        this.employee = employee;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

//    @Override
//    public List<GrantedAuthority> getAuthorities()
//    {
//        return AuthorityUtils.createAuthorityList("VALID_EMPLOYEE_ROLE");
//    }

    @Override
    public String getPassword() {
        return this.employee.getPassword();
    }

    @Override
    public String getUsername() {
        return this.employee.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
