package com.example.uboncandlefestival.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import  androidx.recyclerview.widget.RecyclerView;

import com.example.uboncandlefestival.R;
import com.example.uboncandlefestival.model.Temple;

import java.util.List;

public class TempleAdapter extends RecyclerView.Adapter<TempleAdapter.TempleViewholder>{
    private Context context;
    private List<Temple> temples;


    public TempleAdapter(Context context, List<Temple> temples) {
        this.context = context;
        this.temples = temples;
    }

    @NonNull
    @Override
    public TempleAdapter.TempleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.temple_view, parent , false);
        return new TempleViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TempleAdapter.TempleViewholder holder, int position) {
        Temple temple = temples.get(position);
        holder.tvTempleName.setText(temple.getName());
        holder.tvDescription.setText(temple.getDescription());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TempleViewholder extends RecyclerView.ViewHolder {
        TextView tvTempleName, tvDescription;
        public TempleViewholder(@NonNull View itemView) {
            super(itemView);
            tvTempleName = itemView.findViewById(R.id.tv_temple_name);
            tvDescription = itemView.findViewById(R.id.tv_description);

        }
    }
}
