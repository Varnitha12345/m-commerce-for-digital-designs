package lk.jiat.app.varandesigns;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.activities.PlacedOrderActivity;
import lk.jiat.app.varandesigns.adapters.CartAdapter;
import lk.jiat.app.varandesigns.adapters.OrderAdapter;
import lk.jiat.app.varandesigns.modals.Cart;
import lk.jiat.app.varandesigns.modals.Order;

public class OrdersFragment extends Fragment {
    RelativeLayout container1,container2;

    ImageView order_design_img;
    ScrollView scrollView;
    SpinKitView spinKitView;
    RecyclerView recyclerView;
    FirebaseFirestore db;
    FirebaseAuth auth;
    List<Order> orderList;
    OrderAdapter orderAdapter;
    public OrdersFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_orders, container, false);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.orderRecyclerView);
        scrollView = root.findViewById(R.id.scroll_view3);
        spinKitView = root.findViewById(R.id.spin_kit6);
        order_design_img = root.findViewById(R.id.order_design_img);
        container1 = root.findViewById(R.id.container1);
        container2 = root.findViewById(R.id.container2);
        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(getActivity(),orderList);
        recyclerView.setAdapter(orderAdapter);
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrder").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){

                                String documentId = documentSnapshot.getId();

                                Order order = documentSnapshot.toObject(Order.class);

                                order.setDocumentId(documentId);

                                orderList.add(order);
                                orderAdapter.notifyDataSetChanged();
                                spinKitView.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.VISIBLE);
                                if(orderList == null){
                                    container1.setVisibility(View.VISIBLE);
                                    container2.setVisibility(View.GONE);
                                } else {
                                    container1.setVisibility(View.GONE);
                                    container2.setVisibility(View.VISIBLE);
                                }

                            }

                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;

    }
}