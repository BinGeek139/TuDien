package com.tuantm2703.demodictionary.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

public class EnglishDetail extends AppCompatActivity {

    TextView tvFirstWord,tvSecondWord,tvExplanation;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        Init();
        RegisterEvent();
    }

    private void RegisterEvent() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnglishDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Init() {
        tvFirstWord=findViewById(R.id.tv_Detail_FirstWord);
        tvSecondWord=findViewById(R.id.tv_Detail_SecondWord);
        tvExplanation=findViewById(R.id.tv_Detail_Explanation);
        btnBack=findViewById(R.id.btn_Detail_Back);
        ReceiveWord();
    }

    private void ReceiveWord() {

        if (getIntent().hasExtra("work")){
            Word word = (Word) getIntent().getSerializableExtra("work");
            tvFirstWord.setText(word.getEnglish());
            tvSecondWord.setText(word.getVietnamese());
            tvExplanation.setText(word.getExplanation());
        }
        else if(getIntent().hasExtra("SendEnglishWord")){
            Bundle bundle = getIntent().getExtras();
            Word word = (Word) bundle.getSerializable("SendEnglishWord");
            tvFirstWord.setText(word.getEnglish());
            tvSecondWord.setText(word.getVietnamese());
            tvExplanation.setText(word.getExplanation());
        }
        else if (getIntent().hasExtra("work2")){
            Word word = (Word) getIntent().getSerializableExtra("work2");
            tvSecondWord.setText(word.getEnglish());
            tvFirstWord.setText(word.getVietnamese());
            tvExplanation.setText(word.getExplanation());
        }
    }
}
