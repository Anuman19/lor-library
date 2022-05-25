package ch.bbcag.lorlibrary;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bbcag.lorlibrary.model.Card;

public class CustomAdapter extends ArrayAdapter<Card> {
  public CustomAdapter(Context context, int id, ArrayList<Card> cards) {
    super(context, id, cards);
  }

//  @Override
//  public View getView(int position, View convertView, ViewGroup parent){
//    TextView test = (TextView)convertView.findViewById(R.id.)
//  }
}
