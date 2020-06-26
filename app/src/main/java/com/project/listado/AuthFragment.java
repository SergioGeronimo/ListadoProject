package com.project.listado;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.opencensus.tags.Tag;

public class AuthFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private EditText emailText, passwordText;
    private Button logInButton, registerButton, googleAccountButton;
    private static final String TAG = "Main Activity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_auth, container, false);

        setup(view);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            //pasar a otro fragment
            //....

        }
    }

    private void setup(View view) {
        emailText = view.findViewById(R.id.email);
        passwordText = view.findViewById(R.id.password);
        logInButton = view.findViewById(R.id.log_in);
        registerButton = view.findViewById(R.id.register);
        googleAccountButton = view.findViewById(R.id.with_google_account);
        firebaseAuth = FirebaseAuth.getInstance();

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emailText.getText().length() != 0 &&
                    passwordText.getText().length() != 0){
                    firebaseAuth.signInWithEmailAndPassword(
                            emailText.getText().toString(),
                            passwordText.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(
                                        getContext(),
                                        "Sesion iniciada",
                                        Toast.LENGTH_SHORT)
                                    .show();
                            }else {
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(
                                        getContext(),
                                        "Inicio fallido, comprueba tus datos",
                                        Toast.LENGTH_SHORT)
                                    .show();
                            }
                        }
                    });
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}