package com.tuantm2703.demodictionary.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.tuantm2703.demodictionary.Adapter.AutoCompleteEnglishText;
import com.tuantm2703.demodictionary.Adapter.EnglishAdapter;
import com.tuantm2703.demodictionary.DbHelper;
import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    List<Word> wordList;
    ListView lvDictionary;
    EnglishAdapter englishAdapter;
    AutoCompleteTextView actvSearchWord;
    Button btnGoToVietnameseDictionary;

    private static final String DB_NAME="DbTestdictionary2.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        RegisterEvent();
    }

    private void RegisterEvent() {

        lvDictionary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = wordList.get(i);
                Intent intent = new Intent(MainActivity.this, EnglishDetail.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SendEnglishWord",word);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //
        btnGoToVietnameseDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,VietnameseDictionary.class);
                startActivity(intent);
            }
        });

        AutoCompleteEnglishText();

    }

    private void AutoCompleteEnglishText() {
        final AutoCompleteEnglishText autoCompleteEnglishText = new AutoCompleteEnglishText(this, new ArrayList<Word>(wordList));
        actvSearchWord.setAdapter(autoCompleteEnglishText);
        autoCompleteEnglishText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,EnglishDetail.class);
                intent.putExtra("work", autoCompleteEnglishText.getItem(i));
                startActivity(intent);
            }
        });
    }

    private void Init() {
        dbHelper = new DbHelper(getApplicationContext());
        actvSearchWord = findViewById(R.id.edt_search);
        btnGoToVietnameseDictionary=findViewById(R.id.btn_Go_To_VietnameseDictionary);

        lvDictionary = findViewById(R.id.lv_Dictionary);
        wordList = new ArrayList<Word>();
        englishAdapter = new EnglishAdapter(MainActivity.this,R.layout.item_word,wordList);
        lvDictionary.setAdapter(englishAdapter);

        showDataFromTableTestDic();

    }

    private void showDataFromTableTestDic() {
        SQLiteDatabase database = openOrCreateDatabase(DB_NAME,MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("SELECT * FROM TestDic",null);
        while(cursor.moveToNext()){
            int ID=cursor.getInt(0);
            String english=cursor.getString(1);
            String vietnamese=cursor.getString(2);
            String explanation=cursor.getString(3);
            Word word = new Word(ID,english,vietnamese,explanation);

            wordList.add(word);
    }
        cursor.close();
        englishAdapter.notifyDataSetChanged();
    }

}

