package lk.jiat.app.varandesigns.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.modals.Cart;

public class PlacedOrderActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseFirestore firestore;

    TextView name,price,date,time;

    private NotificationManager notificationManager;
    private String channelId = "info";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);

        name = findViewById(R.id.design_name);
        price = findViewById(R.id.design_price);
        date = findViewById(R.id.design_date);
        time = findViewById(R.id.design_time);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        List<Cart> cartList = (ArrayList<Cart>) getIntent().getSerializableExtra("itemList");

        if (cartList != null && cartList.size() > 0){
            for (Cart cart : cartList){
                final HashMap<String,Object> cartMap = new HashMap<>();

                cartMap.put("designName",cart.getDesignName());
                cartMap.put("designPrice",cart.getDesignPrice().toString());
                cartMap.put("currentDate",cart.getCurrentDate());
                cartMap.put("currentTime",cart.getCurrentTime());
                cartMap.put("totalPrice",cart.getTotalPrice());

                firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                        .collection("MyOrder").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(PlacedOrderActivity.this,"Your Order Has Been Placed",Toast.LENGTH_SHORT).show();

                                name.setText(cart.getDesignName());
                                price.setText(cart.getDesignPrice());
                                date.setText(cart.getCurrentDate());
                                time.setText(cart.getCurrentTime());

                            }
                        });
            }
        }

    }
}