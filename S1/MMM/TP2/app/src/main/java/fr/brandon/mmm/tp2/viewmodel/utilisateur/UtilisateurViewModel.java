package fr.brandon.mmm.tp2.viewmodel.utilisateur;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import fr.brandon.mmm.tp2.model.Utilisateur;

public class UtilisateurViewModel extends ViewModel
{
    private MutableLiveData<List<Utilisateur>> utilisateurs = new MutableLiveData<>();

    public void setUtilisateur(List<Utilisateur> utilisateurs)
    {
        this.utilisateurs.setValue(utilisateurs);
    }

    public MutableLiveData<List<Utilisateur>> getUtilisateurs()
    {
        return this.utilisateurs;
    }
}
