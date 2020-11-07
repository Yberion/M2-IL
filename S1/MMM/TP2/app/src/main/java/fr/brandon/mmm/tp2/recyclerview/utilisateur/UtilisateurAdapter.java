package fr.brandon.mmm.tp2.recyclerview.utilisateur;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.brandon.mmm.tp2.databinding.RecyclerViewUserItemBinding;
import fr.brandon.mmm.tp2.model.Utilisateur;

public class UtilisateurAdapter extends RecyclerView.Adapter<UtilisateurViewHolder>
{
    private List<Utilisateur> utilisateurs;

    public UtilisateurAdapter()
    {
        this.utilisateurs = new ArrayList<>();
    }

    public UtilisateurAdapter(List<Utilisateur> utilisateurs)
    {
        this.utilisateurs = utilisateurs;
    }

    public void updateUtilisateurs(List<Utilisateur> utilisateurs)
    {
        this.utilisateurs = utilisateurs;
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
    public void onBindViewHolder(@NonNull UtilisateurViewHolder holder, int position)
    {
        Utilisateur utilisateur = this.utilisateurs.get(position);

        holder.updateWithUtilisateur(utilisateur);
    }

    @Override
    public int getItemCount()
    {
        return this.utilisateurs.size();
    }
}
