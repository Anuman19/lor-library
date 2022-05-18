package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method loads all the cards from the json file into Card Objects to then display
    private void addCardsToClickableList() throws IOException {
        ListView cards = findViewById(R.id.cardList);

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

        ArrayAdapter<Card> cardAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1);
        cardAdapter.addAll(gson.fromJson(string, Card[].class));
        cards.setAdapter(cardAdapter);
    }
}