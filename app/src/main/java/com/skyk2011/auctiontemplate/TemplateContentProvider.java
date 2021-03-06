package com.skyk2011.auctiontemplate;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import javax.xml.transform.Templates;

public class TemplateContentProvider extends ContentProvider {

    public static final String AUTHORITY =
            "com.skyk2011.auctiontemplate.TemplateContentProvider";
    public static final Uri CONTENT_URI =
            Uri.parse("content://" + AUTHORITY + "/" + MemoContract.Memos.TABLE_NAME); //Uri.pars:文字列をUriに変換する命令


    // UriMatcher
    private static final int MEMOS = 1;
    private static final int MEMO_ITEM = 2;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, MemoContract.Memos.TABLE_NAME, MEMOS);
        uriMatcher.addURI(AUTHORITY, MemoContract.Memos.TABLE_NAME + "/#", MEMO_ITEM);
    }

    private MemoOpenHelper memoOpenHelper;

    public TemplateContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        if (uriMatcher.match(uri) != MEMO_ITEM) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        SQLiteDatabase db = memoOpenHelper.getWritableDatabase();
        int deletedCount = db.delete(
                MemoContract.Memos.TABLE_NAME,
                selection,
                selectionArgs
        );
        getContext().getContentResolver().notifyChange(uri, null);
        return deletedCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        if (uriMatcher.match(uri) != MEMOS) {
            throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        SQLiteDatabase db = memoOpenHelper.getWritableDatabase();
        long newId = db.insert(
                MemoContract.Memos.TABLE_NAME,
                null, //値が空だった場合にする処理
                values
        );
        Uri newUri = ContentUris.withAppendedId(
                TemplateContentProvider.CONTENT_URI,
                newId
        );
        getContext().getContentResolver().notifyChange(newUri, null);
        return newUri;    }

    @Override
    public boolean onCreate() {
        memoOpenHelper = new MemoOpenHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(
                        Uri uri,
                        String[] projection,
                        String selection,
                        String[] selectionArgs,
                        String sortOrder
    ) {
        switch (uriMatcher.match(uri)){
            case MEMOS:
            case MEMO_ITEM:
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        SQLiteDatabase db = memoOpenHelper.getReadableDatabase();
        Cursor c = db.query(
                MemoContract.Memos.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
        c.setNotificationUri(getContext().getContentResolver(), uri); //監視処理の設定
        return c;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        if (uriMatcher.match(uri) != MEMO_ITEM) { //MEMO_ITEM以外の結果は受け取ってはいけない
            throw new IllegalArgumentException("Invalid URI: " + uri); //例外処理
        }
        // dataの更新
        SQLiteDatabase db = memoOpenHelper.getWritableDatabase();
        int updatedCount = db.update(
                MemoContract.Memos.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );

        getContext().getContentResolver().notifyChange(uri, null);//dataの変更があった時にUIに通知をだす。
        return updatedCount;

    }
}
