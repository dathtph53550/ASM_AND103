package com.example.asm_and103;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    EditText etEmail, etPassword, etRePassword;
    Button btnCreateAccount;
    TextView tvSignIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etRePassword = findViewById(R.id.re_password);
        btnCreateAccount = findViewById(R.id.create_account_button);
        tvSignIn = findViewById(R.id.tvSignIn);
        mAuth = FirebaseAuth.getInstance();

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();
                String re_pass = etRePassword.getText().toString();
                if(email.isEmpty() || pass.isEmpty() || re_pass.isEmpty()){
                    Toast.makeText(Register.this, "Vui lòng nhập đầy đủ thông tin !!", Toast.LENGTH_SHORT).show();
                }
                else if(!re_pass.trim().equals(pass.trim())) {
                    Toast.makeText(Register.this, "Mật Khẩu Không Trùng Khớp", Toast.LENGTH_SHORT).show();
                }
                else if(pass.length() < 6){
                    Toast.makeText(Register.this, "Mật Khẩu Phải Lớn Hơn 6 Ký Tự", Toast.LENGTH_SHORT).show();
                }
                else{
                    mAuth.createUserWithEmailAndPassword(email,pass)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(Register.this, "Đăng ký thành công !! ", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Register.this, Login.class));
                                    } else {
                                        Log.w("zzzzz", "onComplete: ", task.getException());
                                        Toast.makeText(Register.this, "Đăng ký thất bại !!", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });

    }
}