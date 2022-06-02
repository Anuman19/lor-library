package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    System.out.println("test");
    addCardsToClickableList();
  }

  // This method loads all the cards from the json file into Card Objects to then display
  private void addCardsToClickableList() {

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

    Card[] cards = gson.fromJson(string, Card[].class);
    ClickListener onClickListener =
        position -> {
          Intent intent = new Intent(this, CardImageTest.class);
          Card selected = cards[position];

          intent.putExtra("cardCode", selected.getCardCode());
          intent.putExtra("name", selected.getName());
          intent.putExtra("cardImage", selected.getCardImage());
          intent.putExtra("banner", selected.getBanner());
          intent.putExtra("descriptionRaw", selected.getDescriptionRaw());
          intent.putExtra("levelupDescriptionRaw", selected.getLevelupDescriptionRaw());
          intent.putExtra("flavorText", selected.getFlavorText());
          intent.putExtra("artistName", selected.getArtistName());
          intent.putExtra("rarityRef", selected.getRarityRef());
          intent.putExtra("type", selected.getType());
          intent.putExtra("firstRegion", selected.getFirstRegion());

          Bundle args = new Bundle();
          startActivity(intent);
        };

    RecyclerView recyclerView = findViewById(R.id.recycle_view);
    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, cards, onClickListener);
    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
    recyclerView.setAdapter(recyclerViewAdapter);
  }
}
