package com.csse.busticketingsystem.database;

import android.provider.BaseColumns;

public class ScheduleMaster {
    public ScheduleMaster() {
    }

    public static class  SchedulesT implements BaseColumns {
        public static final String TABLE_NAME = "schedule";
        public static final String COLUMN_NAME_SCHEDULE_ID = "schedule_id";
        public static final String COLUMN_NAME_ROUTE_ID = "route_id";
        public static final String COLUMN_NAME_BUS_ID = "bud_id";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_END_TIME = "end_time";
        public static final String COLUMN_NAME_STATUS = "status";
    }

}

