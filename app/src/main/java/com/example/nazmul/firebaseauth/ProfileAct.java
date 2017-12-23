package com.example.nazmul.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileAct extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private TextView tvName;
    private Button btLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName=(TextView) findViewById(R.id.tvName);
        btLogout=(Button) findViewById(R.id.btLogout);

        firebaseAuth= FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LogInActivity.class));
        }

        FirebaseUser user =firebaseAuth.getCurrentUser();



        btLogout =(Button) findViewById(R.id.btLogout);
        tvName.setText("Welcome "+user.getEmail());
        tvName=(TextView) findViewById(R.id.tvName);

        btLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view==btLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LogInActivity.class));
        }
    }
}
