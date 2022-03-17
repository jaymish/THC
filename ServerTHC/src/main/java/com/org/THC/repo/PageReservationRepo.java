package com.org.THC.repo;

import com.org.THC.model.Location;
import com.org.THC.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PageReservationRepo extends PagingAndSortingRepository<Reservation, Long> {
    Page<Reservation> findByLocation_Id(String locationId, Pageable pageable);
}
