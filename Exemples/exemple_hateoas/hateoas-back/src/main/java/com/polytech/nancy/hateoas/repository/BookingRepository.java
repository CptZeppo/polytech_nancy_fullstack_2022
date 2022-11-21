package com.polytech.nancy.hateoas.repository;

import com.polytech.nancy.hateoas.domain.Booking;
import com.polytech.nancy.hateoas.domain.Search;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BookingRepository {

    Map<UUID, Booking> bookings = new HashMap<>();

    public Booking save(Booking booking) {
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking findById(UUID id) {
        return bookings.get(id);
    }
}
