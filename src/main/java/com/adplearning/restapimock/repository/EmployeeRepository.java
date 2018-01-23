package com.adplearning.restapimock.repository;

import com.adplearning.restapimock.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Override
    Employee findOne(Integer id);

    @Override
    void delete(Employee deleted);
}
