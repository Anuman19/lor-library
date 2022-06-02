package ch.bbcag.lorlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

import ch.bbcag.lorlibrary.model.Card;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

  private Card[] cards;
  private Context context;
  private ClickListener clickListener;

  public RecyclerViewAdapter(Context context, Card[] cards, ClickListener clickListener) {
    this.context = context;
    this.cards = cards;
    this.clickListener = clickListener;
    System.out.println(Arrays.toString(cards));
  }

  @NonNull
  @Override
  public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(
      @NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(
      @NonNull RecyclerViewAdapter.MyViewHolder holder, final int position) {
    final Card card = cards[position];
    holder.title.setText(card.getName());

    new DownloadImageFromInternet(holder.image.getContext(), holder.image)
        .execute(card.getCardImage());
    holder.cardView.setOnClickListener(
        data -> {
          clickListener.onItemClick(position);
        });
  }

  @Override
  public int getItemCount() {
    return cards.length;
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView image;
    private CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.card_name);
      image = itemView.findViewById(R.id.card_image);
      cardView = itemView.findViewById(R.id.card_view);
    }

    public TextView getTitle() {
      return title;
    }

    public ImageView getImage() {
      return image;
    }

    public CardView getCardView() {
      return cardView;
    }
  }
}
