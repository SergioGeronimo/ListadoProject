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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.listado.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

   FirebaseAuth firebaseAuth;
   FirebaseUser currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController navController = Navigation.findNavController(view);

        if (currentUser == null){
            navController.navigate(R.id.action_homeFragment_to_log_in_option);
        }else {

            Button logOutButton = view.findViewById(R.id.log_out);
            logOutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    firebaseAuth.signOut();
                    navController.navigate(R.id.action_homeFragment_to_log_in_option);
                }
            });

            TextView greetings = view.findViewById(R.id.greetings);
            greetings.setText("Hola, " + currentUser.getDisplayName());

            TextView id = view.findViewById(R.id.user_id);
            id.setText("ID: "  + currentUser.getUid());
        }
    }
}