package com.org.THC.repo;

import com.org.THC.model.Location;
import com.org.THC.model.OpenHours;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageOpenHoursRepo extends PagingAndSortingRepository<OpenHours, Long> {
    Page<OpenHours> findByLocation_Id(String locationId, Pageable pageable);
}
