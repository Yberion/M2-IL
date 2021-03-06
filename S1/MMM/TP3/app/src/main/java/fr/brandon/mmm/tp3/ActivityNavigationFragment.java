package fr.brandon.mmm.tp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ActivityNavigationFragment extends AppCompatActivity implements FragmentPutInfo.OnFragmentPutInfoInteractionListener, FragmentRecyclerViewUser.OnFragmentRecyclerViewUserInteractionListener
{
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (FirebaseAuth.getInstance().getCurrentUser() == null)
        {
            Intent intent = new Intent(ActivityNavigationFragment.this, ActivityAuth.class);
            startActivity(intent);
            finish();
        }

        setContentView(R.layout.activity_navigation_fragment);

        this.navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onFragmentPutInfoInteractionListener(Uri uri)
    {
        this.navController.navigate(R.id.action_fragmentPutInfo_to_recyclerViewUser);
    }

    @Override
    public void onFragmentRecyclerViewUserInteractionListener(Uri uri)
    {
        this.navController.navigate(R.id.action_recyclerViewUser_to_fragmentPutInfo);
    }
}