package lk.jiat.app.varandesigns.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.modals.User;

public class RegistrationActivity extends AppCompatActivity {

    public static final String TAG = RegistrationActivity.class.getName();
    TextInputEditText textInputEditName, textInputEditEmail, textInputEditPassword, textInputEditConfirmPassword, textInputEditContact;
    FirebaseAuth mAuth;
    SpinKitView spinKitView;
    FirebaseDatabase database;
    private SignInClient signInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        textInputEditName = findViewById(R.id.name);
        textInputEditEmail = findViewById(R.id.email);
        textInputEditPassword = findViewById(R.id.password);
        textInputEditConfirmPassword = findViewById(R.id.confirm_password);
        textInputEditContact = findViewById(R.id.contact);

        spinKitView = findViewById(R.id.spin_kit2);

        findViewById(R.id.sign_in_link2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.register_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name, email, password, confirm_password,contact;
                name = String.valueOf(textInputEditName.getText());
                email = String.valueOf(textInputEditEmail.getText());
                password = String.valueOf(textInputEditPassword.getText());
                confirm_password = String.valueOf(textInputEditConfirmPassword.getText());
                contact = String.valueOf(textInputEditContact.getText());


                if (TextUtils.isEmpty(name)){
                    Toast.makeText(RegistrationActivity.this,"Please enter the name",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegistrationActivity.this,"Please enter the email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(RegistrationActivity.this,"Please enter the password",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 7){
                    Toast.makeText(RegistrationActivity.this,"Please Length must be greater than 7 letters",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password == confirm_password){
                    Toast.makeText(RegistrationActivity.this,"Please re enter the correct password",Toast.LENGTH_SHORT).show();
                    return;
                }

                spinKitView.setVisibility(View.VISIBLE);
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    spinKitView.setVisibility(View.GONE);

                                    User user = new User(name,email,password,contact);

                                    String id = task.getResult().getUser().getUid();
                                    database.getReference().child("Users").child(id).setValue(user);

                                    Toast.makeText(RegistrationActivity.this, "Account Successfully Created.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    spinKitView.setVisibility(View.GONE);
                                    Toast.makeText(RegistrationActivity.this, "Account Can't be create", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }

        });

        findViewById(R.id.google_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BeginSignInRequest oneTapRequest = BeginSignInRequest.builder()
                        .setGoogleIdTokenRequestOptions(
                                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                                        .setSupported(true)
                                        .setServerClientId(getString(R.string.web_client_id))
                                        .setFilterByAuthorizedAccounts(true)
                                        .build()
                        ).build();

                Task<BeginSignInResult> beginSignInResultTask = signInClient.beginSignIn(oneTapRequest);
                beginSignInResultTask.addOnSuccessListener(new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult beginSignInResult) {
                        IntentSenderRequest intentSenderRequest = new IntentSenderRequest
                                .Builder(beginSignInResult.getPendingIntent()).build();
                        signInLauncher.launch(intentSenderRequest);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
        });

    }

    private void firebaseAuthWithGoogle(String idToken){
        AuthCredential authCredential = GoogleAuthProvider.getCredential(idToken,null);
        Task<AuthResult> authResultTask = mAuth.signInWithCredential(authCredential);
        authResultTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
    private void handleSignInResult(Intent intent){
        try {
            SignInCredential signInCredential = signInClient.getSignInCredentialFromIntent(intent);
            String idToken = signInCredential.getGoogleIdToken();
            firebaseAuthWithGoogle(idToken);
        }catch (ApiException e){
            Log.e(TAG, e.getMessage());
        }
    }

    private final ActivityResultLauncher<IntentSenderRequest> signInLauncher =
            registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            handleSignInResult(o.getData());
                        }
                    }
            );

}