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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import fr.brandon.mmm.tp3.databinding.FragmentRecyclerViewUserBinding;
import fr.brandon.mmm.tp3.model.Utilisateur;
import fr.brandon.mmm.tp3.recyclerview.utilisateur.UtilisateurAdapter;

// FirestoreRecyclerAdapter: https://www.youtube.com/watch?v=cBwaJYocb9I
// Paging: https://www.youtube.com/watch?v=LatlcDZhpd4
// The FirebaseUI paging adapter does not use real-time listeners. According to the documentation:
// So you'll have to choose: either you can have real-time updates, or you can have pagination, but you can't have both with the adapters that come with FirebaseUI.
//
// Another Playlist: https://www.youtube.com/watch?v=ub6mNHWGVHw&list=PLrnPJCHvNZuAXdWxOzsN5rgG2M4uJ8bH1
public class FragmentRecyclerViewUser extends Fragment
{
    private OnFragmentRecyclerViewUserInteractionListener listener;
    private FragmentRecyclerViewUserBinding binding;
    private UtilisateurAdapter utilisateurAdapter;
    private FirebaseFirestore firebaseFirestore;

    public FragmentRecyclerViewUser()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        this.firebaseFirestore = FirebaseFirestore.getInstance();

        CollectionReference query = this.firebaseFirestore.collection("utilisateurs");

        FirestoreRecyclerOptions<Utilisateur> utilisateurs = new FirestoreRecyclerOptions.Builder<Utilisateur>().setQuery(query, Utilisateur.class).setLifecycleOwner(this).build();

        this.utilisateurAdapter = new UtilisateurAdapter(utilisateurs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.binding = FragmentRecyclerViewUserBinding.inflate(inflater, container, false);

        this.binding.RecyclerViewUtilisateur.setAdapter(utilisateurAdapter);

        this.binding.buttonAddUser.setOnClickListener(view ->
        {
            onButtonAddUserPressed(null);
        });

        return this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                utilisateurAdapter.deleteUsertAt(viewHolder.getAdapterPosition());
                Toast.makeText(requireActivity().getBaseContext(), "User deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(this.binding.RecyclerViewUtilisateur);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_signout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();

        if (itemId == R.id.menuSignout)
        {
            signout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void signout()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null)
        {
            AuthUI.getInstance().signOut(getActivity().getBaseContext()).addOnCompleteListener(task ->
            {
                Toast.makeText(getActivity().getBaseContext(), "Logged out " + user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity().getBaseContext(), ActivityAuth.class);
                startActivity(intent);
                getActivity().finish();
            });
        }
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

    /*
    //Plus besoin grâce au ".setLifecycleOwner(this)" sur le FirestoreRecyclerAdapter
    @Override
    public void onStart()
    {
        super.onStart();
        this.utilisateurAdapter.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        this.utilisateurAdapter.stopListening();
    }
    */

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
    }

    private void onButtonAddUserPressed(Uri uri)
    {
        if (this.listener != null)
        {
            this.listener.onFragmentRecyclerViewUserInteractionListener(uri);
        }
    }

    public interface OnFragmentRecyclerViewUserInteractionListener
    {
        void onFragmentRecyclerViewUserInteractionListener(Uri uri);
    }
}