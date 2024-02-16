package lk.jiat.app.varandesigns.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.modals.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    Context context;
    List<Order> orderList;
    FirebaseFirestore firestore;
    FirebaseAuth auth;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        holder.name.setText(orderList.get(position).getDesignName());
        holder.price.setText(orderList.get(position).getDesignPrice());
        holder.currentDate.setText(orderList.get(position).getCurrentDate());
        holder.currentTime.setText(orderList.get(position).getCurrentTime());
        holder.totalPrice.setText(String.valueOf(orderList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder")
                        .document(orderList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    orderList.remove(orderList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Order Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,currentDate,currentTime,totalPrice;
        ImageView deleteItem;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.order_name);
            price = itemView.findViewById(R.id.order_price);
            currentDate = itemView.findViewById(R.id.order_date);
            currentTime = itemView.findViewById(R.id.order_time);
            totalPrice = itemView.findViewById(R.id.order_final_price);
            deleteItem = itemView.findViewById(R.id.order_delete_item);

        }
    }

}
