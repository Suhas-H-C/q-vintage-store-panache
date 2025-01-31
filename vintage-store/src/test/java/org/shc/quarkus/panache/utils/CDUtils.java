package org.shc.quarkus.panache.utils;

import org.shc.quarkus.panache.entity.CD;

import java.math.BigDecimal;

public class CDUtils {

    public static CD pop() {
        CD cd = new CD();
        cd.genre = "Pop";
        cd.musicCompany = "Epic Records";
        cd.price = BigDecimal.valueOf(10.0);
        cd.title = "Bad";
        return cd;
    }

    public static CD rock() {
        CD cd = new CD();
        cd.genre = "Rock";
        cd.musicCompany = "Sony";
        cd.price = BigDecimal.valueOf(90.0);
        cd.title = "Thriller";
        return cd;
    }

    public static CD jazz() {
        CD cd = new CD();
        cd.genre = "Jazz";
        cd.musicCompany = "Warner Bros";
        cd.price = BigDecimal.valueOf(20.0);
        cd.title = "Off the Wall";
        return cd;
    }

    public static CD blues() {
        CD cd = new CD();
        cd.genre = "Blues";
        cd.musicCompany = "Atlantic Records";
        cd.price = BigDecimal.valueOf(30.0);
        cd.title = "Dangerous";
        return cd;
    }

    public static CD classical() {
        CD cd = new CD();
        cd.genre = "Classical";
        cd.musicCompany = "Deutsche Grammophon";
        cd.price = BigDecimal.valueOf(40.0);
        cd.title = "Invincible";
        return cd;
    }

    public static CD country() {
        CD cd = new CD();
        cd.genre = "Country";
        cd.musicCompany = "RCA Records";
        cd.price = BigDecimal.valueOf(50.0);
        cd.title = "Xscape";
        return cd;
    }

    public static CD electronic() {
        CD cd = new CD();
        cd.genre = "Electronic";
        cd.musicCompany = "Virgin Records";
        cd.price = BigDecimal.valueOf(60.0);
        cd.title = "Blood on the Dance Floor";
        return cd;
    }
}