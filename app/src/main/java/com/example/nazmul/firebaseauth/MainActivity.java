package com.example.nazmul.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btRegd;
    private EditText etEmail;
    private EditText etPass;
    private TextView tvSignIn;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            ///start profile Act
            finish();
            startActivity(new Intent(MainActivity.this,ProfileAct.class));
        }

        progressDialog=new ProgressDialog(this);

        btRegd =(Button) findViewById(R.id.btRegd);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPass=(EditText) findViewById(R.id.etPass);
        tvSignIn=(TextView) findViewById(R.id.tvSignIn);

        btRegd.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

    }

    private void registerUser(){
        String email= etEmail.getText().toString().trim();
        String password= etPass.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            ///email empty
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            ///pass empty
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
            return;

        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        ///Successfully
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileAct.class));
                        }
                       // Toast.makeText(MainActivity.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                    else{
                        Toast.makeText(MainActivity.this,"Failed to Register, Try Again",Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view==btRegd){
            registerUser();
        }
        if(view==tvSignIn){
            //Login
            startActivity(new Intent(this,LogInActivity.class));
        }
    }
}
