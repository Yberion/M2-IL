package fr.brandon.mmm.tp3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import fr.brandon.mmm.tp3.databinding.FragmentPutInfoBinding;
import fr.brandon.mmm.tp3.model.Utilisateur;

import static android.text.InputType.TYPE_CLASS_PHONE;

public class FragmentPutInfo extends Fragment
{
    private FragmentPutInfoBinding binding;
    private OnFragmentPutInfoInteractionListener listener;
    private boolean phoneAdded;
    private EditText editTextPhone;

    public FragmentPutInfo()
    {
        // Required empty public constructor
    }

    public static FragmentPutInfo newInstance()
    {
        return new FragmentPutInfo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.binding = FragmentPutInfoBinding.inflate(inflater, container, false);

        View viewPutInfo = this.binding.getRoot();

        this.binding.buttonValider.setOnClickListener(view ->
        {
            onButtonPressedValider(null);
        });

        return viewPutInfo;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentPutInfoInteractionListener)
        {
            this.listener = (OnFragmentPutInfoInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentPutInfoInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        this.listener = null;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        this.binding = null;
        this.editTextPhone = null;
    }

    private void onButtonPressedValider(Uri uri)
    {
        if (this.listener != null)
        {
            CollectionReference utilisateurRef = FirebaseFirestore.getInstance().collection("utilisateurs");
            utilisateurRef.add(new Utilisateur(this.binding.editTextNom.getText().toString(), this.binding.editTextPrenom.getText().toString(), this.binding.editTextVilleNaissance.getText().toString(), this.binding.editTextDateNaissance.getText().toString()));
            this.listener.onFragmentPutInfoInteractionListener(uri);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.test_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();

        if (itemId == R.id.menuReset_infos)
        {
            resetInformation();
            return true;
        }
        else if (itemId == R.id.menuAdd_phone)
        {
            if (!this.phoneAdded)
            {
                TextView textViewPhone = new TextView(this.binding.getRoot().getContext());
                textViewPhone.setText(getResources().getString(R.string.textPhone));

                this.editTextPhone = new EditText(this.binding.getRoot().getContext());
                this.editTextPhone.setHint(getResources().getString(R.string.textEditHintVotre_phone));
                this.editTextPhone.setInputType(TYPE_CLASS_PHONE);

                this.binding.linearLayout.addView(textViewPhone);
                this.binding.linearLayout.addView(this.editTextPhone);

                this.phoneAdded = true;
            }

            return true;
        }
        else if (itemId == R.id.menuNavigateur)
        {
            Uri webpage = Uri.parse("https://fr.wikipedia.org/wiki/" + this.binding.editTextVilleNaissance.getText());
            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void resetInformation()
    {
        this.binding.editTextNom.setText("");
        this.binding.editTextPrenom.setText("");
        this.binding.editTextDateNaissance.setText("");
        this.binding.editTextVilleNaissance.setText("");

        if (this.editTextPhone != null)
        {
            this.editTextPhone.setText("");
        }
    }

    public interface OnFragmentPutInfoInteractionListener
    {
        void onFragmentPutInfoInteractionListener(Uri uri);
    }
}