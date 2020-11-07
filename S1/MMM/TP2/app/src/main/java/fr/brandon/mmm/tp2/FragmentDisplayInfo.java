package fr.brandon.mmm.tp2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import fr.brandon.mmm.tp2.databinding.FragmentDisplayInfoBinding;

public class FragmentDisplayInfo extends Fragment
{
    private FragmentDisplayInfoBinding binding;
    private OnFragmentDisplayInfoInteractionListener listener;

    public FragmentDisplayInfo()
    {
        // Required empty public constructor
    }

    public static FragmentPutInfo newInstance()
    {
        return new FragmentPutInfo();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.binding = FragmentDisplayInfoBinding.inflate(inflater, container, false);

        View viewPutInfo = this.binding.getRoot();

        this.binding.buttonRetour.setOnClickListener(view ->
        {
            onButtonPressedRetour(null);
        });

        return viewPutInfo;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        if (context instanceof OnFragmentDisplayInfoInteractionListener)
        {
            this.listener = (OnFragmentDisplayInfoInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement OnFragmentPutInfoInteractionListener");
        }
    }

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

    private void onButtonPressedRetour(Uri uri)
    {
        if (this.listener != null)
        {
            this.listener.onFragmentDisplayInfoInteractionListener(uri);
        }
    }

    public interface OnFragmentDisplayInfoInteractionListener
    {
        void onFragmentDisplayInfoInteractionListener(Uri uri);
    }
}