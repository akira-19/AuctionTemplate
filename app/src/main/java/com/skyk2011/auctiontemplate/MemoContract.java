package com.skyk2011.auctiontemplate;

import android.provider.BaseColumns;

/**
 * Created by akira on 2018-01-23.
 */

public final class MemoContract {

    public  MemoContract(){}

    public static abstract class Memos implements BaseColumns{
        public static final String TABLE_NAME = "templates";
        public static final String COL_TITLE = "title";
        public static final String COL_BODY = "body";
        public static final String COL_CREATE = "created";
        public static final String COL_UPDATED = "updated";

    }
}