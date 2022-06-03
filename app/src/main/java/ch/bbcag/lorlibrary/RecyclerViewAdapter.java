package ch.bbcag.lorlibrary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ch.bbcag.lorlibrary.model.Card;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

  private final List<Card> cards;
  private final Context context;
  private final ClickListener clickListener;

  public RecyclerViewAdapter(Context context, List<Card> cards, ClickListener clickListener) {
    this.context = context;
    this.cards = cards;
    this.clickListener = clickListener;
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

    // get data for list
    final Card card = cards.get(position);

    holder.title.setText(card.getName());

    // load image
    Picasso.with(context)
        .load(card.getCardImage())
        .noFade()
        .placeholder(R.mipmap.ic_launcher_round)
        .error(R.mipmap.ic_launcher)
        .into(holder.image);

    // set on click for detail view
    holder.cardView.setOnClickListener(data -> clickListener.onItemClick(position));
  }

  @Override
  public int getItemCount() {
    return cards.size();
  }

  public static class MyViewHolder extends RecyclerView.ViewHolder {
    private final TextView title;
    private final ImageView image;
    private final CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.card_name);
      image = itemView.findViewById(R.id.card_image);
      cardView = itemView.findViewById(R.id.card_view);
    }

    public TextView getTitle() {
      return title;
    }
  }
}
