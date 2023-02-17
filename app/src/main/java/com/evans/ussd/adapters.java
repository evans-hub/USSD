package com.evans.ussd;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapters extends RecyclerView.Adapter<adapters.viewHolder>{
    private static final int REQUEST_CALL=1;
    Context context;
    Activity activity;
    ArrayList<Model> arrayList;
    DatabaseHelper database_helper;
    CardView cardView;
    MainActivity mainActivity;
    public ArrayList<Model> arrayListFiltered;
    public adapters(Context context, Activity activity, ArrayList<Model> arrayList) {
        this.context = context;
        this.activity  = activity ;
        this.arrayList = arrayList;
        arrayListFiltered = arrayList;
    }

    @Override
    public adapters.viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.draw, viewGroup, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        cardView=holder.itemView.findViewById(R.id.card);

        if (position == 0) {
            cardView.setCardBackgroundColor(Color.parseColor("#002063"));
        }
        if (position == 1) {
            cardView.setCardBackgroundColor(Color.parseColor("#7330a5"));
        }
        if (position == 2) {
            cardView.setCardBackgroundColor(Color.parseColor("#212421"));
        }
        if (position == 3) {
            cardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
        }
        if (position == 4) {
            cardView.setCardBackgroundColor(Color.parseColor("#00b252"));
        }
        if (position == 5) {
            cardView.setCardBackgroundColor(Color.parseColor("#0071c6"));
        }
        if (position == 6) {
            cardView.setCardBackgroundColor(Color.parseColor("#7b6100"));
        }
        if (position == 7) {
            cardView.setCardBackgroundColor(Color.parseColor("#313c52"));
        }
        if (position == 8) {
            cardView.setCardBackgroundColor(Color.parseColor("#c65910"));
        }
        if (position == 9) {
            cardView.setCardBackgroundColor(Color.parseColor("#295594"));
        }
        if (position == 10) {
            cardView.setCardBackgroundColor(Color.parseColor("#000000"));
        }
        if (position == 11) {
            cardView.setCardBackgroundColor(Color.parseColor("#737173"));
        }
        if (position == 12) {
            cardView.setCardBackgroundColor(Color.parseColor("#313c4a"));
        }
        if (position == 13) {
            cardView.setCardBackgroundColor(Color.parseColor("#c65910"));
        }
        if (position == 14) {
            cardView.setCardBackgroundColor(Color.parseColor("#7330a5"));
        }
        if (position == 15) {
            cardView.setCardBackgroundColor(Color.parseColor("#002063"));
        }
        if (position == 16) {
            cardView.setCardBackgroundColor(Color.parseColor("#00b252"));
        }
        if (position == 17) {
            cardView.setCardBackgroundColor(Color.parseColor("#002063"));
        }
        if (position == 18) {
            cardView.setCardBackgroundColor(Color.parseColor("#7330a5"));
        }
        if (position == 19) {
            cardView.setCardBackgroundColor(Color.parseColor("#212421"));
        }
        if (position == 20) {
            cardView.setCardBackgroundColor(Color.parseColor("#ff0000"));
        }
        if (position == 21) {
            cardView.setCardBackgroundColor(Color.parseColor("#00b252"));
        }
        if (position == 22) {
            cardView.setCardBackgroundColor(Color.parseColor("#0071c6"));
        }
        if (position == 23) {
            cardView.setCardBackgroundColor(Color.parseColor("#7b6100"));
        }
        if (position == 24) {
            cardView.setCardBackgroundColor(Color.parseColor("#313c52"));
        }
        if (position == 25) {
            cardView.setCardBackgroundColor(Color.parseColor("#c65910"));
        }
        if (position == 26) {
            cardView.setCardBackgroundColor(Color.parseColor("#295594"));
        }
        if (position == 27) {
            cardView.setCardBackgroundColor(Color.parseColor("#000000"));
        }
        if (position == 28) {
            cardView.setCardBackgroundColor(Color.parseColor("#737173"));
        }
        if (position == 29) {
            cardView.setCardBackgroundColor(Color.parseColor("#313c4a"));
        }
        if (position == 30) {
            cardView.setCardBackgroundColor(Color.parseColor("#c65910"));
        }
        if (position == 31) {
            cardView.setCardBackgroundColor(Color.parseColor("#7330a5"));
        }
        if (position == 32) {
            cardView.setCardBackgroundColor(Color.parseColor("#002063"));
        }
        if (position == 33) {
            cardView.setCardBackgroundColor(Color.parseColor("#00b252"));
        }

Model mode=arrayList.get(position);
        holder.title.setText(arrayList.get(position).getTitle());
        holder.description.setText(arrayList.get(position).getDes());
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=mode.getID();
                database_helper.delete(phone);
                Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();

            }
        });

        database_helper = new DatabaseHelper(context);

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView del;

        public viewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            del=itemView.findViewById(R.id.delll);



        }
    }



}
