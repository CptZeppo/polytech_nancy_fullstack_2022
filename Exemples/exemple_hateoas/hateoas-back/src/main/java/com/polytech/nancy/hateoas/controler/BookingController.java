package com.polytech.nancy.hateoas.controler;

import com.polytech.nancy.hateoas.domain.Booking;
import com.polytech.nancy.hateoas.domain.Search;
import com.polytech.nancy.hateoas.domain.dto.BookingDto;
import com.polytech.nancy.hateoas.domain.dto.SearchDto;
import com.polytech.nancy.hateoas.exception.ResourceBadRequestException;
import com.polytech.nancy.hateoas.repository.BookingRepository;
import com.polytech.nancy.hateoas.service.CinemasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/bookings")
public class BookingController {

    @Autowired
    private CinemasterService service;


    @GetMapping("/{bookingID}")
    public BookingDto getByUUID(@PathVariable UUID bookingID) {
        BookingDto booking = service.findBookingById(bookingID).toDto();
        booking.add(linkTo(methodOn(BookingController.class).getByUUID(bookingID)).withSelfRel());
        return booking;
    }

    @PostMapping
    public BookingDto createBooking(@RequestParam UUID searchId) {
        Search search = service.findSearch(searchId);
        if (search == null) {
            throw new ResourceBadRequestException("Cannot find search with id=" + searchId);
        }
        BookingDto booking = service.book(search).toDto();
        booking.add(linkTo(methodOn(BookingController.class).getByUUID(booking.getId())).withSelfRel());
        return booking;
    }

}
