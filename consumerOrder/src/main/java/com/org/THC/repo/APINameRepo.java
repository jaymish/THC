package com.org.THC.repo;

import com.org.THC.model.APIExecutionTime;
import com.org.THC.model.APIName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface APINameRepo extends JpaRepository<APIName, Integer> {
    public APIName findByName(String apiName);

}
