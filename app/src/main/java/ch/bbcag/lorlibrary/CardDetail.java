package ch.bbcag.lorlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class CardDetail extends AppCompatActivity {

  private ActionBarDrawerToggle actionBarDrawerToggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_detail);

    // Navigation
    NavigationView navigationView = findViewById(R.id.detail_navigation_view);
    navigationView.setNavigationItemSelectedListener(
        item -> {
          if (item.getItemId() == R.id.main_page) {
            Intent intent = new Intent(CardDetail.this, MainActivity.class);
            startActivity(intent);
          } else {
            Intent intent = new Intent(CardDetail.this, Overview.class);
            startActivity(intent);
          }
          return true;
        });

    // burger menu
    DrawerLayout drawerLayout = findViewById(R.id.my_drawer_layout_detail);
    actionBarDrawerToggle =
        new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
    drawerLayout.addDrawerListener(actionBarDrawerToggle);
    actionBarDrawerToggle.syncState();
    Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    // get Data from intent
    Intent intent = getIntent();
    String gameAbsolutePath = intent.getStringExtra("cardImage");
    String fullAbsolutePath = intent.getStringExtra("banner");
    String name = intent.getStringExtra("name");
    String descriptionRaw = intent.getStringExtra("descriptionRaw");
    String levelupDescriptionRaw = intent.getStringExtra("levelupDescriptionRaw");
    String flavorText = intent.getStringExtra("flavorText");
    String rarityRef = intent.getStringExtra("rarityRef");
    String type = intent.getStringExtra("type");
    String firstRegion = intent.getStringExtra("firstRegion");
    String info = firstRegion + ", " + type;

    // load images
    Picasso.with(getApplicationContext())
        .load(fullAbsolutePath)
        .noFade()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher)
        .into((ImageView) findViewById(R.id.card_banner));

    Picasso.with(getApplicationContext())
        .load(gameAbsolutePath)
        .noFade()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher)
        .into((ImageView) findViewById(R.id.card_image));

    TextView cardName = findViewById(R.id.card_name);
    cardName.setText(name);

    TextView subTitle = findViewById(R.id.sub_title);
    subTitle.setText(name);

    TextView cardInfo = findViewById(R.id.card_info);
    cardInfo.setText(info);

    TextView cardDescription = findViewById(R.id.card_description);
    cardDescription.setText(descriptionRaw);

    TextView cardFlavor = findViewById(R.id.card_flavor);
    cardFlavor.setText(flavorText);

    if (rarityRef.equals("Champion")) {
      TextView subTitle2 = findViewById(R.id.sub_title2);
      subTitle2.setText("Level Up");

      TextView levelUp = findViewById(R.id.level_up);
      levelUp.setText(levelupDescriptionRaw);
    }
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
