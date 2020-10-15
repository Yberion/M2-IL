package fr.brandon.mmm.tp1;

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
                    String infos = "";

                    infos += binding.editTextNom.getText() + " ";
                    infos += binding.editTextPrenom.getText() + ", né le ";
                    infos += binding.editTextDateNaissance.getText() + " à ";
                    infos += binding.editTextVilleNaissance.getText();

                    if (this.editTextPhone != null) {
                        infos += ", " + this.editTextPhone.getText();
                    }

                    Toast.makeText(getApplicationContext(), infos, Toast.LENGTH_SHORT).show();
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

        if (itemId == R.id.reset_infos) {
            resetInformation();
            return true;
        } else if (itemId == R.id.add_phone) {
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