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
import lk.jiat.app.varandesigns.modals.Cart;

public class CartAdapter  extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    Context context;
    List<Cart> cartList;
    int cartTotalPrice= 0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;


    public CartAdapter(Context context, List<Cart> cartList) {
        this.context = context;
        this.cartList = cartList;
        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        holder.name.setText(cartList.get(position).getDesignName());
        holder.price.setText(cartList.get(position).getDesignPrice());
        holder.currentDate.setText(cartList.get(position).getCurrentDate());
        holder.currentTime.setText(cartList.get(position).getCurrentTime());
        holder.totalPrice.setText(String.valueOf(cartList.get(position).getTotalPrice()));

        holder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("AddToCart")
                        .document(cartList.get(position).getDocumentId())
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    cartList.remove(cartList.get(position));
                                    notifyDataSetChanged();
                                    Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
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
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,price,currentDate,currentTime,totalPrice;
        ImageView deleteItem;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price);
            currentDate = itemView.findViewById(R.id.cart_date);
            currentTime = itemView.findViewById(R.id.cart_time);
            totalPrice = itemView.findViewById(R.id.cart_final_price);
            deleteItem = itemView.findViewById(R.id.cart_delete_item);

        }
    }
}
