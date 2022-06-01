package ch.bbcag.lorlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import ch.bbcag.lorlibrary.model.Card;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("test");
        addCardsToClickableList();
    }

    // This method loads all the cards from the json file into Card Objects to then display
    private void addCardsToClickableList() {

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

        Card[] cards = gson.fromJson(string, Card[].class);
        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(this, cards);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListener(
                new ClickListener<Card>() {
                    @Override
                    public void onItemClick(Card data) {
                        Toast.makeText(MainActivity.this, data.getName(), Toast.LENGTH_SHORT).show();
                    }
                });

        AdapterView.OnItemClickListener mListClickedHandler = (parent, v, position, id) -> {
            Intent intent = new Intent(this, CardImageTest.class);
            Card selected = (Card) parent.getItemAtPosition(position);

            // pass Extras to DetailPage
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

        gson.fromJson(string, Card[].class);
    }
}
