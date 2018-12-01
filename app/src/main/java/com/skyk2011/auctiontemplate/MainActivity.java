package com.skyk2011.auctiontemplate;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentProvider;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity  {

    public static AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_app_id));



    }

    // newTemplateへ移動
    public void newTemplate(View view) {
        Intent intent = new Intent(this, NewTemplate.class);
        startActivity(intent);
    }
    // Templateへ移動
    public void keptTemplate(View view) {
        Intent intent = new Intent(this, KeptTemplate.class);
        startActivity(intent);
    }

    // ポップアップを表示
    public void explanation(View view) {


        new AlertDialog.Builder(this)
                .setTitle("title")
                .setMessage("●出品テキストを作る\n\n" +
                        "まずは一番上のドロップダウンから該当する商品ジャンルを選んで下さい。\n\n" +
                        "次に必要な情報を入力し、本文に挿入をタップすると自動でテキストエリアに入力した情報を挿入します。\n\n" +
                        "何も変更していない情報は挿入しません。\n\n" +
                        "テキストエリアが完成したらコピーを押すとクリップボードに本文をコピーします。\n\n" +
                        "テンプレートとして保存を押すと、テキストエリアの本文をテンプレートとして保存します。\n\n" +
                        "タイトルをつけて保存すると、次回以降、保存したテンプレートを使うから呼び出すことができます。\n\n" +
                        "\n" +
                        "\n" +
                        "●保存したテンプレートを使う\n\n" +
                        "今までに保存したテンプレートを使用します。\n\n" +
                        "新しくテンプレートを追加する場合には右上の追加ボタンをタップしてください。\n\n" +
                        "テンプレートタイトルをタップすると今までに作成したテンプレートが表示されるので、テキストボックスに必要な情報を追記して下さい。\n\n" +
                        "テンプレートを削除するにはタイトルを長押しして下さい。\n\n")
                .setPositiveButton("OK", null)
                .show();

//        出品テキストを作る
//                まずは一番上のドロップダウンから該当する商品ジャンルを選んで下さい。
//                次に必要な情報を入力し、本文に挿入をタップすると自動でテキストエリアに入力した情報を挿入します。
//                何も変更していない情報は挿入しません。
//                テキストエリアが完成したらコピーを押すとクリップボードに本文をコピーします。
//                テンプレートとして保存を押すと、テキストエリアの本文をテンプレートとして保存します。
//        タイトルをつけて保存すると、次回以降、保存したテンプレートを使うから呼び出すことができます。
//
//        保存したテンプレートを
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }




}
