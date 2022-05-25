package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class CardImageTest extends AppCompatActivity {

  private String cardCode;
  private String gameAbsolutePath;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_card_image_test);
    Intent intent = getIntent();
    cardCode = intent.getStringExtra("cardCode");
    gameAbsolutePath = intent.getStringArrayExtra("assets")[0];

    new DownloadImageFromInternet(
            getApplicationContext(), (ImageView) findViewById(R.id.image_view))
        .execute(gameAbsolutePath);
  }
}
