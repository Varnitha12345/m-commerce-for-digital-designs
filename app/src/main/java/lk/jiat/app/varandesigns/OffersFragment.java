package lk.jiat.app.varandesigns;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.adapters.HomeAdapter;
import lk.jiat.app.varandesigns.adapters.OfferAdapter;
import lk.jiat.app.varandesigns.adapters.PopularAdapters;
import lk.jiat.app.varandesigns.adapters.RecommendedAdapter;
import lk.jiat.app.varandesigns.adapters.ViewAllAdapter;
import lk.jiat.app.varandesigns.modals.HomeCategory;
import lk.jiat.app.varandesigns.modals.Popular;
import lk.jiat.app.varandesigns.modals.Recommended;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class OffersFragment extends Fragment {

    ScrollView scrollView;
    SpinKitView spinKitView;
    RecyclerView offerRecyclerView;
    FirebaseFirestore db;
    private List<ViewAll> viewAllList;
    private ViewAllAdapter viewAllAdapter;
    List<Popular> popularList;

    OfferAdapter offerAdapter;

    public OffersFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_offers, container, false);

        db = FirebaseFirestore.getInstance();

        offerRecyclerView = root.findViewById(R.id.offerRecyclerView);

        scrollView = root.findViewById(R.id.scroll_view3);
        spinKitView = root.findViewById(R.id.spin_kit6);
        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        offerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        popularList = new ArrayList<>();
        offerAdapter = new OfferAdapter(getActivity(),popularList);
        offerRecyclerView.setAdapter(offerAdapter);

        db.collection("PopularDesigns")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Popular popular = document.toObject(Popular.class);
                                popularList.add(popular);
                                offerAdapter.notifyDataSetChanged();
                                spinKitView.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}