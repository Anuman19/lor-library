package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.InputStream;

public class CardImageTest extends AppCompatActivity {

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_image_test);
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
}
