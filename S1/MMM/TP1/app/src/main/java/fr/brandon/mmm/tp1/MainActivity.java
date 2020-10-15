package fr.brandon.mmm.tp1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import fr.brandon.mmm.tp1.databinding.ActivityMainBinding;

import static android.text.InputType.TYPE_CLASS_PHONE;

public class MainActivity extends AppCompatActivity {
    private @NonNull
    ActivityMainBinding binding;
    private boolean phoneAdded;
    private EditText editTextPhone;

    public MainActivity() {
        this.binding = null;
        this.phoneAdded = false;
        this.editTextPhone = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());

        View viewActivityMain = binding.getRoot();

        setContentView(viewActivityMain);

        binding.buttonValider.setOnClickListener(view -> {
                    /*
                    String infos = "";

                    infos += binding.editTextNom.getText().toString() + " ";
                    infos += binding.editTextPrenom.getText().toString() + ", né le ";
                    infos += binding.editTextDateNaissance.getText().toString() + " à ";
                    infos += binding.editTextVilleNaissance.getText().toString();

                    if (this.editTextPhone != null) {
                        infos += ", " + this.editTextPhone.getText().toString();
                    }

                    Toast.makeText(getApplicationContext(), infos, Toast.LENGTH_SHORT).show();
                    */

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    intent.putExtra("nom", binding.editTextNom.getText().toString());
                    intent.putExtra("prenom", binding.editTextPrenom.getText().toString());
                    intent.putExtra("dateNaissance", binding.editTextDateNaissance.getText().toString());
                    intent.putExtra("ville", binding.editTextVilleNaissance.getText().toString());
                    startActivity(intent);
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.test_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menuReset_infos) {
            resetInformation();
            return true;
        } else if (itemId == R.id.menuAdd_phone) {
            if (!this.phoneAdded) {
                TextView textViewPhone = new TextView(this);
                textViewPhone.setText(getResources().getString(R.string.textPhone));

                this.editTextPhone = new EditText(this);
                this.editTextPhone.setHint(getResources().getString(R.string.textEditHintVotre_phone));
                this.editTextPhone.setInputType(TYPE_CLASS_PHONE);

                this.binding.linearLayout.addView(textViewPhone);
                this.binding.linearLayout.addView(this.editTextPhone);

                this.phoneAdded = true;
            }

            return true;
        } else if (itemId == R.id.menuNavigateur) {
            Uri webpage = Uri.parse("https://fr.wikipedia.org/wiki/" + this.binding.editTextVilleNaissance.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetInformation() {
        this.binding.editTextNom.setText("");
        this.binding.editTextPrenom.setText("");
        this.binding.editTextDateNaissance.setText("");
        this.binding.editTextVilleNaissance.setText("");

        if (this.editTextPhone != null) {
            this.editTextPhone.setText("");
        }
    }
}