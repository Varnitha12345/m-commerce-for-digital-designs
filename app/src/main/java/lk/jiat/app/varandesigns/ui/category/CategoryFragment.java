package lk.jiat.app.varandesigns.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.adapters.CategoryAdapter;
import lk.jiat.app.varandesigns.adapters.HomeAdapter;
import lk.jiat.app.varandesigns.adapters.PopularAdapters;
import lk.jiat.app.varandesigns.modals.HomeCategory;
import lk.jiat.app.varandesigns.modals.Popular;

public class CategoryFragment extends Fragment {


    ScrollView scrollView;
    SpinKitView spinKitView;
    RecyclerView categoryRecycleView;
    FirebaseFirestore db;

    List<HomeCategory> categoryList;
    CategoryAdapter categoryAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_category, container, false);
        db = FirebaseFirestore.getInstance();

        categoryRecycleView = root.findViewById(R.id.category_rec);
        scrollView = root.findViewById(R.id.scroll_view2);
        spinKitView = root.findViewById(R.id.spin_kit5);

        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);
        categoryRecycleView.setVisibility(View.GONE);

        categoryRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        categoryList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getActivity(),categoryList);
        categoryRecycleView.setAdapter(categoryAdapter);

        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()){

                                HomeCategory category = document.toObject(HomeCategory.class);
                                categoryList.add(category);
                                categoryAdapter.notifyDataSetChanged();

                                spinKitView.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                                categoryRecycleView.setVisibility(View.VISIBLE);

                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;
    }

}