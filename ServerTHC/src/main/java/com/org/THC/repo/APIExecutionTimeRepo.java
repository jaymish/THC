package com.org.THC.repo;

import com.org.THC.model.APIExecutionTime;
import com.org.THC.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APIExecutionTimeRepo extends JpaRepository<APIExecutionTime, Integer> {
    Page<APIExecutionTime> findByApiName_Name(String apiName, Pageable pageable);
    Page<APIExecutionTime> findByExecutionDate(String date,Pageable pageable);
    Page<APIExecutionTime> findByExecutionDateAndApiName_Name(String date, String apiName, Pageable pageable);
}
