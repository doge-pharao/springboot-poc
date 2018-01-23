package com.adplearning.restapimock.repository;

import com.adplearning.restapimock.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public interface TitleRepository extends CrudRepository<Title, Title.TitlePK> {
    @Override
    Title findOne(Title.TitlePK id);

    List<Title> findByIdEmployeeNumberAndIdTitle(Integer employeeNumber, String title);

    @Query("from Title t where t.id.employeeNumber = ?1 and " +
                                        "t.id.startDate >= ?2 and " +
                                        "t.endDate <= ?3" )
    List<Title> findByDuration(Integer employeeNumber, Date startDate, Date endDate);

    @Override
    void delete(Title deleted);
}
