package com.adplearning.restapimock.controller;

import com.adplearning.restapimock.model.Employee;
import com.adplearning.restapimock.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController

public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method=RequestMethod.GET, value="/employees")
    public Iterable<Employee> requests() {
        return employeeRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, value="/employees")
    public Integer save(HttpServletRequest request) {
        Employee empModel = new Employee();
        employeeRepository.save(empModel);

        return empModel.getEmployeeNumber();
    }

    @RequestMapping(method=RequestMethod.GET, value="/employees/{id}")
    public Employee show(@PathVariable Integer id) {
        return employeeRepository.findOne(id);
    }

    @RequestMapping(method=RequestMethod.PUT, value="/employees/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee incomingReq) {
        Employee req = employeeRepository.findOne(id);
        employeeRepository.save(req);

        return req;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/employees/{id}")
    public String delete(@PathVariable Integer id) {
        Employee req = employeeRepository.findOne(id);
        employeeRepository.delete(req);

        return "request deleted";
    }
}