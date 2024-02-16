package lk.jiat.app.varandesigns.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.adapters.ViewAllAdapter;
import lk.jiat.app.varandesigns.modals.ViewAll;

public class ViewAllActivity extends AppCompatActivity {
    FirebaseFirestore db;
    RecyclerView recyclerView;
    ViewAllAdapter viewAllAdapter;
    List<ViewAll> viewAllList;
    Toolbar toolbar;
    ScrollView scrollView;
    SpinKitView spinKitView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        db= FirebaseFirestore.getInstance();
        String category = getIntent().getStringExtra("category");
        recyclerView = findViewById(R.id.view_all_rec);

        scrollView = findViewById(R.id.scroll_view);
        spinKitView = findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.GONE);

        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        viewAllList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(this,viewAllList);
        recyclerView.setAdapter(viewAllAdapter);

        if (category != null && category.equalsIgnoreCase("ArtWork")) {
            db.collection("AllDesigns").whereEqualTo("category", "ArtWork")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot document:task.getResult()) {
                                    ViewAll viewAll = document.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        if (category != null && category.equalsIgnoreCase("3DModal")) {
            db.collection("AllDesigns").whereEqualTo("category", "3DModal")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                                    ViewAll viewAll = documentSnapshot.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        if (category != null && category.equalsIgnoreCase("3DCharacter")) {
            db.collection("AllDesigns").whereEqualTo("category", "3DCharacter")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                                    ViewAll viewAll = documentSnapshot.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        if (category != null && category.equalsIgnoreCase("2DDesign")) {
            db.collection("AllDesigns").whereEqualTo("category", "2DDesign")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                                    ViewAll viewAll = documentSnapshot.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        if (category != null && category.equalsIgnoreCase("Drawings")) {
            db.collection("AllDesigns").whereEqualTo("category", "Drawings")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()) {
                                    ViewAll viewAll = documentSnapshot.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

        if (category != null && category.equalsIgnoreCase("Vector")) {
            db.collection("AllDesigns").whereEqualTo("category", "Vector")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {

                            if(task.isSuccessful()){
                                for (QueryDocumentSnapshot documentSnapshot:task.getResult()) {

                                    ViewAll viewAll = documentSnapshot.toObject(ViewAll.class);
                                    viewAllList.add(viewAll);
                                    viewAllAdapter.notifyDataSetChanged();
                                    spinKitView.setVisibility(View.GONE);
                                    scrollView.setVisibility(View.VISIBLE);
                                    recyclerView.setVisibility(View.VISIBLE);
                                }
                            }else {
                                Toast.makeText(ViewAllActivity.this, "Error"+task.getException(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }

    }
}