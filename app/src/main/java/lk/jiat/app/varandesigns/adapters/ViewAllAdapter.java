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
import lk.jiat.app.varandesigns.activities.SingleViewActivity;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class ViewAllAdapter extends RecyclerView.Adapter<ViewAllAdapter.ViewHolder> {

    private Context context;
    private List<ViewAll> viewAllList;

    public ViewAllAdapter(Context context, List<ViewAll> viewAllList) {
        this.context = context;
        this.viewAllList = viewAllList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(viewAllList.get(position).getImg_url()).into(holder.viewAllImg);
        holder.name.setText(viewAllList.get(position).getName());
        holder.rating.setText(viewAllList.get(position).getRating());
        holder.description.setText(viewAllList.get(position).getDescription());
        holder.price.setText("Rs."+String.valueOf(viewAllList.get(position).getPrice())+"/=");

        if (viewAllList.get(position).getCategory().equals("Vector")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }
        if (viewAllList.get(position).getCategory().equals("3DModal")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }
        if (viewAllList.get(position).getCategory().equals("3DCharacter")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }
        if (viewAllList.get(position).getCategory().equals("2DDesign")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }
        if (viewAllList.get(position).getCategory().equals("Drawings")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }
        if (viewAllList.get(position).getCategory().equals("ArtWork")){
            holder.price.setText("Rs."+viewAllList.get(position).getPrice()+"/=");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,SingleViewActivity.class);
                intent.putExtra("detail",viewAllList.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView viewAllImg;
        TextView name,description,rating,price;
        //            Double price;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            viewAllImg = itemView.findViewById(R.id.view_all_img);
            name = itemView.findViewById(R.id.view_all_name);
            description = itemView.findViewById(R.id.view_all_des);
            rating = itemView.findViewById(R.id.view_all_rating);
            price = itemView.findViewById(R.id.view_all_price);

        }
    }

}
