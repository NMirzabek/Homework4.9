package uz.pdp.homework2;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static uz.pdp.homework2.DB.POSTS;

public class User implements Serializable {
    private String phone;
    private String password;
    private ZoneId zoneId;

    public User(String phone, String password, ZoneId zoneId) {
        this.phone = phone;
        this.password = password;
        this.zoneId = zoneId;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public void menu() {
        for (Post post : POSTS) {
            ZonedDateTime time = post.getZonedDateTime().withZoneSameInstant(zoneId);
            System.out.println(post.getTitle() + " " + time.getHour() + ":" + time.getMinute());
        }
    }
}
