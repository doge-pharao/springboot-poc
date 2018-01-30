package com.adplearning.restapimock.controller.employee;

import com.adplearning.restapimock.controller.title.TitleController;
import com.adplearning.restapimock.dao.EmployeeDao;
import com.adplearning.restapimock.error.ElementNotFound;
import com.adplearning.restapimock.entity.Employee;
import com.adplearning.restapimock.entity.Title;
import com.adplearning.restapimock.repository.EmployeeRepository;
import com.adplearning.restapimock.repository.TitleRepository;
import com.adplearning.restapimock.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    TitleRepository titleRepository;
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeResourceAssembler widgetResourceAssembler;
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(method=RequestMethod.GET, value="/", produces = {"application/json"})
    public PagedResources<Employee> showAll(Pageable pageable, PagedResourcesAssembler assembler) throws ElementNotFound {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return assembler.toResource(employees, widgetResourceAssembler);
    }

    @RequestMapping(method= RequestMethod.POST, value="/")
    public Integer save(@RequestBody Employee newEmployee) {
        employeeRepository.save(newEmployee);
        return newEmployee.getEmployeeNumber();
    }

    @RequestMapping(method= RequestMethod.PUT, value="/employees/{id}")
    public Integer update(@RequestBody Employee updatedEmployee, @PathVariable Integer id) throws ElementNotFound {
        Employee result;

        if (employeeRepository.findOne(id)== null)
            throw new ElementNotFound();

        employeeDao.update(id, updatedEmployee);

        return updatedEmployee.getEmployeeNumber();
    }

    @RequestMapping(method=RequestMethod.GET, value="/{id}")
    public EmployeeResource showById(@PathVariable Integer id) throws ElementNotFound {
        EmployeeResource result;
        Employee employee = employeeRepository.findOne(id);

        if (employee != null)
            throw new ElementNotFound();
        else
            result = new EmployeeResource(employee);

        List<Title> methodLinkBuilder =
                methodOn(TitleController.class).getAllTitles(id);
        Link titlesLink = linkTo(methodLinkBuilder).withRel("titles");

        result.add(titlesLink);

        return result;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/employees/{id}")
    public String delete(@PathVariable Integer id) {
        Employee req = employeeRepository.findOne(id);
        employeeRepository.delete(req);

        return "request deleted";
    }
}