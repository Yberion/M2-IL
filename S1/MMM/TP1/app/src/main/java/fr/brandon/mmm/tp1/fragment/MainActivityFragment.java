package fr.brandon.mmm.tp1.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import fr.brandon.mmm.tp1.R;

public class MainActivityFragment extends AppCompatActivity implements FragmentPutInfo.OnFragmentPutInfoInteractionListener, FragmentDisplayInfo.OnFragmentDisplayInfoInteractionListener
{
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_fragment);

        this.navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    }

    @Override
    public void onFragmentPutInfoInteractionListener(Uri uri)
    {
        this.navController.navigate(R.id.action_fragmentPutInfo_to_fragmentDisplayInfo);
    }

    @Override
    public void onFragmentDisplayInfoInteractionListener(Uri uri)
    {
        this.navController.navigate(R.id.action_fragmentDisplayInfo_to_fragmentPutInfo);
    }
}