package com.project.listado.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.listado.R;


public class LogInFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private EditText emailText, passwordText;
    private Button logInButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_log_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup(view);
    }

    private void setup(View view) {
        emailText = view.findViewById(R.id.email);
        passwordText = view.findViewById(R.id.password);
        logInButton = view.findViewById(R.id.log_in);
        firebaseAuth = FirebaseAuth.getInstance();


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
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


    }
}