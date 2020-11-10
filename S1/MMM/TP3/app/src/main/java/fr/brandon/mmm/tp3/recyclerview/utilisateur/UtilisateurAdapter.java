package fr.brandon.mmm.tp3.recyclerview.utilisateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import fr.brandon.mmm.tp3.databinding.RecyclerViewUserItemBinding;
import fr.brandon.mmm.tp3.model.Utilisateur;

public class UtilisateurAdapter extends FirestoreRecyclerAdapter<Utilisateur, UtilisateurViewHolder>
{
    public UtilisateurAdapter(@NonNull FirestoreRecyclerOptions<Utilisateur> options)
    {
        super(options);
    }

    @NonNull
    @Override
    public UtilisateurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerViewUserItemBinding recyclerViewUserItemBinding = RecyclerViewUserItemBinding.inflate(layoutInflater, parent, false);

        return new UtilisateurViewHolder(recyclerViewUserItemBinding);
    }

    @Override
    protected void onBindViewHolder(@NonNull UtilisateurViewHolder holder, int position, @NonNull Utilisateur model)
    {
        holder.updateWithUtilisateur(model);
    }

    public void deleteUsertAt(int adapterPosition)
    {
        getSnapshots().getSnapshot(adapterPosition).getReference().delete();
    }
}
