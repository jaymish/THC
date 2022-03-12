package com.org.THC.repo;

import com.org.THC.model.Location;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageLocationRepo extends PagingAndSortingRepository<Location, Long> {
}
