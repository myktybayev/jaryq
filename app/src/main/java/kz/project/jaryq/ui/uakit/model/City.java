package kz.project.jaryq.ui.uakit.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class City implements Serializable, Comparable<City> {
    String title;
    String lng;
    String lat;
    String timezone;
    String slug;


    public City(String title, String lng, String lat, String timezone, String slug) {
        this.title = title;
        this.lng = lng;
        this.lat = lat;
        this.timezone = timezone;
        this.slug = slug;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public int compareTo(City city) {
        return this.getTitle().compareTo(city.getTitle());
    }
}
