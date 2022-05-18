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
    cardCode = intent.getStringExtra("nameCode");
    gameAbsolutePath = intent.getStringArrayExtra("assets")[0];

    new DownloadImageFromInternet((ImageView) findViewById(R.id.image_view))
        .execute(gameAbsolutePath);
  }

  private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
    ImageView imageView;

    public DownloadImageFromInternet(ImageView imageView) {
      this.imageView = imageView;
      Toast.makeText(
              getApplicationContext(),
              "Please wait, it may take a few minute...",
              Toast.LENGTH_SHORT)
          .show();
    }

    protected Bitmap doInBackground(String... urls) {
      String imageURL = urls[0];
      Bitmap bimage = null;
      try {
        InputStream in = new java.net.URL(imageURL).openStream();
        bimage = BitmapFactory.decodeStream(in);
      } catch (Exception e) {
        Log.e("Error Message", e.getMessage());
        e.printStackTrace();
      }
      return bimage;
    }

    protected void onPostExecute(Bitmap result) {
      imageView.setImageBitmap(result);
    }
  }
}
