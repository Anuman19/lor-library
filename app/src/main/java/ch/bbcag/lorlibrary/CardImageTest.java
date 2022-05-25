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

import ch.bbcag.lorlibrary.model.Card;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_image_test);
        Intent intent = getIntent();
        cardCode = intent.getStringExtra("cardCode");
        gameAbsolutePath = intent.getStringExtra("cardImage");
        fullAbsolutePath = intent.getStringExtra("banner");

        new DownloadImageFromInternet((ImageView) findViewById(R.id.card_banner))
                .execute(fullAbsolutePath);

        new DownloadImageFromInternet((ImageView) findViewById(R.id.card_image))
                .execute(gameAbsolutePath);
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;

        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
            Toast.makeText(
                            getApplicationContext(),
                            "Please wait, it may take a few minutes...",
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
