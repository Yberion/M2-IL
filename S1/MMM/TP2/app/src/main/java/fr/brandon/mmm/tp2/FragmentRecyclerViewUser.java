package fr.brandon.mmm.tp2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.brandon.mmm.tp2.databinding.FragmentRecyclerViewUserBinding;
import fr.brandon.mmm.tp2.model.Utilisateur;
import fr.brandon.mmm.tp2.recyclerview.utilisateur.UtilisateurAdapter;
import fr.brandon.mmm.tp2.viewmodel.utilisateur.UtilisateurViewModel;

public class FragmentRecyclerViewUser extends Fragment
{
    private OnFragmentRecyclerViewUserInteractionListener listener;
    private FragmentRecyclerViewUserBinding binding;
    private UtilisateurAdapter utilisateurAdapter;
    private UtilisateurViewModel utilisateurViewModel;

    public FragmentRecyclerViewUser()
    {
        // Required empty public constructor
    }

    public static FragmentRecyclerViewUser newInstance()
    {
        return new FragmentRecyclerViewUser();
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.utilisateurAdapter = new UtilisateurAdapter();

        // Just for the first time it's loaded
        //populateUser();
    }

    private void populateUser()
    {
        List<Utilisateur> utilisateurs = new ArrayList<>();

        utilisateurs.add(new Utilisateur("Nom1", "Prénom1", "01/01/1901", "Ville1"));
        utilisateurs.add(new Utilisateur("Nom2", "Prénom2", "01/01/1902", "Ville2"));
        utilisateurs.add(new Utilisateur("Nom3", "Prénom3", "01/01/1903", "Ville3"));

        this.utilisateurAdapter.setUtilisateurs(utilisateurs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.binding = FragmentRecyclerViewUserBinding.inflate(inflater, container, false);

        View viewRecyclerViewUser = this.binding.getRoot();

        this.binding.RecyclerViewUtilisateur.setAdapter(utilisateurAdapter);

        this.binding.buttonAddUser.setOnClickListener(view ->
        {
            onButtonAddUserPressed(null);
        });

        return viewRecyclerViewUser;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        this.utilisateurViewModel = new ViewModelProvider(requireActivity()).get(UtilisateurViewModel.class);

        utilisateurViewModel.getUtilisateurs().observe(getViewLifecycleOwner(), utilisateurs ->
        {
            this.utilisateurAdapter.setUtilisateurs(utilisateurs);
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction)
            {
                //utilisateurAdapter.removeUtilisateurAt(viewHolder.getAdapterPosition());
                utilisateurViewModel.delete(utilisateurAdapter.getUtilisateurAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity().getBaseContext(), "User deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(this.binding.RecyclerViewUtilisateur);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof FragmentRecyclerViewUser.OnFragmentRecyclerViewUserInteractionListener)
        {
            this.listener = (FragmentRecyclerViewUser.OnFragmentRecyclerViewUserInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentRecyclerViewUserInteractionListener");
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
        this.utilisateurAdapter = null;
        this.utilisateurViewModel = null;
    }

    private void onButtonAddUserPressed(Uri uri)
    {
        if (this.listener != null)
        {
            //this.utilisateurViewModel.setUtilisateur(this.utilisateurAdapter.getUtilisateurs());
            this.listener.onFragmentRecyclerViewUserInteractionListener(uri);
        }
    }

    public interface OnFragmentRecyclerViewUserInteractionListener
    {
        void onFragmentRecyclerViewUserInteractionListener(Uri uri);
    }
}