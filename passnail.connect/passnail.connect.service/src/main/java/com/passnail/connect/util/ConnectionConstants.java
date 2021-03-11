package com.passnail.connect.util;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 21:19
 * Project: passnail-client
 */
public class ConnectionConstants {

    private ConnectionConstants() {
    }

    public static final String SERVER_HEROKU_HOST = "https://passnail-server.herokuapp.com";

    public static final String GENERATE_ONLINE_ID_URI = "/user/create/";

    public static final String SYNCHRONIZE_DATA_URI = "/user/synchronize/";
}
