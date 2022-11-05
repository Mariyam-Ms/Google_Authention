package com.project1.google_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {
    private TextView name,email;
    private Button signOut;
    private ImageView profilePic;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        name=findViewById(R.id.username);
        email=findViewById(R.id.email);
        signOut=findViewById(R.id.signout);
        profilePic=findViewById(R.id.profile_pic);
        firebaseAuth=FirebaseAuth.getInstance();

        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            String mName=account.getDisplayName();
            String mEmail=account.getEmail();
            Uri image=account.getPhotoUrl();
            name.setText(mName);
            email.setText(mEmail);
          //  profilePic.setImageURI(image);
            Picasso.get().load(image).into(profilePic);

        }
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                startActivity(new Intent(ProfileActivity.this,MainActivity.class));
                finish();

            }
        });

    }
}