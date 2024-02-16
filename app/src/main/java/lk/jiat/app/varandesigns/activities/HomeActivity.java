package lk.jiat.app.varandesigns.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.firebase.auth.FirebaseAuth;

import lk.jiat.app.varandesigns.R;

public class HomeActivity extends AppCompatActivity {

    SpinKitView spinKitView;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
        spinKitView = findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null){
            spinKitView.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this,MainActivity.class));
            Toast.makeText(this,"Please wait you are already logged in", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void login(View view){
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }

    public void registration(View view){
        startActivity(new Intent(HomeActivity.this,RegistrationActivity.class));
    }
}