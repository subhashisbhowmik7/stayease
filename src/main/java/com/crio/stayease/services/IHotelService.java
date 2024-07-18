package com.crio.stayease.services;

import java.util.List;

import com.crio.stayease.models.Hotel;

public interface IHotelService {
    String addHotel(Hotel hotel);
    List<Hotel> getHotels();
    Hotel getHotelById(Long id);
    String deleteHotel(Long id);
    String updateHotel(Long id, Hotel hotel);
}