package com.project.listado.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.project.listado.R;


public class RegisterEmail extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final EditText nombres, apellidos, email, contrasena;

        nombres = view.findViewById(R.id.nombre);
        apellidos = view.findViewById(R.id.apellidos);
        email = view.findViewById(R.id.email);
        contrasena = view.findViewById(R.id.password);
        final NavController navController = Navigation.findNavController(view);;

        Button registerButton = view.findViewById(R.id.create_account);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isNotEmptyData(nombres, apellidos, email, contrasena)) {

                    firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),
                            contrasena.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        /*FirebaseUser user = firebaseAuth.getCurrentUser();
                                        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest().Builder()
                                                .setDisplayName("Jane Q. User")
                                                .build();*/


                                        navController.navigate(R.id.action_registerEmail_to_homeFragment);

                                    }else {
                                        Toast.makeText(getContext(), "Error al crear cuenta",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                            });
                }else {
                    Toast.makeText(getContext(), "Llena tus datos", Toast.LENGTH_SHORT);
                }

            }
        });



    }

    private boolean isNotEmptyData(EditText...  data){
        for (EditText singleData : data){
            if (singleData.getText().length() == 0)
                return false;
        }
        return true;
    }
}