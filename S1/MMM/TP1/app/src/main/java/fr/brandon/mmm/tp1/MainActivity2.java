package fr.brandon.mmm.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.brandon.mmm.tp1.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    private @NonNull
    ActivityMain2Binding binding;

    public MainActivity2() {
        binding = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMain2Binding.inflate(getLayoutInflater());

        View viewActivityMain2 = binding.getRoot();

        loadDataFromIntent();

        setContentView(viewActivityMain2);
    }

    public void loadDataFromIntent() {
        Intent intent = getIntent();
        this.binding.textViewShowNom.setText(intent.getStringExtra("nom"));
        this.binding.textViewShowPrenom.setText(intent.getStringExtra("prenom"));
        this.binding.textViewShowDateNaissance.setText(intent.getStringExtra("dateNaissance"));
        this.binding.textViewShowVille.setText(intent.getStringExtra("ville"));
    }
}