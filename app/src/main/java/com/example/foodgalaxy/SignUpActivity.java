package com.example.foodgalaxy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.foodgalaxy.Common.Common;
import com.example.foodgalaxy.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail, inputPassword,inputPhone,inputAddress;
    private Button btnSignIn, btnSignUp, btnResetPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    long id ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignIn = (Button) findViewById(R.id.sign_in_button);
        btnSignUp = (Button) findViewById(R.id.sign_up_button);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        inputPhone= (EditText) findViewById(R.id.Phone);
        inputAddress = (EditText) findViewById(R.id.Address);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnResetPassword = (Button) findViewById(R.id.btn_reset_password);


        //Init Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference user= database.getReference("User");

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, ResetPasswordActivity.class));
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog = new ProgressDialog(SignUpActivity.this);
                mDialog.setMessage("Please Wait");
                mDialog.show();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String phone = inputEmail.getText().toString().trim();
                String address = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(getApplicationContext(), "Enter phone number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.length() < 10) {
                    Toast.makeText(getApplicationContext(), "phone number is  too short, enter 10 digit number!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    Toast.makeText(getApplicationContext(), "Enter postal address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create user
                user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if already user phone
                        if(dataSnapshot.child(inputPhone.getText().toString()).exists()){
                            progressBar.setVisibility(View.GONE);
                            id = (dataSnapshot.getChildrenCount());
                            Toast.makeText(SignUpActivity.this, "Phone number already registered", Toast.LENGTH_SHORT).show();
                            mDialog.dismiss();
                        }
                        else{
                            auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            progressBar.setVisibility(View.GONE);
                                            if (task.isSuccessful()) {
                                                mDialog.dismiss();
                                                User users = new User(id ,inputEmail.getText().toString(),inputPassword.getText().toString(),inputPhone.getText().toString(),inputAddress.getText().toString());
                                                user.child(inputPhone.getText().toString()).setValue(users);
                                                Toast.makeText(SignUpActivity.this, "created User With "+inputEmail.getText().toString()+" successfully registered" , Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
                                                finish();
                                            } else {
                                                mDialog.dismiss();
                                                Toast.makeText(SignUpActivity.this, "User With "+inputEmail.getText().toString()+" already exist" ,
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}