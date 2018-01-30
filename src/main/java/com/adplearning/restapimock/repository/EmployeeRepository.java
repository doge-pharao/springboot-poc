package com.adplearning.restapimock.repository;

import com.adplearning.restapimock.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    Employee findOne(Integer id);

    @Override
    Page<Employee> findAll(Pageable pageRequest);

    @Override
    void delete(Employee deleted);

}
