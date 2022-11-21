package com.polytech.nancy.hateoas.service;

import com.polytech.nancy.hateoas.domain.*;
import com.polytech.nancy.hateoas.exception.ResourceBadRequestException;
import com.polytech.nancy.hateoas.repository.BookingRepository;
import com.polytech.nancy.hateoas.repository.MovieRepository;
import com.polytech.nancy.hateoas.repository.SearchRepository;
import com.polytech.nancy.hateoas.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CinemasterService {

    @Autowired
    private SearchRepository searchRepo;

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private BookingRepository bookingRepo;

    @Autowired
    private TheaterRepository theaterRepo;

    public Search search(Criteria criteria) {
        List<Show> shows = generateTheaterShows(criteria);
        Search s = searchRepo.save(new Search(criteria, shows));
        return s;
    }

    private List<Show> generateTheaterShows(Criteria criteria) {
        List<Show> shows = new ArrayList<>();
        Theater theater = findTheaterById(criteria.getTheaterID());
        int numberOfRooms = theater.getNumberOfRooms();

        for (int i = 0; i < numberOfRooms; i++) {
            LocalDateTime startingTime = criteria.getStartTime().plusMinutes(new Random().nextLong(0, 60));
            shows.addAll(generateShows(startingTime, theater, i+1));
        }
        return shows;
    }

    private List<Show> generateShows(final LocalDateTime startTime, final Theater theater, final int roomNumber) {
        List<Show> shows = new ArrayList<>();
        List<Movie> movies = movieRepo.getAll();
        Random random = new Random();
        LocalDateTime tempStartTime = startTime;
        LocalDateTime lastSession = startTime.withHour(23).withMinute(0);
        boolean fullSchedule = false;

        while (!fullSchedule) {
            int movieIndex = random.nextInt(0, movies.size());
            Show show = new Show(theater, movies.get(movieIndex), tempStartTime, roomNumber);
            tempStartTime = show.getEndTime().plusMinutes(10);
            if (tempStartTime.isAfter(lastSession)) {
                fullSchedule = true;
            }
            shows.add(show);
        }
        return shows;
    }

    public Search findSearch(UUID id) {
        return searchRepo.findById(id);
    }

    public List<Show> filter(Search search, Integer movieIndex, Boolean onlySelectable) {
        if (!onlySelectable) {
            return search.getSearchResult();
        }

        return search.getSelectableShow(movieIndex);
    }

    public Booking findBookingById(UUID bookingID) {
        return bookingRepo.findById(bookingID);
    }

    public Booking book(Search search) {
        Selection selection = search.getSelection();
        if (selection.isEmpty()) {
            throw new ResourceBadRequestException("Cannot book empty selection");
        }
        Booking booking = new Booking(selection.getSelectedShow(), selection.getTotalPrice());
        return bookingRepo.save(booking);
    }

    public List<Theater> getAllTheater() {
        return theaterRepo.getAll();
    }


    public Theater findTheaterById(UUID uuid) {
        return theaterRepo.findById(uuid);
    }
    
}
