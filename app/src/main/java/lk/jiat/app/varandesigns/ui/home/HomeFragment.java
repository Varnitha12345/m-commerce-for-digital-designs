package lk.jiat.app.varandesigns.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.adapters.HomeAdapter;
import lk.jiat.app.varandesigns.adapters.PopularAdapters;
import lk.jiat.app.varandesigns.adapters.RecommendedAdapter;
import lk.jiat.app.varandesigns.adapters.ViewAllAdapter;
import lk.jiat.app.varandesigns.modals.HomeCategory;
import lk.jiat.app.varandesigns.modals.Popular;
import lk.jiat.app.varandesigns.modals.Recommended;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class HomeFragment extends Fragment {
    ScrollView scrollView;
    SpinKitView spinKitView;
    RecyclerView popularRecycleView,homeCategoryRecycleView,recommendedRecycleView;
    FirebaseFirestore db;
    EditText search_bar;
    private List<ViewAll> viewAllList;
    private RecyclerView recyclerViewSearch;
    private ViewAllAdapter viewAllAdapter;
    List<Popular> popularList;
    PopularAdapters popularAdapters;
    List<HomeCategory> categoryList;
    HomeAdapter homeAdapter;
    List<Recommended> recommendedList;
    RecommendedAdapter recommendedAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);;
        db = FirebaseFirestore.getInstance();

        popularRecycleView = root.findViewById(R.id.popular_rec);
        homeCategoryRecycleView = root.findViewById(R.id.explore_rec);
        recommendedRecycleView = root.findViewById(R.id.recommended_rec);

        scrollView = root.findViewById(R.id.scroll_view);
        spinKitView = root.findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        popularRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        popularList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(),popularList);
        popularRecycleView.setAdapter(popularAdapters);
        db.collection("PopularDesigns")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Popular popular = document.toObject(Popular.class);
                                popularList.add(popular);
                                popularAdapters.notifyDataSetChanged();
                                spinKitView.setVisibility(View.GONE);
                                scrollView.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        homeCategoryRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryList = new ArrayList<>();
        homeAdapter = new HomeAdapter(getActivity(),categoryList);
        homeCategoryRecycleView.setAdapter(homeAdapter);
        db.collection("HomeCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()){
                                HomeCategory homeCategory = document.toObject(HomeCategory.class);
                                categoryList.add(homeCategory);
                                homeAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        recommendedRecycleView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        recommendedList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(),recommendedList);
        recommendedRecycleView.setAdapter(recommendedAdapter);
        db.collection("Recommended")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for(QueryDocumentSnapshot document : task.getResult()){

                                Recommended recommended = document.toObject(Recommended.class);
                                recommendedList.add(recommended);
                                recommendedAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        recyclerViewSearch = root.findViewById(R.id.search_rec);
        search_bar = root.findViewById(R.id.search_bar);
        viewAllList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(),viewAllList);
        recyclerViewSearch.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
        recyclerViewSearch.setAdapter(viewAllAdapter);
        recyclerViewSearch.setHasFixedSize(true);
        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    viewAllList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }else {
                    searchDesign(s.toString());
                }
            }
        });
        return root;
    }
    private void searchDesign(String category) {
        if (!category.isEmpty()){
            db.collection("AllDesigns").whereEqualTo("category",category).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                viewAllList.clear();
                                viewAllAdapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "Searching", Toast.LENGTH_SHORT).show();
                                for (DocumentSnapshot doc : task.getResult().getDocuments()){
                                    ViewAll viewAll = doc.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    Toast.makeText(getActivity(), "Refreshing", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
        }
    }

}