package model;

import com.fasterxml.jackson.annotation.JsonRootName;


public class Rating {
    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Rating(String scheme, String rating) {
        this.scheme = scheme;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "scheme='" + scheme + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }

    public String scheme;
    public String rating;


}
