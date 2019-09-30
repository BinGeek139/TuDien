package com.tuantm2703.demodictionary.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.tuantm2703.demodictionary.Adapter.AutoCompleteVietnameseText;
import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

import java.util.ArrayList;
import java.util.WeakHashMap;

public class VietnameseDictionary extends AppCompatActivity {

    AutoCompleteTextView actvVietnameseCompleteText;
    AutoCompleteVietnameseText autoCompleteVietnameseText;
    ArrayList<Word> vietnameseWordList;
    String DB_NAME = "DbTestdictionary2.db";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vietnamese_dictionary);
        Init();
        RegisterEvent();
    }

    private void RegisterEvent() {
        autoCompleteVietnameseText = new AutoCompleteVietnameseText(this,vietnameseWordList);
        actvVietnameseCompleteText.setAdapter(autoCompleteVietnameseText);

        autoCompleteVietnameseText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(VietnameseDictionary.this,EnglishDetail.class);
                intent.putExtra("work2", autoCompleteVietnameseText.getItem(i));
                startActivity(intent);
            }
        });
    }

    private void Init() {
        actvVietnameseCompleteText =findViewById(R.id.actv_autoCompleteVietnamesText);
        vietnameseWordList = new ArrayList<Word>();
        LoadVietnameseData();
    }

    private void LoadVietnameseData() {
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT * FROM TestDic",null);
        while(cursor.moveToNext()){
            int ID=cursor.getInt(0);
            String english=cursor.getString(1);
            String vietnamese=cursor.getString(2);
            String explanation=cursor.getString(3);
            Word word = new Word(ID,english,vietnamese,explanation);

            vietnameseWordList.add(word);
        }
        cursor.close();
    }

}
