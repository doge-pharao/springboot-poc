package com.adplearning.restapimock.controller.employee;

import com.adplearning.restapimock.controller.title.TitleController;
import com.adplearning.restapimock.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by aperez on 1/29/2018.
 */
@Component
public class EmployeeResourceAssembler extends ResourceAssemblerSupport<Employee, Resource> {

    public EmployeeResourceAssembler() {
        super(EmployeeResourceAssembler.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends Employee> employees) {
        List<Resource> resources = new ArrayList<Resource>();
        for(Employee emp : employees) {
            Resource res = new Resource<Employee>(emp, linkTo(EmployeeController.class).slash(emp.getEmployeeNumber()).withSelfRel());
            res.add(linkTo(methodOn(TitleController.class).getAllTitles(emp.getEmployeeNumber())).withRel("titles"));

            resources.add(res);
        }
        return resources;
    }

    @Override
    public Resource toResource(Employee emp) {
        Resource res = new Resource<Employee>(emp, linkTo(EmployeeController.class).slash(emp.getEmployeeNumber()).withSelfRel());
        res.add(linkTo(methodOn(TitleController.class).getAllTitles(emp.getEmployeeNumber())).withRel("titles"));

        return res;
    }
}