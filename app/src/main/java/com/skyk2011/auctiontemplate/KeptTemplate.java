package com.skyk2011.auctiontemplate;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class KeptTemplate extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;
    private Long memoId;
    public final static  String EXTRA_MYID = "com.skyk2011.auctiontemplate.MYID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kept_template);

        MainActivity.mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        MainActivity.mAdView.loadAd(adRequest);

        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_app_id));


        String[] from = {
                MemoContract.Memos.COL_TITLE,
                MemoContract.Memos.COL_UPDATED
        };

        int[] to = {
                android.R.id.text1,
                android.R.id.text2
        };

        adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_2, // 行レイアウトのリソース（今回はandroid.SDKのものを使う。SDKのものはandroid.R.、自分で作ったものはR.になる ）
                null, // cursor loaderが後で作ってくれる
                from, //simple_list_item_2のどのidにどのフィールドを紐づけるか指定する。
                to,
                0 // adapterの動作を規定する
        );

        ListView myListView = (ListView) findViewById(R.id.myListView);
        myListView.setAdapter(adapter);
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view,
                                           int position,
                                           long id
            ) {
                memoId = id;
                new AlertDialog.Builder(KeptTemplate.this)
                        .setTitle("テンプレート削除")
                        .setMessage("本当に削除しますか？")
                        .setPositiveButton("削除する", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // OK button pressed

                                Uri uri = ContentUris.withAppendedId(
                                       TemplateContentProvider.CONTENT_URI, // Uri
                                       memoId  // long id
                                );
                                getContentResolver().delete(
                                        uri,
                                        MemoContract.Memos._ID + " = ?",
                                        new String[] { Long.toString(memoId) }
                                );
                                //finish();
                            }
                        })
                        .setNegativeButton("キャンセル", null)
                        .show();
                return true;
            }
        });
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(
                    AdapterView<?> parent,
                    View view,
                    int position,
                    long id
            ){
                Intent intent = new Intent(KeptTemplate.this, Templates.class);
                intent.putExtra(EXTRA_MYID, id);
                startActivity(intent);
            }


        });


        getLoaderManager().initLoader( // Loaderの初期化
                0, //複数のloaderを起動することもできるが、今回は一つしかないので、引数は0
                null, //起動オプション
                this); //Loaderの実装場所
    }






    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                MemoContract.Memos._ID,
                MemoContract.Memos.COL_TITLE,
                MemoContract.Memos.COL_UPDATED
        };
        return new CursorLoader( //Loaderを作った時に実行されるqueryを返す必要がある。
                this,
                TemplateContentProvider.CONTENT_URI,
                projection, //どのフィールドを引張ってくるか
                null,
                null,
                MemoContract.Memos.COL_UPDATED + " DESC"
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {//ContentPrividerからデータが返ってきた時に実行される処理
        adapter.swapCursor(data); //返ってきたデータをadapterに入れる。
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {//何らかの理由でloaderがリセットされた時に呼ばれる処理
        adapter.swapCursor(null);
    }


    // メニューをActivity上に設置する
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 参照するリソースは上でリソースファイルに付けた名前と同じもの
        getMenuInflater().inflate(R.menu.option, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void addTemplate(){
        Intent intent = new Intent(KeptTemplate.this, Templates.class);
        intent.putExtra(EXTRA_MYID, 0L);
        startActivity(intent);
    }

    // メニューが選択されたときの処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem1:
                addTemplate();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }


}
