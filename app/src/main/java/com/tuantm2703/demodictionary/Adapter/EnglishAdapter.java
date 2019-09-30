package com.tuantm2703.demodictionary.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tuantm2703.demodictionary.Model.Word;
import com.tuantm2703.demodictionary.R;

import java.util.List;
import java.util.zip.Inflater;

public class EnglishAdapter extends ArrayAdapter<Word> {

    Context context;
    List<Word> wordList;
    Integer resource;

    public EnglishAdapter(Context context, int resource, List<Word> wordList) {
        super(context, resource, wordList);
        this.context = context;
        this.wordList = wordList;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_word,parent,false);
            viewHolder.tvItemEnglish=convertView.findViewById(R.id.tv_item_word);
            viewHolder.vLine=convertView.findViewById(R.id.v_Item_Line);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag ();
        }

        Word word = wordList.get(position);
        viewHolder.tvItemEnglish.setText(word.getEnglish().trim());
        viewHolder.vLine.setVisibility(View.VISIBLE);
        return  convertView;
    }

    public class ViewHolder{
        TextView tvItemEnglish;
        View vLine;
    }
}
