package com.org.THC.repo;

import com.org.THC.model.Location;
import com.org.THC.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageLocationRepo extends PagingAndSortingRepository<Location, Long> {
    Page<Location> findByStatus(String status, Pageable pageable);
}
