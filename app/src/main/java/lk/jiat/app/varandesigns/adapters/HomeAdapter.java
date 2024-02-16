package lk.jiat.app.varandesigns.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.activities.ViewAllActivity;
import lk.jiat.app.varandesigns.modals.HomeCategory;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<HomeCategory> categoryList;
    public HomeAdapter(Context context, List<HomeCategory> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_category_items,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(categoryList.get(position).getImg_url()).into(holder.categoryImage);
        holder.category.setText(categoryList.get(position).getCategory());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("category",categoryList.get(position).getCategory());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return categoryList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView category;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryImage = itemView.findViewById(R.id.home_category_image);
            category = itemView.findViewById(R.id.home_category_category);
        }
    }
}
