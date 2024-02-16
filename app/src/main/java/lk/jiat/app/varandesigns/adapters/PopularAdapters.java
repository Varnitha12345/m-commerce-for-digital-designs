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
import lk.jiat.app.varandesigns.modals.ViewAll;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<Popular> popularList;

    public PopularAdapters(Context context, List<Popular> popularList) {
        this.context = context;
        this.popularList = popularList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_design,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularList.get(position).getImg_url()).into(holder.popularImg);
        holder.name.setText(popularList.get(position).getName());
        holder.rating.setText(popularList.get(position).getRating());
        holder.description.setText(popularList.get(position).getDescription());
        holder.discount.setText(popularList.get(position).getDiscount());

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
        ImageView popularImg;
        TextView name,description,rating,discount;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            popularImg = itemView.findViewById(R.id.popular_img);
            name = itemView.findViewById(R.id.popular_name);
            description = itemView.findViewById(R.id.popular_des);
            rating = itemView.findViewById(R.id.popular_rating);
            discount = itemView.findViewById(R.id.popular_discount);


        }
    }

}
