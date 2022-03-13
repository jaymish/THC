package com.org.THC.repo;

import com.org.THC.model.Location;
import com.org.THC.model.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PageMenuRepo extends PagingAndSortingRepository<Menu, Long> {
    Page<Menu> findMenuByLocation_Id(String locationId, Pageable pageable);
    Page<Menu> findByStatusAndLocation_Id(String status,String locationId, Pageable pageable);
}
