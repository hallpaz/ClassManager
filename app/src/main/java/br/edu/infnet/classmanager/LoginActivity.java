package br.edu.infnet.classmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
    }


    //
    public void signIn(View view){
        //TODO: sign in code
        EditText field = findViewById(R.id.email_field);
        String email = field.getText().toString();

        field = findViewById(R.id.password_field);
        String password = field.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(
                                    getApplicationContext(),
                                    MainActivity.class
                            );
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public void goToSignUp(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }

    public void skipToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        //TODO: remove from stack
        startActivity(intent);
    }
}
