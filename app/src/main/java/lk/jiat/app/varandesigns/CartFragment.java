package lk.jiat.app.varandesigns;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.activities.PlacedOrderActivity;
import lk.jiat.app.varandesigns.adapters.CartAdapter;
import lk.jiat.app.varandesigns.adapters.HomeAdapter;
import lk.jiat.app.varandesigns.adapters.PopularAdapters;
import lk.jiat.app.varandesigns.adapters.RecommendedAdapter;
import lk.jiat.app.varandesigns.modals.Cart;
import lk.jiat.app.varandesigns.modals.HomeCategory;
import lk.jiat.app.varandesigns.modals.Popular;
import lk.jiat.app.varandesigns.modals.Recommended;

public class CartFragment extends Fragment {
    RelativeLayout container1,container2;
    Button buy_now;
    ScrollView scrollView;
    SpinKitView spinKitView;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    FirebaseAuth auth;
    TextView overTotalAmount;
    List<Cart> cartList;
    CartAdapter cartAdapter;
    public CartFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_cart, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.cartRecyclerView);

        scrollView = root.findViewById(R.id.scroll_view3);
        spinKitView = root.findViewById(R.id.spin_kit6);
        buy_now = root.findViewById(R.id.buy_now);
        container1 = root.findViewById(R.id.container1);
        container2 = root.findViewById(R.id.container2);
        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        overTotalAmount = root.findViewById(R.id.textView2);
        cartList = new ArrayList<>();
        cartAdapter = new CartAdapter(getActivity(),cartList);
        recyclerView.setAdapter(cartAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                String documentId = documentSnapshot.getId();

                                Cart cart = documentSnapshot.toObject(Cart.class);

                                cart.setDocumentId(documentId);

                                cartList.add(cart);
                                cartAdapter.notifyDataSetChanged();
                                spinKitView.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                if(cartList == null){
                                    container1.setVisibility(View.VISIBLE);
                                    container2.setVisibility(View.GONE);
                                } else {
                                    container1.setVisibility(View.GONE);
                                    container2.setVisibility(View.VISIBLE);
                                }

                            }

                            calculateTotalAmount(cartList);
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeNotification();
            }
        });
        return root;
    }

    public void makeNotification(){
        String channelID = "CHANNEL_ID_NOTIFICATION";
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getContext(),channelID);
        builder.setSmallIcon(R.drawable.varan_logo_291x272)
                .setContentTitle("Ordered Design")
                .setContentText("Order placed")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent = new Intent(getContext(),PlacedOrderActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("itemList", (Serializable) cartList);

        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),
                0,intent,PendingIntent.FLAG_MUTABLE);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getActivity()
                        .getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel =
                    notificationManager.getNotificationChannel(channelID);
            if (notificationChannel == null){
                int importance = NotificationManager.IMPORTANCE_HIGH;
                notificationChannel = new NotificationChannel
                        (channelID,"VARAN Design Order Placed",importance);
                notificationChannel.setLightColor(Color.GREEN);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        notificationManager.notify(0,builder.build());

    }
    private void calculateTotalAmount(List<Cart> cartList) {

        double totalAmount = 0.0;
        for (Cart cart : cartList){
            totalAmount += cart.getTotalPrice();
        }
        overTotalAmount.setText("Total Amount : Rs."+totalAmount+"/=");
    }
}