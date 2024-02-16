package lk.jiat.app.varandesigns.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import lk.jiat.app.varandesigns.R;

public class SplashActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.splash_view);

        Picasso.get().load(R.drawable.varan_logo_873x816)
                .into(imageView);

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                findViewById(R.id.spin_kit).setVisibility(View.INVISIBLE);
                findViewById(R.id.splash_view).setVisibility(View.VISIBLE);
                findViewById(R.id.start_shopping_btn).setVisibility(View.VISIBLE);
                findViewById(R.id.sign_in_que).setVisibility(View.VISIBLE);
                findViewById(R.id.sign_in_link).setVisibility(View.VISIBLE);


            }
        },10000);

        findViewById(R.id.start_shopping_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                if (mAuth.getCurrentUser() != null){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    Toast.makeText(SplashActivity.this,"Please wait you are already logged in",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SplashActivity.this,"Please click on the sign in button and sign in first",Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.sign_in_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}