package fr.brandon.mmm.tp2.viewmodel.utilisateur;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.brandon.mmm.tp2.database.utilisateur.UtilisateurRepository;
import fr.brandon.mmm.tp2.model.Utilisateur;

// public class UtilisateurViewModel extends ViewModel
public class UtilisateurViewModel extends AndroidViewModel
{
    private UtilisateurRepository utilisateurRepository;
    private LiveData<List<Utilisateur>> utilisateurs;

    public UtilisateurViewModel(@NonNull Application application)
    {
        super(application);
        this.utilisateurRepository = new UtilisateurRepository(application);
        this.utilisateurs = this.utilisateurRepository.getAllUsers();
    }

    public void insert(Utilisateur utilisateur)
    {
        utilisateurRepository.insert(utilisateur);
    }

    public void delete(Utilisateur utilisateur)
    {
        utilisateurRepository.delete(utilisateur);
    }

    public LiveData<List<Utilisateur>> getUtilisateurs()
    {
        return utilisateurs;
    }

    /*
    private MutableLiveData<List<Utilisateur>> utilisateurs = new MutableLiveData<>();

    public void setUtilisateur(List<Utilisateur> utilisateurs)
    {
        this.utilisateurs.setValue(utilisateurs);
    }

    public MutableLiveData<List<Utilisateur>> getUtilisateurs()
    {
        return this.utilisateurs;
    }
     */
}
