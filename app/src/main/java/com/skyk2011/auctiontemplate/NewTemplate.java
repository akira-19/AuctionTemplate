package com.skyk2011.auctiontemplate;

        import android.app.AlertDialog;
        import android.content.ClipData;
        import android.content.ClipboardManager;
        import android.content.ContentUris;
        import android.content.ContentValues;
        import android.content.DialogInterface;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.ads.AdRequest;
        import com.google.android.gms.ads.AdView;
        import com.google.android.gms.ads.MobileAds;

        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.Locale;

public class NewTemplate extends AppCompatActivity {

    // 共通の変数
    private String color;
    private String colorTitle;
    private String condition;
    private String conditionTitle;
    private String price;
    private String priceTitle;
    private String ship;
    private String shipTitle;
    private String yen;
    private ArrayList<String> textPay = new ArrayList<String>();


    // ファッション　変数
    private String name;
    private String nameTitle;
    private String brand;
    private String brandTitle;
    private String size;
    private String sizeTitle;
    private String size1 ;
    private String size1Title;
    private String size2;
    private String size2Title;
    private String size3;
    private String size3Title;
    private String size4;
    private String size4Title;
    private String cm;
    private String comment;
    private String commentTitle;


    // スマホ　変数
    private String phoneMaker;
    private String phoneMakerTitle;
    private String phoneModel;
    private String phoneModelTitle;
    private String phoneColor;
    private String phoneSN;
    private String phoneSNTitle;
    private ArrayList<String> phoneACarray = new ArrayList<String>();
    private String commentPhone;
    private String commentPhoneTitle;

    // PC
    private String PCmaker;
    private String PCMakerTitle;
    private String PCmodel;
    private String PCmodelTitle;
    private String PCOS;
    private String PCOSTitle;
    private String CPU;
    private String CPUTitle;
    private String memory;
    private String memoryTitle;
    private String HDD;
    private String HDDTitle;
    private String display;
    private String displayTitle;
    private String PCcomment;
    private String PCcommentTitle;








    private Spinner mSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_template);

        MainActivity.mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        MainActivity.mAdView.loadAd(adRequest);

        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_app_id));



        //全ての項目を非表示
        findViewById(R.id.fashion).setVisibility(View.GONE);
        findViewById(R.id.smartPhone).setVisibility(View.GONE);
        findViewById(R.id.PC).setVisibility(View.GONE);
        findViewById(R.id.appliance).setVisibility(View.GONE);
        findViewById(R.id.DVD).setVisibility(View.GONE);
        findViewById(R.id.book).setVisibility(View.GONE);
        findViewById(R.id.others).setVisibility(View.GONE);

        // スピナーで選択されたジャンルフォーマットを表示する。
        mSpinner = (Spinner) findViewById(R.id.formatSort);
        String[] formatSort = getResources().getStringArray(R.array.formatSort);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // 初回起動時の動作
                if (mSpinner.isFocusable() == false) {
                    mSpinner.setFocusable(true);
                    return;
                }
                // 初回以降の動作

                // 他のフォームが出てこない様に一度全てリセットする。
                findViewById(R.id.fashion).setVisibility(View.GONE);
                findViewById(R.id.smartPhone).setVisibility(View.GONE);
                findViewById(R.id.PC).setVisibility(View.GONE);
                findViewById(R.id.appliance).setVisibility(View.GONE);
                findViewById(R.id.DVD).setVisibility(View.GONE);
                findViewById(R.id.book).setVisibility(View.GONE);
                findViewById(R.id.others).setVisibility(View.GONE);



                //スピナー内容に応じて表示するフォーマットを変える。
                //スピナー取得
                Spinner spinner = (Spinner) parent;
                // 選択したアイテムを取得
                String item = (String) spinner.getSelectedItem();

                // 選択したアイテムごとにフォームを有効化する。
                switch (item){
                    case "ファッション":
                        findViewById(R.id.fashion).setVisibility(View.VISIBLE);
                        break;
                    case "スマホ、タブレット":
                        findViewById(R.id.smartPhone).setVisibility(View.VISIBLE);
                        break;
                    case "PC":
                        findViewById(R.id.PC).setVisibility(View.VISIBLE);
                        break;
                    case "家電":
                        findViewById(R.id.appliance).setVisibility(View.VISIBLE);
                        break;
                    case "DVD,Blu-ray":
                        findViewById(R.id.DVD).setVisibility(View.VISIBLE);
                        break;
                    case "本、雑誌、漫画":
                        findViewById(R.id.book).setVisibility(View.VISIBLE);
                        break;
                    case "その他":
                        findViewById(R.id.others).setVisibility(View.VISIBLE);
                        break;
                }

//
//                Toast.makeText(getApplicationContext(), formatSort[1], Toast.LENGTH_SHORT).show();
//                findViewById(R.id.fashion).setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // 初回起動時の対応
        mSpinner.setFocusable(false);

    }


//    public void newTemp(View view){
//
//    }




    public void newTemp(View arg0) {

        //テキスト入力を受け付けるビューを作成します。
        final EditText editView = new EditText(NewTemplate.this);
        new AlertDialog.Builder(NewTemplate.this)
                .setIcon(android.R.drawable.ic_menu_edit)
                .setTitle("タイトルを入力して下さい")
                //setViewにてビューを設定します。
                .setView(editView)
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 保存を押した時の処理
                        EditText editText = (EditText) findViewById(R.id.format) ;
                        String textBox = editText.getText().toString();
                        String updated =
                                new SimpleDateFormat("yyyy-MM-dd kk:mm:ss", Locale.US)
                                        .format(new Date());

                        ContentValues values = new ContentValues();
                        values.put(MemoContract.Memos.COL_TITLE,  editView.getText().toString());
                        values.put(MemoContract.Memos.COL_BODY, textBox);
                        values.put(MemoContract.Memos.COL_UPDATED, updated);

                        // template new
                        getContentResolver().insert(
                                TemplateContentProvider.CONTENT_URI,
                                values
                        );
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    }
                })
                .show();

    }




    public void Insert(View view){

        // 各テキストボックス、スピナー内容取得
        ArrayList<String> textForm = new ArrayList<String>(); // textForm 商品詳細（ファッション）
        ArrayList<String> textFormSP = new ArrayList<String>(); // textFormSP 商品詳細（スマホ）
        ArrayList<String> textFormPC = new ArrayList<String>(); // textFormSP 商品詳細（PC）
        ArrayList<String> textFormAP = new ArrayList<String>(); // textFormSP 商品詳細(家電）
        ArrayList<String> textFormDV = new ArrayList<String>(); // textFormSP 商品詳細（DVD）
        ArrayList<String> textFormBK = new ArrayList<String>(); // textFormSP 商品詳細（本）
        ArrayList<String> textFormOT = new ArrayList<String>(); // textFormSP 商品詳細（その他）

        ArrayList<String> textForm2 = new ArrayList<String>(); // textForm2 発送方法
        ArrayList<String> textForm3 = new ArrayList<String>(); // textForm3 その他


        // スマホチェックボックスとタイトルを取得
        CheckBox[] checkPaySP = {(CheckBox)findViewById(R.id.phoneCheck1),
                (CheckBox)findViewById(R.id.phoneCheck2),
                (CheckBox)findViewById(R.id.phoneCheck3),
                (CheckBox)findViewById(R.id.phoneCheck4)};
        String[] stringPaySP = {getResources().getString(R.string.phoneCheck1),
                getResources().getString(R.string.phoneCheck2),
                getResources().getString(R.string.phoneCheck3),
                getResources().getString(R.string.phoneCheck4)};


        // 支払い方法チェックボックスとタイトルを取得
        CheckBox[] checkPay = {(CheckBox)findViewById(R.id.check1),
                (CheckBox)findViewById(R.id.check2),
                (CheckBox)findViewById(R.id.check3),
                (CheckBox)findViewById(R.id.check4),
                (CheckBox)findViewById(R.id.check5),
                (CheckBox)findViewById(R.id.check6)

        };
        String[] stringPay = {getResources().getString(R.string.check1),
                getResources().getString(R.string.check2),
                getResources().getString(R.string.check3),
                getResources().getString(R.string.check4),
                getResources().getString(R.string.check5),
                getResources().getString(R.string.check6)
        };





        // ----------------------------ファッション----------------------------

        // 商品名
        EditText editTextName = (EditText) findViewById(R.id.name);
        name = String.valueOf(editTextName.getText());
        nameTitle = getResources().getString(R.string.name);
        if(!name.equals("")){
            textForm.add(nameTitle+name);
        }

        // ブランド
        EditText editTextBrand = (EditText) findViewById(R.id.brand);
        brand = String.valueOf(editTextBrand.getText());
        brandTitle = getResources().getString(R.string.brand);
        if(!brand.equals("")){
            textForm.add(brandTitle+brand);
        }



        // サイズ
        Spinner spinnerSize = (Spinner)findViewById(R.id.sizeList);
        size = (String) spinnerSize.getSelectedItem();
        sizeTitle = getResources().getString(R.string.size);
        if(!size.equals("選択してください。")&&!size.equals("自分で入力する")){
            textForm.add(sizeTitle+size);
        }else if(size.equals("自分で入力する")){
            textForm.add(sizeTitle);
        }


        // 着丈
        EditText editTextSize1 = (EditText) findViewById(R.id.size1);
        size1 = String.valueOf(editTextSize1.getText());
        size1Title = getResources().getString(R.string.size1);
        cm = getResources().getString(R.string.cm);
        if(!size1.equals("")){
            textForm.add("　"+size1Title+size1+cm);
        }

        // 肩幅
        EditText editTextSize2 = (EditText) findViewById(R.id.size2);
        size2 = String.valueOf(editTextSize2.getText());
        size2Title = getResources().getString(R.string.size2);
        cm = getResources().getString(R.string.cm);
        if(!size2.equals("")){
            textForm.add("　"+size2Title+size2+cm);
        }

        // 身幅
        EditText editTextSize3 = (EditText) findViewById(R.id.size3);
        size3 = String.valueOf(editTextSize3.getText());
        size3Title = getResources().getString(R.string.size3);
        cm = getResources().getString(R.string.cm);
        if(!size3.equals("")){
            textForm.add("　"+size3Title+size3+cm);
        }

        // 袖丈
        EditText editTextSize4 = (EditText) findViewById(R.id.size4);
        size4 = String.valueOf(editTextSize4.getText());
        size4Title = getResources().getString(R.string.size4);
        cm = getResources().getString(R.string.cm);
        if(!size4.equals("")){
            textForm.add("　"+size4Title+size4+cm);
        }


        // カラー
        Spinner spinnerColor = (Spinner)findViewById(R.id.colorList);
        color = (String) spinnerColor.getSelectedItem();
        colorTitle = getResources().getString(R.string.color);
        if(!color.equals("選択してください。")&&!color.equals("自分で入力する")){
            textForm.add(colorTitle+color);
        }else if(color.equals("自分で入力する")){
            textForm.add(colorTitle);
        }

        // 商品状態
        Spinner spinnerCondition = (Spinner)findViewById(R.id.conditionList);
        condition = (String) spinnerCondition.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textForm.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textForm.add(conditionTitle);
        }


        // 定価（参考）
        EditText editTextPrice = (EditText) findViewById(R.id.price);
        price = String.valueOf(editTextPrice.getText());
        priceTitle = getResources().getString(R.string.price);
        yen = getResources().getString(R.string.yen);
        if(!price.equals("")){
            textForm.add(priceTitle+price+yen);
        }

        // コメント
        EditText editTextComment = (EditText) findViewById(R.id.comment);
        comment = String.valueOf(editTextComment.getText());
        commentTitle = getResources().getString(R.string.comment);
        if(!comment.equals("")){
            textForm.add(commentTitle+comment);
        }


        // ----------------------------スマホ----------------------------

        // メーカー
        EditText editTextMaker = (EditText) findViewById(R.id.phoneMaker);
        phoneMaker = String.valueOf(editTextMaker.getText());
        phoneMakerTitle = getResources().getString(R.string.phoneMaker);
        if(!phoneMaker.equals("")){
            textFormSP.add(phoneMakerTitle+phoneMaker);
        }

        // 機種名
        EditText editTextModel = (EditText) findViewById(R.id.phoneModel);
        phoneModel = String.valueOf(editTextModel.getText());
        phoneModelTitle = getResources().getString(R.string.phoneModel);
        if(!phoneModel.equals("")){
            textFormSP.add(phoneModelTitle+phoneModel);
        }



        // カラー
        Spinner spinnerPhoneColor = (Spinner)findViewById(R.id.phoneColorList);
        phoneColor = (String) spinnerPhoneColor.getSelectedItem();
        colorTitle = getResources().getString(R.string.color);
        if(!phoneColor.equals("選択してください。")&&!phoneColor.equals("自分で入力する")){
            textFormSP.add(colorTitle+phoneColor);
        }else if(phoneColor.equals("自分で入力する")){
            textFormSP.add(colorTitle);
        }

        // 製造番号
        EditText editTextPhoneSN = (EditText) findViewById(R.id.phoneSN);
        phoneSN = String.valueOf(editTextPhoneSN.getText());
        phoneSNTitle = getResources().getString(R.string.phoneSN);
        if(!phoneSN.equals("")){
            textFormSP.add(phoneSNTitle+phoneSN);
        }


        // アクセサリー
        phoneACarray.clear();
        for(int i = 0; i < checkPaySP.length;i++){
            if(checkPaySP[i].isChecked()){
                phoneACarray.add(stringPaySP[i]);
            }
        }
        if(phoneACarray.size()>0){
            textFormSP.add("付属品：" + phoneACarray.get(0));
            for(int i = 1; i < phoneACarray.size();i++){
                textFormSP.add("　　　　"+phoneACarray.get(i));
            }
        }

        // 商品状態
        Spinner spinnerConditionSP = (Spinner)findViewById(R.id.SPconditionList);
        condition = (String) spinnerConditionSP.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textFormSP.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textFormSP.add(conditionTitle);
        }

        // コメント
        EditText editTextCommentPhone = (EditText) findViewById(R.id.commentPhone);
        commentPhone = String.valueOf(editTextCommentPhone.getText());
        commentPhoneTitle = getResources().getString(R.string.comment);
        if(!commentPhone.equals("")){
            textFormSP.add(commentPhoneTitle+commentPhone);
        }


        // ----------------------------PC----------------------------

//        メーカー
        EditText editTextMakerPC = (EditText) findViewById(R.id.PCmaker);
        PCmaker = String.valueOf(editTextMakerPC.getText());
        PCMakerTitle = getResources().getString(R.string.phoneMaker);
        if(!PCmaker.equals("")){
            textFormPC.add(PCMakerTitle+PCmaker);
        }
//        モデル
        EditText editTextModelPC = (EditText) findViewById(R.id.PCmodel);
        PCmodel = String.valueOf(editTextModelPC.getText());
        PCmodelTitle = getResources().getString(R.string.model);
        if(!PCmodel.equals("")){
            textFormPC.add(PCmodelTitle+PCmodel);
        }
//        OS
        EditText editTextOS = (EditText) findViewById(R.id.PCOS);
        PCOS = String.valueOf(editTextOS.getText());
        PCOSTitle = getResources().getString(R.string.OS);
        if(!PCOS.equals("")){
            textFormPC.add(PCOSTitle+PCOS);
        }
//        色
        Spinner spinnerPCColor = (Spinner)findViewById(R.id.PCColorList);
        color = (String) spinnerPCColor.getSelectedItem();
        colorTitle = getResources().getString(R.string.color);
        if(!color.equals("選択してください。")&&!color.equals("自分で入力する")){
            textFormPC.add(colorTitle+color);
        }else if(color.equals("自分で入力する")){
            textFormPC.add(colorTitle);
        }

//        CPU
        EditText editTextCPU = (EditText) findViewById(R.id.CPU);
        CPU = String.valueOf(editTextCPU.getText());
        CPUTitle = getResources().getString(R.string.CPU);
        if(!CPU.equals("")){
            textFormPC.add(CPUTitle+CPU);
        }
//        メモリ：  GB
        EditText editTextMemory = (EditText) findViewById(R.id.memory);
        memory = String.valueOf(editTextMemory.getText());
        memoryTitle = getResources().getString(R.string.memory);
        if(!memory.equals("")) {
            textFormPC.add(CPUTitle + CPU + "GB");
        }
//        HDD：  GB
            EditText editTextHDD = (EditText) findViewById(R.id.HDD);
            HDD = String.valueOf(editTextHDD.getText());
            HDDTitle = getResources().getString(R.string.HDD);
            if(!HDD.equals("")) {
                textFormPC.add(HDDTitle + HDD + "GB");
            }
//        ディスプレイ：  インチ
                EditText editTextDisplay = (EditText) findViewById(R.id.display);
                display = String.valueOf(editTextHDD.getText());
                displayTitle = getResources().getString(R.string.display);
                if(!display.equals("")) {
                    textFormPC.add(displayTitle + display + "インチ");
                }

//        商品状態
               Spinner spinnerConditionPC = (Spinner)findViewById(R.id.conditionList);
               condition = (String) spinnerConditionPC.getSelectedItem();
               conditionTitle = getResources().getString(R.string.condition);
               if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
                   textFormPC.add(conditionTitle+condition);
               }else if(condition.equals("自分で入力する")){
                   textFormPC.add(conditionTitle);
               }
//        コメント
         EditText editTextCommentPC = (EditText) findViewById(R.id.PCComment);
         PCcomment = String.valueOf(editTextCommentPC.getText());
         PCmodelTitle = getResources().getString(R.string.comment);
         if(!PCcomment.equals("")) {
             textFormPC.add(PCcommentTitle + PCcomment);
         }


        // ----------------------------家電----------------------------


//        メーカー
          editTextComment = (EditText) findViewById(R.id.appMaker);
          PCcomment = String.valueOf(editTextComment.getText());
          PCmodelTitle = getResources().getString(R.string.phoneMaker);
          if(!PCcomment.equals("")) {
              textFormAP.add(PCmodelTitle + PCcomment);
          }
//        型番
          editTextComment = (EditText) findViewById(R.id.appModel);
          PCcomment = String.valueOf(editTextComment.getText());
          PCmodelTitle = getResources().getString(R.string.appModel);
          if(!PCcomment.equals("")) {
              textFormAP.add(PCmodelTitle + PCcomment);
          }
//        製造年月
          editTextComment = (EditText) findViewById(R.id.makeYear);
          EditText makeMonth = (EditText) findViewById(R.id.makeMonth) ;
          PCcomment = String.valueOf(editTextComment.getText());
          PCmodel = String.valueOf(makeMonth.getText());
          PCmodelTitle = getResources().getString(R.string.makeDate);
          if(!PCcomment.equals("")&&!PCmodel.equals("")) {
              textFormAP.add(PCmodelTitle + PCcomment + "年" + PCmodel + "月");
          }else if(!PCcomment.equals("")&&PCmodel.equals("")){
              textFormAP.add(PCmodelTitle + PCcomment + "年" );
          }
//        色
         spinnerPCColor = (Spinner)findViewById(R.id.appColorList);
        color = (String) spinnerPCColor.getSelectedItem();
        colorTitle = getResources().getString(R.string.color);
        if(!color.equals("選択してください。")&&!color.equals("自分で入力する")){
            textFormAP.add(colorTitle+color);
        }else if(color.equals("自分で入力する")){
            textFormAP.add(colorTitle);
        }
//        付属品
        editTextComment = (EditText) findViewById(R.id.appAC);
        PCcomment = String.valueOf(editTextComment.getText());
        PCmodelTitle = getResources().getString(R.string.phoneAC);
        if(!PCcomment.equals("")) {
            textFormAP.add(PCmodelTitle + PCcomment);
        }
//        商品状態
        spinnerConditionPC = (Spinner)findViewById(R.id.AppConditionList);
        condition = (String) spinnerConditionPC.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textFormAP.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textFormAP.add(conditionTitle);
        }
//        コメント
        editTextCommentPC = (EditText) findViewById(R.id.appComment);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.comment);
        if(!PCcomment.equals("")) {
            textFormAP.add(PCmodelTitle + PCcomment);
        }


        // ----------------------------DVD, Blu-ray----------------------------

//        タイトル
        editTextCommentPC = (EditText) findViewById(R.id.DVDtitle);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.DVDtitle);
        if(!PCcomment.equals("")) {
            textFormDV.add(PCmodelTitle + PCcomment);
        }
//        商品状態：
        spinnerConditionPC = (Spinner)findViewById(R.id.DVDConditionList);
        condition = (String) spinnerConditionPC.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textFormDV.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textFormDV.add(conditionTitle);
        }
//        コメント
        editTextCommentPC = (EditText) findViewById(R.id.DVDcomment);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.comment);
        if(!PCcomment.equals("")) {
            textFormDV.add(PCmodelTitle + PCcomment);
        }

        // ----------------------------本、雑誌、漫画----------------------------

//        タイトル
        editTextCommentPC = (EditText) findViewById(R.id.bookTitle);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.DVDtitle);
        if(!PCcomment.equals("")) {
            textFormBK.add(PCmodelTitle + PCcomment);
        }
//        著者：
        editTextCommentPC = (EditText) findViewById(R.id.bookAuthor);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.author);
        if(!PCcomment.equals("")) {
            textFormBK.add(PCmodelTitle + PCcomment);
        }
//        巻数：
        editTextComment = (EditText) findViewById(R.id.firstVolume);
        makeMonth = (EditText) findViewById(R.id.secondVolume) ;
        PCcomment = String.valueOf(editTextComment.getText());
        PCmodel = String.valueOf(makeMonth.getText());
        PCmodelTitle = getResources().getString(R.string.volume);
        if(!PCcomment.equals("")&&!PCmodel.equals("")) {
            textFormBK.add(PCmodelTitle + PCcomment + "巻〜" + PCmodel + "巻");
        }else if(!PCcomment.equals("")&&PCmodel.equals("")){
            textFormBK.add(PCmodelTitle + PCcomment + "巻");
        }
//        商品状態：
        spinnerConditionPC = (Spinner)findViewById(R.id.bookConditionList);
        condition = (String) spinnerConditionPC.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textFormBK.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textFormBK.add(conditionTitle);
        }
//        コメント
        editTextCommentPC = (EditText) findViewById(R.id.bookComment);
        PCcomment = String.valueOf(editTextCommentPC.getText());
        PCmodelTitle = getResources().getString(R.string.comment);
        if(!PCcomment.equals("")) {
            textFormBK.add(PCmodelTitle + PCcomment);
        }



        // ----------------------------その他----------------------------
//
//        商品名：
        editTextName = (EditText) findViewById(R.id.nameOthers);
        name = String.valueOf(editTextName.getText());
        nameTitle = getResources().getString(R.string.name);
        if(!name.equals("")){
            textFormOT.add(nameTitle+name);
        }
//        色：
        spinnerPCColor = (Spinner)findViewById(R.id.othersColorList);
        color = (String) spinnerPCColor.getSelectedItem();
        colorTitle = getResources().getString(R.string.color);
        if(!color.equals("選択してください。")&&!color.equals("自分で入力する")){
            textFormOT.add(colorTitle+color);
        }else if(color.equals("自分で入力する")){
            textFormOT.add(colorTitle);
        }
//        状態：
        spinnerConditionPC = (Spinner)findViewById(R.id.othersConditionList);
        condition = (String) spinnerConditionPC.getSelectedItem();
        conditionTitle = getResources().getString(R.string.condition);
        if(!condition.equals("選択してください。")&&!condition.equals("自分で入力する")){
            textFormOT.add(conditionTitle+condition);
        }else if(condition.equals("自分で入力する")){
            textFormOT.add(conditionTitle);
        }


        //【発送方法】
        // 発送方法
        Spinner spinnerShip = (Spinner)findViewById(R.id.shipList);
        ship = (String) spinnerShip.getSelectedItem();
        shipTitle = getResources().getString(R.string.ship);

        if(!ship.equals("選択してください。")&&!ship.equals("自分で入力する")){
            textForm2.add(shipTitle+ship);
        }else if(ship.equals("自分で入力する")){
            textForm2.add(shipTitle);
        }


        //【その他】
        // 支払い方法
        textPay.clear();
        for(int i = 0; i < checkPay.length;i++){
            if(checkPay[i].isChecked()){
                textPay.add(stringPay[i]);
            }
        }
        if(textPay.size()>0){
            if(textPay.get(0).equals("自分で入力")){
                textForm3.add("支払い方法：");
            }else {
                textForm3.add("支払い方法：" + textPay.get(0));
                for (int i = 1; i < textPay.size(); i++) {
                    if(!textPay.get(i).equals("自分で入力")) {
                        textForm3.add("　　　　　　" + textPay.get(i));
                    }
                }
            }
        }






        // put text into text box

        EditText format = (EditText) findViewById(R.id.format); // get text box
        format.setText(""); // clear text box

        format.append("【商品詳細】\n"); // add text into text box


        //スピナー取得
        Spinner spinner = (Spinner) findViewById(R.id.formatSort);
        // 選択したアイテムを取得
        String item = (String) spinner.getSelectedItem();

        // 選択したアイテムごとにフォームを有効化する。
        switch (item) {
            case "ファッション":
                for (int i = 0; i < textForm.size(); i++) { // add components in 商品詳細 into text box
                    if (textForm.get(i) != "") {
                        format.append(textForm.get(i) + "\n");
                    }
                }
                break;
            case "スマホ、タブレット":
                for (int i = 0; i < textFormSP.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormSP.get(i) != "") {
                        format.append(textFormSP.get(i) + "\n");
                    }
                }
                break;
            case "PC":
                for (int i = 0; i < textFormPC.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormPC.get(i) != "") {
                        format.append(textFormPC.get(i) + "\n");
                    }
                }
                break;
            case "家電":
                for (int i = 0; i < textFormAP.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormAP.get(i) != "") {
                        format.append(textFormAP.get(i) + "\n");
                    }
                }
                break;
            case "DVD,Blu-ray":
                for (int i = 0; i < textFormDV.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormDV.get(i) != "") {
                        format.append(textFormDV.get(i) + "\n");
                    }
                }
                break;
            case "本、雑誌、漫画":
                for (int i = 0; i < textFormBK.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormBK.get(i) != "") {
                        format.append(textFormBK.get(i) + "\n");
                    }
                }
                break;
            case "その他":
                for (int i = 0; i < textFormOT.size(); i++) { // add components in 商品詳細 into text box
                    if (textFormOT.get(i) != "") {
                        format.append(textFormOT.get(i) + "\n");
                    }
                }
                break;
        }





        format.append("\n【発送方法】\n");// add text into text box

        for(int j = 0; j < textForm2.size(); j++){// add components in 発送方法 into text box
            if(textForm2.get(j) != ""){
                format.append(textForm2.get(j)+"\n");
            }
        }

        format.append("\n【その他】\n");// add text into text box

        for(int j = 0; j < textForm3.size(); j++){// add components in 発送方法 into text box
            if(textForm3.get(j) != ""){
                format.append(textForm3.get(j)+"\n");
            }
        }

        Toast.makeText(this, "テキストボックスに挿入しました。", Toast.LENGTH_SHORT).show();

    }


    public void Copy(View view){
        EditText editText = (EditText)findViewById(R.id.format);
        ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

        // コピーするデータをエディットテキストから取得する
        ClipData clip = ClipData.newPlainText("copied_text", editText.getText().toString());
        // クリップボードに内容をコピーする
        clipboardManager.setPrimaryClip(clip);
        // toast shows
        Toast.makeText(this, "コピーしました。", Toast.LENGTH_SHORT).show();
    }






}