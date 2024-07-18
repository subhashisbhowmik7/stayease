package com.crio.stayease.repositories;

import com.crio.stayease.models.Booking;
import com.crio.stayease.models.Hotel;
import com.crio.stayease.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findByHotel(Hotel hotel);
    boolean existsByUserAndHotel(User user, Hotel hotel);
}
