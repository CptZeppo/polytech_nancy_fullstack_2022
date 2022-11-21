package com.polytech.nancy.hateoas.controler;

import com.polytech.nancy.hateoas.domain.Criteria;
import com.polytech.nancy.hateoas.domain.Selection;
import com.polytech.nancy.hateoas.domain.Show;
import com.polytech.nancy.hateoas.domain.Search;
import com.polytech.nancy.hateoas.domain.dto.CriteriaDto;
import com.polytech.nancy.hateoas.domain.dto.SearchDto;
import com.polytech.nancy.hateoas.domain.dto.SelectionDto;
import com.polytech.nancy.hateoas.domain.dto.ShowDto;
import com.polytech.nancy.hateoas.service.CinemasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/searchs")
public class SearchController {

    @Autowired
    private CinemasterService service;

    @PostMapping
    public SearchDto newSearch(@RequestBody CriteriaDto criteriaDto) {
        Criteria criteria = criteriaDto.toDomaine();
        SearchDto search = service.search(criteria).toDTO();
        return addSearchLinks(search);
    }

    @GetMapping("/{searchID}")
    public SearchDto getSearch(@PathVariable UUID searchID) {
        SearchDto search = service.findSearch(searchID).toDTO();
        return addSearchLinks(search);
    }

    @GetMapping("/{searchID}/shows")
    public CollectionModel<ShowDto> getMovies(
            @PathVariable UUID searchID,
            @RequestParam Integer movieIndex,
            @RequestParam Boolean onlySelectable) {
        Search search = service.findSearch(searchID);
        List<Show> filteredResult = service.filter(search, movieIndex, onlySelectable);

        CollectionModel<ShowDto> result = CollectionModel.of(filteredResult.stream().map(show -> addShowLinks(show.toDTO(),searchID, movieIndex)).toList());

        result.add(linkTo(methodOn(SearchController.class).getMovies(searchID, movieIndex, onlySelectable)).withSelfRel());
        result.add(linkTo(methodOn(SearchController.class).getSelection(searchID)).withRel("selection"));
        result.add(linkTo(methodOn(SearchController.class).getSearch(searchID)).withRel("search"));

        return result;
    }

    @GetMapping("/{searchID}/selection")
    public SelectionDto getSelection(@PathVariable UUID searchID) {
        Search search = service.findSearch(searchID);
        Selection selection = search.getSelection();
        return addSelectionLinks(selection.toDTO(), searchID, selection.isComplete());
    }


    @PostMapping("/{searchID}/shows/{showID}")
    public SelectionDto addSelection(
            @PathVariable UUID searchID,
            @PathVariable UUID showID,
            @RequestParam Boolean resetSelection) {
        Search search = service.findSearch(searchID);
        Selection selection = search.addSelection(showID, resetSelection);
        return addSelectionLinks(selection.toDTO(), searchID, selection.isComplete());
    }


    private ShowDto addShowLinks(ShowDto show, UUID searchID, Integer movieIndex) {
        boolean resetSelection =  (movieIndex == null || movieIndex <= 0); //on reset si c'est la première selection

        show.add(linkTo(Objects.requireNonNull(methodOn(SearchController.class).addSelection(searchID, show.getId(), false))).withRel("select"));
        return show;
    }


    private SearchDto addSearchLinks(SearchDto search) {
        search.add(linkTo(methodOn(SearchController.class).getSearch(search.getId())).withSelfRel());
        search.add(linkTo(methodOn(SearchController.class).getSelection(search.getId())).withRel("selection"));
        search.add(linkTo(methodOn(SearchController.class).getMovies(search.getId(), 0, false)).withRel("all_shows"));
        return search;
    }

    private SelectionDto addSelectionLinks(SelectionDto selection, UUID searchID, boolean isSelectionComplete) {
            selection.add(linkTo(methodOn(SearchController.class).getSelection(searchID)).withSelfRel());
            selection.add(linkTo(methodOn(SearchController.class).getSearch(searchID)).withRel("search"));

            if (isSelectionComplete) {
                selection.add(linkTo(methodOn(BookingController.class).createBooking(searchID)).withRel("create_booking"));
            }
            return selection;
    }


}
