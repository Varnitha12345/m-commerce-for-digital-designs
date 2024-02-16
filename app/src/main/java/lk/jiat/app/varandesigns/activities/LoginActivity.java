package lk.jiat.app.varandesigns.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import lk.jiat.app.varandesigns.R;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText textInputEditEmail, textInputEditPassword;
    FirebaseAuth mAuth;
    SpinKitView spinKitView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        textInputEditEmail = findViewById(R.id.email);
        textInputEditPassword = findViewById(R.id.password);
        spinKitView = findViewById(R.id.spin_kit3);


        findViewById(R.id.sign_up_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.login_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email, password;
                email = String.valueOf(textInputEditEmail.getText());
                password = String.valueOf(textInputEditPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Please enter the email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this,"Please enter the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 7){
                    Toast.makeText(LoginActivity.this,"Please Length must be greater than 7 letters",Toast.LENGTH_SHORT).show();
                    return;
                }
                spinKitView.setVisibility(View.VISIBLE);
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    spinKitView.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    spinKitView.setVisibility(View.GONE);
                                    Toast.makeText(LoginActivity.this, "Login Failed(Invalid Credentials)", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }
}