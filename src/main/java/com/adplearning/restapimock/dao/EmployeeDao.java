package com.adplearning.restapimock.dao;

import com.adplearning.restapimock.entity.Employee;

/**
 * Created by aperez on 1/25/2018.
 */
public interface EmployeeDao {

    void update (Integer id, Employee employee);
}
