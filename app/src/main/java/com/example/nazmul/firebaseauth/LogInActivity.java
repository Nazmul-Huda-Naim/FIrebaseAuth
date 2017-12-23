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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btLogIn;
    private EditText etEmail;
    private EditText etPass;
    private TextView tvSignUp;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            ///start profile Act
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileAct.class));
        }
        progressDialog=new ProgressDialog(this);

        btLogIn =(Button) findViewById(R.id.btLogIn);
        etEmail=(EditText) findViewById(R.id.etEmail);
        etPass=(EditText) findViewById(R.id.etPass);
        tvSignUp=(TextView) findViewById(R.id.tvSignUp);

        btLogIn.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);

    }
    private void userLogin(){
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
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            ///start profile
                            finish();
                            startActivity(new Intent(getApplicationContext(),ProfileAct.class));
                        }
                    }
                });
    }


    @Override
    public void onClick(View view) {
        if(view==btLogIn){
            userLogin();
        }
        if(view==tvSignUp){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
