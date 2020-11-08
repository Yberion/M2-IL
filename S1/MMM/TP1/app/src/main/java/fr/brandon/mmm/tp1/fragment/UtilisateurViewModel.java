package fr.brandon.mmm.tp1.fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UtilisateurViewModel extends ViewModel
{
    private final MutableLiveData<Utilisateur> utilisateur = new MutableLiveData<>();

    public void setUtilisateur(Utilisateur utilisateur)
    {
        this.utilisateur.setValue(utilisateur);
    }

    public LiveData<Utilisateur> getUtilisateur()
    {
        return this.utilisateur;
    }
}
