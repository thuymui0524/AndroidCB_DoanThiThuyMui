package com.example.gameactivity;

import android.widget.BaseAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.List;
public class CardAdapter extends BaseAdapter {
    private Context context;
    private List<Card> cardList;
    private LayoutInflater inflater;

    public CardAdapter(Context context, List<Card> cardList) {
        this.context = context;
        this.cardList = cardList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int position) {
        return cardList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Lấy view cho mỗi ô trong GridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CardViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new CardViewHolder();
            holder.imageViewBack = convertView.findViewById(R.id.imageViewCardBack);
            holder.imageViewFront = convertView.findViewById(R.id.imageViewCardFront);
            convertView.setTag(holder);
        } else {
            holder = (CardViewHolder) convertView.getTag();
        }

        Card card = cardList.get(position);
        if (card.isFlipped() || card.isMatched()) {
            holder.imageViewFront.setVisibility(View.VISIBLE);
            holder.imageViewBack.setVisibility(View.GONE);
            holder.imageViewFront.setImageResource(card.getFrontImage());
        } else {
            holder.imageViewFront.setVisibility(View.GONE);
            holder.imageViewBack.setVisibility(View.VISIBLE);
            holder.imageViewBack.setImageResource(R.drawable.card);
        }

        return convertView;
    }

    // Lớp ViewHolder để tăng hiệu suất
    private class CardViewHolder {
        ImageView imageViewBack;
        ImageView imageViewFront;
    }
}
