package org.shc.quarkus.panache.utils;

import org.shc.quarkus.jdbc.pojo.Artist;

public class ArtistUtils {
    public static final String KUVEMPU = "kuvempu";
    public static final String KUVEMPU_BIO = "A literary festival in Karnataka";
    public static final String MJ = "Micheal Jackson";
    public static final String MJ_BIO = "A pop singer";
    public static final String SADHU_KOKILA = "Sadhu Kokila";
    public static final String SADHU_COMEDY_BIO = "A comedian from karnataka";
    public static final String SADHU_MUSIC_BIO = "A musician from karnataka";

    public static Artist kuvempu() {
        return new Artist(KUVEMPU, KUVEMPU_BIO);
    }

    public static Artist michealJackson() {
        return new Artist(MJ, MJ_BIO);
    }

    public static Artist sadhuKokilaComedy() {
        return new Artist(SADHU_KOKILA, SADHU_COMEDY_BIO);
    }

    public static Artist sadhuKokilaMusic() {
        return new Artist(SADHU_KOKILA, SADHU_MUSIC_BIO);

    }
}