package TP0.controller.implementation;

import TP0.controller.api.ICamembertController;
import TP0.view.implementation.CamembertView;

public class CamembertController implements ICamembertController
{
    private boolean selected;
    private int selectedPie;
    private CamembertView view;

    @Override
    public boolean isSelected()
    {
        return selected;
    }

    @Override
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    @Override
    public int getSelectedPie()
    {
        return selectedPie;
    }

    @Override
    public void setSelectedPie(int selectedPie)
    {
        this.selectedPie = selectedPie;
    }

    @Override
    public CamembertView getView()
    {
        return view;
    }

    @Override
    public void setView(CamembertView view)
    {
        this.view = view;
    }
}
