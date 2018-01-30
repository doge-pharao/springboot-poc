package com.adplearning.restapimock.entity;

import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    /**
     * create table seq_store (
     *      seq_name varchar(255) not null,
     *      seq_value int not null,
     *      primary key (seq_name));
     * */

    @Id
    @TableGenerator(
            name = "EmployeeSeqStore",
            table = "seq_store",
            pkColumnName = "seq_name",
            pkColumnValue = "emp_no",
            valueColumnName = "seq_value",
            initialValue = 1,
            allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "EmployeeSeqStore" )
    @Column(name = "emp_no")
    private Integer employeeNumber;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private Character gender;

    @Column(name = "hire_date")
    private Date hireDate;

    public Employee() {
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(Integer employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

}
