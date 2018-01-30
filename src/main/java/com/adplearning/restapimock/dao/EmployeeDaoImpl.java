package com.adplearning.restapimock.dao;

import com.adplearning.restapimock.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by aperez on 1/25/2018.
 */
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void update(Integer id, Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        Employee outdatedEmployee = session.byId(Employee.class).load(id);

        outdatedEmployee.setBirthDate(employee.getBirthDate());
        outdatedEmployee.setFirstName(employee.getFirstName());
        outdatedEmployee.setLastName(employee.getLastName());
        outdatedEmployee.setGender(employee.getGender());
        outdatedEmployee.setHireDate(employee.getHireDate());

        session.flush();
    }
}
