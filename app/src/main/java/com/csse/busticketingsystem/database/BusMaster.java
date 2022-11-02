package com.csse.busticketingsystem.database;

import android.provider.BaseColumns;

public class BusMaster {
    public BusMaster() {
    }

    public static class  BusesT implements BaseColumns {
            public static final String TABLE_NAME = "bus";
            public static final String COLUMN_NAME_BUS_ID = "bus_id";
            public static final String COLUMN_NAME_BUS_NUMBER = "bus_number";
            public static final String COLUMN_NAME_DESCRIPTION = "description";
        }

    }

