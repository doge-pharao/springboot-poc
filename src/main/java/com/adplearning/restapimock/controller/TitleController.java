package com.adplearning.restapimock.controller;

import com.adplearning.restapimock.error.ElementNotFound;
import com.adplearning.restapimock.error.ErrorResponse;
import com.adplearning.restapimock.model.Employee;
import com.adplearning.restapimock.model.Title;
import com.adplearning.restapimock.repository.EmployeeRepository;
import com.adplearning.restapimock.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class TitleController {

    @Autowired
    TitleRepository titleRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method=RequestMethod.GET, value="/employees/{employeeNumber}/titles")
    public Iterator<Title> show(@PathVariable Integer employeeNumber) throws ElementNotFound {
        Employee employee;
        Iterator<Title> results;

        if (null != (employee = employeeRepository.findOne(employeeNumber)))
            results = employee.getTitles().iterator();
        else
            throw new ElementNotFound();

        return results;
    }

    @RequestMapping(method=RequestMethod.GET, value="/employees/{employeeNumber}/titles/{title:[a-zA-Z 0-9]*}")
    public List<Title> show(@PathVariable("employeeNumber") Integer employeeNumber, @PathVariable("title") String title) {
        List<Title> results = titleRepository.findByIdEmployeeNumberAndIdTitle(employeeNumber, title);

        if (results == null)
            results = Collections.<Title>emptyList();

        return results;
    }

    @RequestMapping(method=RequestMethod.GET,
            value= "/employees/{employeeNumber}/titles", params = { "fromDate" })
    public List<Title> show(@PathVariable("employeeNumber") Integer employeeNumber,
                                @RequestParam("fromDate") @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Optional<Date> endDate) throws ElementNotFound {
        List<Title> results = null;

        results = titleRepository.findByDuration(employeeNumber, startDate,
                (endDate.isPresent())?endDate.get():Calendar.getInstance().getTime());

        if (results == null)
            results = Collections.<Title>emptyList();

        return results;
    }

    @RequestMapping(method=RequestMethod.POST, value="/titles")
    public Title.TitlePK save(HttpServletRequest request) {
        Title titleModel = new Title();
        titleRepository.save(titleModel);

        return titleModel.getId();
    }

    @RequestMapping(method=RequestMethod.PUT, value="/titles/{id}")
    public Title update(@PathVariable Title.TitlePK id, @RequestBody Title incomingReq) {
        Title tmpTitle = titleRepository.findOne(id);

        tmpTitle.setEndDate(incomingReq.getEndDate());
        titleRepository.save(tmpTitle);

        return tmpTitle;
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/titles/{id}")
    public String delete(@PathVariable Title.TitlePK id) {
        Title req = titleRepository.findOne(id);
        titleRepository.delete(req);

        return "request deleted";
    }
}