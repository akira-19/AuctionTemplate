package com.skyk2011.auctiontemplate;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Templates extends AppCompatActivity {

    private long memoId;

    private EditText titleText;
    private EditText bodyText;
    private TextView updatedText;
    private Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_templates);

        MainActivity.mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        MainActivity.mAdView.loadAd(adRequest);

        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_app_id));



        titleText = (EditText) findViewById(R.id.titleText);
        bodyText = (EditText) findViewById(R.id.bodyText);
        updatedText = (TextView) findViewById(R.id.updatedText);
        saveButton = (Button) findViewById(R.id.save) ;

        Intent intent = getIntent();
        memoId = intent.getLongExtra(KeptTemplate.EXTRA_MYID, 0L);

        if(memoId == 0){
            //new template
            saveButton.setEnabled(false);
        }else{
            //show template
            Uri uri = ContentUris.withAppendedId(//特定の行をContentProviderに問い合わせる。
                    TemplateContentProvider.CONTENT_URI,
                    memoId
            );

            String[] projection = {
                    MemoContract.Memos.COL_TITLE,
                    MemoContract.Memos.COL_BODY,
                    MemoContract.Memos.COL_UPDATED
            };
            Cursor c = getContentResolver().query( //contentProviderに問い合わせをして帰ってくるのはCursor
                    uri,
                    projection,
                    MemoContract.Memos._ID + " = ?",
                    new String[] { Long.toString(memoId) },
                    null
            );
            c.moveToFirst();// 最初のレコードに戻る
            titleText.setText(// Cursorから文字列取得する。
                    c.getString(c.getColumnIndex(MemoContract.Memos.COL_TITLE))
            );
            bodyText.setText(
                    c.getString(c.getColumnIndex(MemoContract.Memos.COL_BODY))
            );
            updatedText.setText(
                    "Updated: " +
                            c.getString(c.getColumnIndex(MemoContract.Memos.COL_UPDATED))
            );
            c.close();


        }
    }




    // 上書き保存
    public void save(View view) {
        String title = titleText.getText().toString().trim();
        String body = bodyText.getText().toString().trim();
        String updated =
                new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US)
                        .format(new Date());
        if (title.isEmpty()) {
            Toast.makeText(
                    Templates.this,
                    "タイトルを入力してください。",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(MemoContract.Memos.COL_TITLE, title);
            values.put(MemoContract.Memos.COL_BODY, body);
            values.put(MemoContract.Memos.COL_UPDATED, updated);
            // template update
            Uri uri = ContentUris.withAppendedId(
                    TemplateContentProvider.CONTENT_URI,
                    memoId
            );
            getContentResolver().update(
                    uri,
                    values,
                    MemoContract.Memos._ID + " = ?",
                    new String[] { Long.toString(memoId) }
            );
        }
        finish();
    }

    // 新しく保存
    public void saveAs(View view) {

        String title = titleText.getText().toString().trim();
        String body = bodyText.getText().toString().trim();
        String updated =
                new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US)
                        .format(new Date());
        if (title.isEmpty()) {
            Toast.makeText(
                    Templates.this,
                    "タイトルを入力してください。",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(MemoContract.Memos.COL_TITLE, title);
            values.put(MemoContract.Memos.COL_BODY, body);
            values.put(MemoContract.Memos.COL_UPDATED, updated);

            getContentResolver().insert(
                    TemplateContentProvider.CONTENT_URI,
                    values
            );
        }
        finish();
    }


}
