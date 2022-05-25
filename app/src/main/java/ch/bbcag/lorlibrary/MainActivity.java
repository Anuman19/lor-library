package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    try {
      addCardsToClickableList();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // This method loads all the cards from the json file into Card Objects to then display
  private void addCardsToClickableList() throws IOException {
    //ListView cards = findViewById(R.id.cardList);

    String string = "";
    try {
      InputStream inputStream = getAssets().open("set1-en_us.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      string = new String(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    }

    Gson gson = new Gson();
    ArrayAdapter<Card> cardAdapter =
        new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
    cardAdapter.addAll(gson.fromJson(string, Card[].class));
    // cards.setAdapter(cardAdapter);

    new DownloadImageFromInternet(getApplicationContext(), findViewById(R.id.card_image))
        .execute(cardAdapter.getItem(0).getAssetsString());
    TextView cardTitle = (TextView) findViewById(R.id.card_name);
    cardTitle.setText(cardAdapter.getItem(0).getName());

    //    for (int i = 0; i < cardAdapter.getCount(); i++) {
    //      new DownloadImageFromInternet(getApplicationContext(), findViewById(R.id.image_test))
    //          .execute(cardAdapter.getItem(i).getAssetsString());
    //    }

//    AdapterView.OnItemClickListener mListClickedHandler =
//        (parent, v, position, id) -> {
//          Intent intent = new Intent(getApplicationContext(), CardImageTest.class);
//          Card selected = (Card) parent.getItemAtPosition(position);
//          intent.putExtra("cardCode", selected.getCardCode());
//          intent.putExtra("name", selected.getName());
//          Bundle args = new Bundle();
//          // args.putSerializable("assets", selected.getAssets());
//          intent.putExtra("assets", selected.getAssetsString());
//          System.out.println(selected);
//          startActivity(intent);
//        };
//    cards.setOnItemClickListener(mListClickedHandler);
  }
}
