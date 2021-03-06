package com.passnail.connect.util;

/**
 * Created by: Pszemko at czwartek, 11.03.2021 21:19
 * Project: passnail-client
 */
public class ConnectionConstants {

    private ConnectionConstants() {
    }


    public static final String SERVER_RPI_HOST = "http://95.108.36.173:8081";

    public static final String AUTHORIZE_WITH_ONLINE_ID = "/api/auth/user";

    public static final String GENERATE_ONLINE_ID_URI = "/api/synch/user/create";

    public static final String SYNCHRONIZE_DATA_URI = "/api/synch/user/synchronize";
}
