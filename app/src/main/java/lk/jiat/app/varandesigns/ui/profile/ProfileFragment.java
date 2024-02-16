package lk.jiat.app.varandesigns.ui.profile;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;
import lk.jiat.app.varandesigns.MapFragment;
import lk.jiat.app.varandesigns.R;
import lk.jiat.app.varandesigns.modals.User;
import lk.jiat.app.varandesigns.ui.home.HomeFragment;

public class ProfileFragment extends Fragment {

    CircleImageView profileImage;
    EditText name,email,contact,address;
    Button update;

    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;

    SpinKitView spinKitView;


    private final int FINE_PERMISSION_CODE =1;
    private GoogleMap myMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        spinKitView = root.findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);

        profileImage = root.findViewById(R.id.profile_image);
        name = root.findViewById(R.id.profile_name);
        email = root.findViewById(R.id.profile_email);
        contact = root.findViewById(R.id.profile_contact);
        address = root.findViewById(R.id.profile_address);
        update = root.findViewById(R.id.update_profile_btn);

        database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                User user = snapshot.getValue(User.class);
                                spinKitView.setVisibility(View.GONE);
                                Glide.with(getContext()).load(user.getProfileImage()).into(profileImage);
                                name.setText(user.getName());
                                email.setText(user.getEmail());
                                contact.setText(user.getContact());
                                address.setText(user.getAddress());

                                user.setName(name.getText().toString());
                                user.setEmail(email.getText().toString());
                                user.setContact(contact.getText().toString());
                                user.setAddress(address.getText().toString());


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Profile Updated Successfully",Toast.LENGTH_SHORT).show();
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new HomeFragment());
                fragmentTransaction.commit();
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container,new MapFragment());
                fragmentTransaction.commit();

            }
        });

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null){
            Uri profileUri = data.getData();
            profileImage.setImageURI(profileUri);

            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());

            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getContext(),"Uploaded",Toast.LENGTH_SHORT).show();

                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                             database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid())
                                     .child("profileImage").setValue(uri.toString());
                            Toast.makeText(getContext(),"Profile Picture Uploaded",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(),"Photo uploading Canceled",Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getContext(),"Photo uploading Canceled",Toast.LENGTH_SHORT).show();
        }
    }
}