package uz.pdp.homework2;

import java.time.ZonedDateTime;

public class Post {
    private String title;
    private ZonedDateTime zonedDateTime;

    public Post(String title, ZonedDateTime zonedDateTime) {
        this.title = title;
        this.zonedDateTime = zonedDateTime;
    }


    public String getTitle() {
        return title;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
