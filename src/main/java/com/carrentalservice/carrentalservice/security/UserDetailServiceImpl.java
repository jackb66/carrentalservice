package com.carrentalservice.carrentalservice.security;

import com.carrentalservice.carrentalservice.entities.Employee;

import com.carrentalservice.carrentalservice.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public UserDetailServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = employeeRepository.findByUsername(username).orElseThrow();
        return new UserDetailsImpl(user);

    }
}
