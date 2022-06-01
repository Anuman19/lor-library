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

        new DownloadImageFromInternet(
                getApplicationContext(), (ImageView) findViewById(R.id.card_banner))
                .execute(fullAbsolutePath);

        new DownloadImageFromInternet(
                getApplicationContext(), (ImageView) findViewById(R.id.card_image))
                .execute(gameAbsolutePath);

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

    //    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
    //        ImageView imageView;
    //
    //        public DownloadImageFromInternet(ImageView imageView) {
    //            this.imageView = imageView;
    //            Toast.makeText(
    //                            getApplicationContext(),
    //                            "Please wait, it may take a few minutes...",
    //                            Toast.LENGTH_SHORT)
    //                    .show();
    //        }
    //
    //        protected Bitmap doInBackground(String... urls) {
    //            String imageURL = urls[0];
    //            Bitmap bimage = null;
    //            try {
    //                InputStream in = new java.net.URL(imageURL).openStream();
    //                bimage = BitmapFactory.decodeStream(in);
    //            } catch (Exception e) {
    //                Log.e("Error Message", e.getMessage());
    //                e.printStackTrace();
    //            }
    //            return bimage;
    //        }
    //
    //        protected void onPostExecute(Bitmap result) {
    //            imageView.setImageBitmap(result);
    //        }
    //    }
}
