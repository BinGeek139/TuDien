package com.tuantm2703.demodictionary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoCompleteVietnameseText extends ArrayAdapter<Word> {

    Context context;
    ArrayList<Word> vietnameseFilterList,baseList;
    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

    public AutoCompleteVietnameseText(Context context, ArrayList<Word> vietnameseFilterList) {
        super(context, 0, vietnameseFilterList);
        this.context = context;
        this.vietnameseFilterList = vietnameseFilterList;
        baseList = new ArrayList<Word>(vietnameseFilterList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_word,parent,false);
        }
        TextView tvItemVietnamese = convertView.findViewById(R.id.tv_item_word);
        //Word word = getItem(position);
        Word word = vietnameseFilterList.get(position);
        if (word!=null){
            tvItemVietnamese.setText(word.getVietnamese());
        }
        //setOclick phai khai bao o trong getView
        tvItemVietnamese.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public Filter getFilter() {
        return vietnameseFiter;
    }

    Filter vietnameseFiter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults filterResults = new FilterResults();
            ArrayList<Word> vietnameseSuggestions = new ArrayList<Word>();
            if (charSequence!=null){
                String curremtFilter = charSequence.toString().toLowerCase().trim();
                for (Word word : baseList){
                    if (word.getVietnamese().contains(curremtFilter)){
                        vietnameseSuggestions.add(word);
                    }
                }
            }
            filterResults.values = vietnameseSuggestions;
            filterResults.count = vietnameseSuggestions.size();
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            vietnameseFilterList.clear();
            vietnameseFilterList.addAll((List<Word>) filterResults.values);
            notifyDataSetChanged();
        }

    };
}
