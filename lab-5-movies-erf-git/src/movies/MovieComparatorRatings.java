package movies;

import java.util.Comparator;

/**
 * A comparator that orders Movie's by descending number of ratings (in Rating)
 *
 * @author RIT CS
 * @author YOUR NAME HERE
 */
public class MovieComparatorRatings implements Comparator<Movie> {

    @Override
    public int compare(Movie m1, Movie m2) {

        int result = m1.getRating().compareTo(m2.getRating());

        return result;
    }
}
