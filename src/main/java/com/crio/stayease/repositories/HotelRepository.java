package com.crio.stayease.repositories;

import com.crio.stayease.models.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HotelRepository extends JpaRepository<Hotel,Long> {
}