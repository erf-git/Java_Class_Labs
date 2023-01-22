package movies;

import cs.Genre;
import cs.TitleType;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * The subclass of the IMDB abstract class that implements all the required
 * abstract query methods.
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class MyIMDB extends IMDB {
    /** The minimum number of votes a movie needs to be considered for top ranking */
    private final static int MIN_NUM_VOTES_FOR_TOP_RANKED = 1000;

    /**
     * Create IMDB using the small or large dataset.
     *
     * @param small true if the small dataset is desired, otherwise the large one
     * @throws FileNotFoundException
     */
    public MyIMDB(boolean small) throws FileNotFoundException {
        super(small);
    }

    @Override
    public Collection<Movie> getMovieTitleWithWords(String type, String words) {
        // we simply loop over movieList and add to our list the movies that
        // have the same type, and contain the words substring
        List<Movie> result = new LinkedList<>();

        // TODO Activity 1.2
        TitleType titleType = TitleType.valueOf(type);
        // for a particular Movie, movie
        for (Movie movie : movieList) {
            if (movie.getTitleType() == titleType && movie.getTitle().contains(words)) {
                // there is a match
                result.add(movie);
            }
        }

        return result;
    }

    @Override
    public Movie findMovieByID(String ID) {
        // TODO Activity 2.3

        // Finds the movie using the ID
        if (this.movieMap.containsKey(ID)) {
            return this.movieMap.get(ID);
        } else {
            return null;
        }
    }

    @Override
    public Collection<Movie> getMoviesByYearAndGenre(String type, int year, String genre) {
        // we use Movie's natural order comparison which is to order Movie's of a type by title and then year
        Set<Movie> result = new TreeSet<>();

        // TODO Activity 3.2
        // Gets genre and title type
        Genre g = Genre.valueOf(genre);
        TitleType t = TitleType.valueOf(type);
        for (Movie m : movieList) {
            // Found a match
            if (m.getTitleType() == t && m.getGenres().contains(g) && m.getYear() == year) {
                result.add(m);
            }
        }
        return result;
    }

    @Override
    public Collection<Movie> getMoviesByRuntime(String type, int start, int end) {
        // we use a comparator which orders Movie's of a type by descending runtime
        // and then title
        Set<Movie> result = new TreeSet<>(new MovieComparatorRuntime());

        // TODO Activity 4.2
        // Gets genre and title type
        TitleType t = TitleType.valueOf(type);
        for (Movie m : movieList) {
            // I don't want the end to be less than the start
            if (end >= start) {
                // Found a match
                if (m.getTitleType() == t && m.getRuntimeMinutes() >= start && m.getRuntimeMinutes() <= end ) {
                    result.add(m);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<Movie> getMoviesMostVotes(int num, String type) {
        // use a comparator that orders Movie's of a type by descending number of votes

        List<Movie> result = new LinkedList<>();
        Set<Movie> preResult = new TreeSet<>(new MovieComparatorVotes());

        // TODO Activity 5.3

        // Puts movies from movieList into preResult
        // preResult will sort movies by MovieComparatorVotes()
        TitleType t = TitleType.valueOf(type);
        for (Movie m : movieList) {
            if (m.getTitleType() == t) {
                preResult.add(m);
            }
        }

        // Takes the top num movies for each type
        for (Movie m : preResult) {
            if (result.size() < num) {
                result.add(m);
            }
        }

        return result;
    }

    @Override
    public Map<Integer, List<Movie>> getMoviesTopRated(int num, String type, int start, int end) {

        Map<Integer, List<Movie>> result = new TreeMap<>();

        // TODO Activity 6.2

        // It'll look through this for every year
        for (int i = start; i <= end; i++) {

            TreeSet<Movie> moviesByRating = new TreeSet<>(new MovieComparatorRatings());
            List<Movie> moviesTop = new LinkedList<>();

            // Will adds movies from movieList to moviesByRating that are
            //  of the specify type
            //  have >= min number of votes
            // moviesByRating will sort by Rating's sort
            for (Movie m : movieList) {
                if (m.getRating().getNumVotes() >= MIN_NUM_VOTES_FOR_TOP_RANKED && m.getTitleType() == TitleType.valueOf(type) && m.getYear() == i) {
                    moviesByRating.add(m);
                }
            }

            // Adds the top num movies to preResult
            for (Movie m : moviesByRating) {
                if (moviesTop.size() < num) {
                    moviesTop.add(m);
                }
            }

            result.put(i, moviesTop);
        }

        return result;
    }
}