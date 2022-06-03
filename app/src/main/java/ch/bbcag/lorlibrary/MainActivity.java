package ch.bbcag.lorlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private List<Card> cardList;
  private int start = 0;
  private int count;
  private Card[] cards;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    addCardsToClickableList();
  }

  // This method loads all the cards from the json file into Card Objects to then display
  private void addCardsToClickableList() {

    String string = "";
    InputStream inputStream = null;
    try {
      inputStream = getAssets().open("set1-en_us.json");
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      string = new String(buffer);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      assert inputStream != null;
      try {
        inputStream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Gson gson = new Gson();

    cardList = new ArrayList<>();
    cards = gson.fromJson(string, Card[].class);

    NestedScrollView nestedSV = findViewById(R.id.nested_scroll_view);
    recyclerView = findViewById(R.id.recycle_view);
    progressBar = findViewById(R.id.progress_bar);
    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);

    loadFiveCards();
    nestedSV.setOnScrollChangeListener(
        (NestedScrollView.OnScrollChangeListener)
            (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
              // on scroll change we are checking when users scroll as bottom.
              if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                // in this method we are incrementing page number,
                // making progress bar visible and calling get data method.
                count++;

                // on below line we are making our progress bar visible.
                progressBar.setVisibility(View.VISIBLE);
                System.out.println("new page");
                if (count < ((cards.length + 1) / 5)) {
                  // on below line we are again calling
                  // a method to load data in our array list.
                  loadFiveCards();
                } else {
                  progressBar.setVisibility(View.INVISIBLE);
                }
              }
            });
  }

  private void loadFiveCards() {

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

    recyclerView.setVisibility(View.VISIBLE);

    for (int i = start; i < start + 5; i++) {
      if (i == 419) {
        break;
      }
      cardList.add(cards[i]);

      RecyclerViewAdapter recyclerViewAdapter =
          new RecyclerViewAdapter(MainActivity.this, cardList, onClickListener);

      recyclerView.setAdapter(recyclerViewAdapter);
    }
    start += 5;
  }
}
