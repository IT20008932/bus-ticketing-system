package com.csse.busticketingsystem.database;

import android.provider.BaseColumns;

public class RouteMaster {
    public RouteMaster() {
    }

    public static class  RoutesT implements BaseColumns {
            public static final String TABLE_NAME = "route";
            public static final String COLUMN_NAME_ROUTE_ID = "route_id";
            public static final String COLUMN_NAME_START_LOCATION = "start_location";
        public static final String COLUMN_NAME_END_LOCATION = "end_location";
        }

    }

