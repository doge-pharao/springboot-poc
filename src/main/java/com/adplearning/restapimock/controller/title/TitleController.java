package com.adplearning.restapimock.controller.title;

import com.adplearning.restapimock.entity.Employee;
import com.adplearning.restapimock.entity.Title;
import com.adplearning.restapimock.error.ElementNotFound;
import com.adplearning.restapimock.repository.EmployeeRepository;
import com.adplearning.restapimock.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.*;

@RestController
@RequestMapping(value = "/employees")
public class TitleController {

    @Autowired
    TitleRepository titleRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeNumber}/titles")
    public List<Title> getAll(@PathVariable Integer employeeNumber) {
        List<Title> results;

        if ((results = titleRepository.findByIdEmployeeNumber(employeeNumber)) == null)
            results = Collections.emptyList();

        return results;
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/{employeeNumber}/titles", params = { "fromDate" })
    public List<Title> getByStartDateAndEndDate(@PathVariable Integer employeeNumber,
                            @RequestParam(value = "fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> startDate,
                            @RequestParam(value = "endDate",  required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> endDate) {
        List<Title> results = null;

        if (startDate.isPresent() || endDate.isPresent()) {
            results = titleRepository.findByDuration(employeeNumber,
                    startDate.get(),
                    (endDate.isPresent()) ? endDate.get() : Calendar.getInstance().getTime());
        } else
            results = titleRepository.findByIdEmployeeNumber(employeeNumber);

        if (results == null)
            results = Collections.<Title>emptyList();

        return results;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{employeeNumber}/titles/{title:[a-zA-Z 0-9]*}")
    public List<Title> getByTitle(@PathVariable Integer employeeNumber, @PathVariable String title) {
        List<Title> results = titleRepository.findByIdEmployeeNumberAndIdTitle(employeeNumber, title);

        if (results == null)
            results = Collections.<Title>emptyList();

        return results;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{employeeNumber}/titles")
    public Title.TitlePK save(@RequestBody Title newTitle) {
        Title titleModel = new Title();

        titleModel.getId().setEmployeeNumber(newTitle.getId().getEmployeeNumber());
        titleModel.getId().setStartDate(newTitle.getId().getStartDate());
        titleModel.getId().setTitle(newTitle.getId().getTitle());
        titleModel.setEndDate(newTitle.getEndDate());

        titleRepository.save(titleModel);

        return titleModel.getId();
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{employeeNumber}/titles}")
    public Title update(@PathVariable Integer employeeNumber, @RequestBody Title newTitle) {
        Title tmpTitle = titleRepository.findOne(newTitle.getId());

        tmpTitle.setEndDate(newTitle.getEndDate());
        titleRepository.save(tmpTitle);

        return tmpTitle;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{employeeNumber}/titles/{title:[a-zA-Z 0-9]*}")
    public String delete(@PathVariable Title.TitlePK id) throws ElementNotFound {
        Title req;

        if ((req = titleRepository.findOne(id)) == null)
            throw new ElementNotFound();

        titleRepository.delete(req);

        return "Title deleted";
    }

}
