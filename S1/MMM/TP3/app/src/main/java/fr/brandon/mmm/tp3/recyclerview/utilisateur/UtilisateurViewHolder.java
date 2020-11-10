package fr.brandon.mmm.tp3.recyclerview.utilisateur;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fr.brandon.mmm.tp3.databinding.RecyclerViewUserItemBinding;
import fr.brandon.mmm.tp3.model.Utilisateur;

public class UtilisateurViewHolder extends RecyclerView.ViewHolder
{
    private RecyclerViewUserItemBinding binding;

    public UtilisateurViewHolder(@NonNull RecyclerViewUserItemBinding recyclerViewUserItemBinding)
    {
        super(recyclerViewUserItemBinding.getRoot());

        this.binding = recyclerViewUserItemBinding;
    }

    public void updateWithUtilisateur(Utilisateur utilisateur)
    {
        this.binding.textViewNom.setText(utilisateur.getNom());
        this.binding.textViewPrenom.setText(utilisateur.getPrenom());
        this.binding.textViewDateNaissance.setText(utilisateur.getDateNaissance());
        this.binding.textViewVille.setText(utilisateur.getVille());
    }
}
