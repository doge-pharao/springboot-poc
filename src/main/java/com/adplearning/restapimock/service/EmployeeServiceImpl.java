package com.adplearning.restapimock.service;

import com.adplearning.restapimock.entity.Employee;
import com.adplearning.restapimock.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aperez on 1/29/2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Employee> findAllByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageRequest = new PageRequest(pageNumber, pageSize);

        //Obtain search results by invoking the preferred repository method.
        Page<Employee> searchResultPage = repository.findAll(pageRequest);

        return searchResultPage;
    }

}
