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

import java.io.Serializable;
import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.activities.SingleViewActivity;
import lk.jiat.app.varandesigns.modals.Recommended;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.ViewHolder> {

        private Context context;
        private List<Recommended> recommendedList;
        private List<ViewAll> viewAllList;

        public RecommendedAdapter(Context context, List<Recommended> recommendedList) {
            this.context = context;
            this.recommendedList = recommendedList;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_design,parent,false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Glide.with(context).load(recommendedList.get(position).getImg_url()).into(holder.recommendImg);
            holder.name.setText(recommendedList.get(position).getName());
            holder.rating.setText(recommendedList.get(position).getRating());
            holder.description.setText(recommendedList.get(position).getDescription());
            holder.price.setText(String.valueOf("Rs."+recommendedList.get(position).getPrice()+"/="));

            if (recommendedList.get(position).getCategory().equals("Vector")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }
            if (recommendedList.get(position).getCategory().equals("3DModal")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }
            if (recommendedList.get(position).getCategory().equals("3DCharacter")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }
            if (recommendedList.get(position).getCategory().equals("2DDesign")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }
            if (recommendedList.get(position).getCategory().equals("Drawings")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }
            if (recommendedList.get(position).getCategory().equals("ArtWork")){
                holder.price.setText("Rs."+recommendedList.get(position).getPrice()+"/=");
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SingleViewActivity.class);
                    intent.putExtra("detail",recommendedList.get(position));
                    context.startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return recommendedList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            ImageView recommendImg;
            TextView name,description,rating,price;
//            Double price;
            public ViewHolder(@NonNull View itemView){
                super(itemView);

                recommendImg = itemView.findViewById(R.id.recommended_img);
                name = itemView.findViewById(R.id.recommended_name);
                description = itemView.findViewById(R.id.recommended_description);
                rating = itemView.findViewById(R.id.recommended_rating);
                price = itemView.findViewById(R.id.recommend_price);

            }
        }

}

