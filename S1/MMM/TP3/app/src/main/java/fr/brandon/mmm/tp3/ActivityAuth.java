package fr.brandon.mmm.tp3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

import fr.brandon.mmm.tp3.databinding.ActivityAuthBinding;

// https://firebase.google.com/docs/auth/android/start
// https://github.com/firebase/FirebaseUI-Android/tree/master/auth
// https://www.youtube.com/watch?v=11iGlclnD8s
public class ActivityAuth extends AppCompatActivity
{
    private static final int RC_SIGN_IN = 1337;
    private ActivityAuthBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        createSignInIntent();
    }

    public void createSignInIntent()
    {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(), new AuthUI.IdpConfig.GoogleBuilder().build());

        this.firebaseAuth = FirebaseAuth.getInstance();

        this.authStateListener = firebaseAuth ->
        {
            FirebaseUser user = firebaseAuth.getCurrentUser();

            if (user != null)
            {
                Toast.makeText(ActivityAuth.this, "You are logged as " + user.getEmail(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActivityAuth.this, ActivityNavigationFragment.class);
                startActivity(intent);
                finish();
            }
            else
            {
                // Create and launch sign-in intent
                // We don't use Smart Lock: https://developers.google.com/identity/smartlock-passwords/android/
                // We then don't need the SHA-1 key in the Firebase console
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).setIsSmartLockEnabled(false).setLogo(R.mipmap.ic_launcher_round).build(), RC_SIGN_IN);
            }
        };
    }

    @Override
    public void onStart()
    {
        super.onStart();

        this.firebaseAuth.addAuthStateListener(this.authStateListener);
    }

    @Override
    protected void onStop()
    {
        super.onStop();

        if (this.authStateListener != null)
        {
            this.firebaseAuth.removeAuthStateListener(this.authStateListener);
        }
    }
}