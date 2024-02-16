package lk.jiat.app.varandesigns.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.activities.ViewAllActivity;
import lk.jiat.app.varandesigns.modals.Popular;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder>{

    Context context;
    List<Popular> popularList;

    public OfferAdapter(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(popularList.get(position).getImg_url()).into(holder.image);
        holder.name.setText(popularList.get(position).getName());
        holder.description.setText(popularList.get(position).getDescription());
        holder.discount.setText(popularList.get(position).getDiscount());
        holder.rating.setText(String.valueOf(popularList.get(position).getRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("category",popularList.get(position).getCategory());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,description,discount,rating;
        ImageView image;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            image = itemView.findViewById(R.id.offer_img);
            name = itemView.findViewById(R.id.offer_name);
            description = itemView.findViewById(R.id.offer_des);
            discount = itemView.findViewById(R.id.offer_discount);
            rating = itemView.findViewById(R.id.offer_rating);

        }
    }

}
