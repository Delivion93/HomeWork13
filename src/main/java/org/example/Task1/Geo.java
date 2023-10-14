package org.example.Task1;

public class Geo {
    public Geo(){

    }
    public Geo(float lat, float lng) {
        this.lat = lat;
        this.lng = lng;
    }

    private float lat;
    private float lng;

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    @Override
    public String toString() {
        return "Geo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
