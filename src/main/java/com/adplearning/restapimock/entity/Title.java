package com.adplearning.restapimock.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "titles")
public class Title implements Serializable {

    @EmbeddedId
    private TitlePK id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "to_date", nullable = false)
    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TitlePK getId() {
        return id;
    }

    @Embeddable
    public static class TitlePK implements Serializable {

        @Column(name = "emp_no", nullable = false)
        private Integer employeeNumber;

        @Column(name = "title", nullable = false)
        private String title;

        @JsonFormat(pattern="yyyy-MM-dd")
        @Column(name = "from_date", nullable = false)
        private Date startDate;

        public Integer getEmployeeNumber() {
            return employeeNumber;
        }

        public void setEmployeeNumber(Integer employeeNumber) {
            this.employeeNumber = employeeNumber;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }
    }
}
