package com.adplearning.restapimock.service;

import com.adplearning.restapimock.entity.Employee;
import org.springframework.data.domain.Page;

/**
 * Created by aperez on 1/29/2018.
 */
public interface EmployeeService {

    public Page<Employee> findAllByPage(Integer pageNumber, Integer pageSize);


}
