package ch.bbcag.lorlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

  private Card[] cards;
  private List<Card> randomCardList;
  private Random random = new Random();
  private DrawerLayout drawerLayout;
  private ActionBarDrawerToggle actionBarDrawerToggle;
  private List<Integer> randomNumbers = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // Navigation

    NavigationView navigationView = findViewById(R.id.main_navigation_view);
    navigationView.setNavigationItemSelectedListener(
        item -> {
          if (item.getItemId() == R.id.main_page) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
          } else {
            Intent intent = new Intent(MainActivity.this, Overview.class);
            startActivity(intent);
          }
          return true;
        });
    drawerLayout = findViewById(R.id.my_drawer_layout);
    actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    // random Data

    getCardsFromFile();
    RecyclerView recyclerView = findViewById(R.id.main_recycle_view);
    RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(linearLayoutManager);
    randomCardList = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      int randomNumber = random.nextInt(cards.length + 1);

      // get the right id for detail view
      randomNumbers.add(randomNumber);
      randomCardList.add(cards[randomNumber]);
    }

    ClickListener onClickListener =
        position -> {
          Intent intent = new Intent(this, CardDetail.class);
          Card selected = cards[randomNumbers.get(position)];

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

    RecyclerViewAdapter recyclerViewAdapter =
        new RecyclerViewAdapter(MainActivity.this, randomCardList, onClickListener);

    recyclerView.setAdapter(recyclerViewAdapter);
  }

  private void getCardsFromFile() {

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
    cards = gson.fromJson(string, Card[].class);
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {

    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
