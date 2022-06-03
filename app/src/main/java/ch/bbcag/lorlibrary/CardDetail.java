package ch.bbcag.lorlibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class CardDetail extends AppCompatActivity {

  private String cardCode;
  private String gameAbsolutePath;
  private String fullAbsolutePath;
  private String name;
  private String descriptionRaw;
  private String levelupDescriptionRaw;
  private String flavorText;
  private String artistName;
  private String rarityRef;
  private String type;
  private String firstRegion;
  private Bitmap imageBitmap;

  private ActionBarDrawerToggle actionBarDrawerToggle;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_detail);

    // Navigation
    NavigationView navigationView = findViewById(R.id.detail_navigation_view);
    System.out.println(navigationView.getId());
    navigationView.setNavigationItemSelectedListener(
        item -> {
          System.out.println(item);
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
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    Intent intent = getIntent();
    cardCode = intent.getStringExtra("cardCode");
    gameAbsolutePath = intent.getStringExtra("cardImage");
    fullAbsolutePath = intent.getStringExtra("banner");
    name = intent.getStringExtra("name");
    descriptionRaw = intent.getStringExtra("descriptionRaw");
    levelupDescriptionRaw = intent.getStringExtra("levelupDescriptionRaw");
    flavorText = intent.getStringExtra("flavorText");
    artistName = intent.getStringExtra("artistName");
    rarityRef = intent.getStringExtra("rarityRef");
    type = intent.getStringExtra("type");
    firstRegion = intent.getStringExtra("firstRegion");

    String info = firstRegion + ", " + type;

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

    TextView cardName = (TextView) findViewById(R.id.card_name);
    cardName.setText(name);

    TextView subTitle = (TextView) findViewById(R.id.sub_title);
    subTitle.setText(name);

    TextView cardInfo = (TextView) findViewById(R.id.card_info);
    cardInfo.setText(info);

    TextView cardDescription = (TextView) findViewById(R.id.card_description);
    cardDescription.setText(descriptionRaw);

    TextView cardFlavor = (TextView) findViewById(R.id.card_flavor);
    cardFlavor.setText(flavorText);

    if (rarityRef.equals("Champion")) {
      TextView subTitle2 = (TextView) findViewById(R.id.sub_title2);
      subTitle2.setText("Level Up");

      TextView levelUp = (TextView) findViewById(R.id.level_up);
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
