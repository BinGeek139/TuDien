package com.tuantm2703.demodictionary.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.Toast;

import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteEnglishText extends ArrayAdapter<Word> {

    Context context ;
    ArrayList<Word> englishFilterList, baseList;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    public AutoCompleteEnglishText(Context context, ArrayList<Word> englishFilterList) {
        super(context, 0, englishFilterList);
        this.context = context;
        this.englishFilterList = englishFilterList;
        baseList = new ArrayList<>(englishFilterList);
    }

    @Override
    public Filter getFilter() {
        return englishFilter;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_word,parent,false);

        }
        TextView tvItemEnglish = convertView.findViewById(R.id.tv_item_word);
        //Word word = getItem(position);
        Word word = englishFilterList.get(position);
        if (word!=null){
            tvItemEnglish.setText(word.getEnglish());
        }
        //setOclick phai khai bao o trong getView
        tvItemEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(null, view, position, 0);

            }
        });
        return  convertView;

    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    Filter englishFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            List<Word> englishSuggestion = new ArrayList<Word>();
            if (charSequence!=null){
                String currentFilter = charSequence.toString().toLowerCase().trim();
                for (Word word: baseList){
                    if (word.getEnglish().toLowerCase().contains(currentFilter)){
                        englishSuggestion.add(word);
                    }
                }
            }
            filterResults.values = englishSuggestion;
            filterResults.count = englishSuggestion.size();
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            // da kiem tra 2 list filter va lít du lieu goc
            englishFilterList.clear();
            englishFilterList.addAll((List) filterResults.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Word)resultValue).getEnglish();
        }
    };
}
