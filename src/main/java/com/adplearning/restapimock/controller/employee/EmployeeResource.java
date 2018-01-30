package com.adplearning.restapimock.controller.employee;

import com.adplearning.restapimock.controller.employee.EmployeeController;
import com.adplearning.restapimock.controller.title.TitleController;
import com.adplearning.restapimock.error.ElementNotFound;
import org.springframework.hateoas.ResourceSupport;
import com.adplearning.restapimock.entity.Employee;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Created by aperez on 1/25/2018.
 */

public class EmployeeResource extends ResourceSupport {

    private final Employee employee;

    public EmployeeResource(final Employee employee)  {
        this.employee = employee;
        add(linkTo(methodOn(TitleController.class).getAllTitles(employee.getEmployeeNumber())).withRel("titles"));
    }

}
