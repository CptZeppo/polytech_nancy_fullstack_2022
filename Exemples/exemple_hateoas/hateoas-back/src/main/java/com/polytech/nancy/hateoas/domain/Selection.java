package com.polytech.nancy.hateoas.domain;

import com.polytech.nancy.hateoas.domain.dto.SelectionDto;
import com.polytech.nancy.hateoas.domain.dto.ShowDto;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Selection {
    private final List<Show> selectedShow = new ArrayList<>();

    public boolean isEmpty() {
        return selectedShow.isEmpty();
    }

    public Show getByMovieIndex(int i) {
        return selectedShow.get(i);
    }

    public boolean isComplete() {
        return !isEmpty();
    }

    public void reset() {
        selectedShow.clear();
    }

    public Selection addShow(Show selectedShow) {
        this.selectedShow.add(selectedShow);
        return this;
    }

    public List<Show> getSelectedShow() {
        return selectedShow;
    }

    public BigDecimal getTotalPrice() {
        return selectedShow.stream().map(Show::getPrice).map(BigDecimal::new).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.UP);
    }

    public SelectionDto toDTO() {
        return new SelectionDto(Show.toListDTO(selectedShow), isComplete());
    }
}
