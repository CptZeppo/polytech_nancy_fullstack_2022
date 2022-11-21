package com.polytech.nancy.hateoas.repository;

import com.polytech.nancy.hateoas.domain.Movie;
import com.polytech.nancy.hateoas.domain.Show;
import com.polytech.nancy.hateoas.domain.Theater;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieRepository {

    Map<UUID, Movie> movies = new HashMap<>();

    public MovieRepository() {
        Set.of(
                new Movie("Nausicaä de la Vallée du Vent", 116, "https://upload.wikimedia.org/wikipedia/en/b/bc/Nausicaaposter.jpg"),
                new Movie("Le Château dans le ciel", 124, "https://upload.wikimedia.org/wikipedia/en/f/f5/Castle_in_the_Sky_%281986%29.png"),
                new Movie("Mon voisin Totoro", 86, "https://upload.wikimedia.org/wikipedia/en/0/02/My_Neighbor_Totoro_-_Tonari_no_Totoro_%28Movie_Poster%29.jpg"),
                new Movie("Kiki la petite sorcière", 103, "https://upload.wikimedia.org/wikipedia/en/0/07/Kiki%27s_Delivery_Service_%28Movie%29.jpg"),
                new Movie("Porco Rosso", 93, "https://upload.wikimedia.org/wikipedia/en/f/fc/Porco_Rosso_%28Movie_Poster%29.jpg"),
                new Movie("Princesse Mononoké", 134, "https://upload.wikimedia.org/wikipedia/en/8/8c/Princess_Mononoke_Japanese_poster.png"),
                new Movie("Le Voyage de Chihiro", 124, "https://upload.wikimedia.org/wikipedia/en/d/db/Spirited_Away_Japanese_poster.png"),
                new Movie("Le Château ambulant", 119, "https://upload.wikimedia.org/wikipedia/en/a/a0/Howls-moving-castleposter.jpg"),
                new Movie("Ponyo sur la falaise", 100, "https://upload.wikimedia.org/wikipedia/en/9/9d/Ponyo_%282008%29.png"),
                new Movie("Le vent se lève", 126, "https://upload.wikimedia.org/wikipedia/en/a/a3/Kaze_Tachinu_poster.jpg")
        ).forEach(movie -> movies.put(movie.getId(), movie));
    }

    public List<Movie> getAll() {
        return List.copyOf(movies.values());
    }

}
