package com.crio.stayease.services;


import com.crio.stayease.exceptions.HotelAlreadyExistsException;
import com.crio.stayease.exceptions.HotelNotFoundException;
import com.crio.stayease.models.Hotel;
import com.crio.stayease.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class HotelServiceImpl implements IHotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String addHotel(Hotel hotel) {
        if (hotelRepository.existsById(hotel.getId())) {
            throw new HotelAlreadyExistsException("Hotel with id " + hotel.getId() + " already exists.");
        }
        hotelRepository.save(hotel);
        return "Hotel added";
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new HotelNotFoundException("Hotel with id " + id + " not found."));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new HotelNotFoundException("Hotel with id " + id + " not found.");
        }
        hotelRepository.deleteById(id);
        return "Hotel deleted";
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public String updateHotel(Long id, Hotel hotel) {
        return hotelRepository.findById(id).map(existingHotel -> {
            existingHotel.setName(hotel.getName());
            existingHotel.setLocation(hotel.getLocation());
            existingHotel.setDescription(hotel.getDescription());
            existingHotel.setAvailableRooms(hotel.getAvailableRooms());
            hotelRepository.save(existingHotel);
            return "Hotel updated";
        }).orElseThrow(() -> new HotelNotFoundException("Hotel with id " + id + " not found."));
    }
}