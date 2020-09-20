package TP0.controller.implementation;

import TP0.controller.api.ICamembertController;
import TP0.model.api.ICamembertModel;
import TP0.view.implementation.CamembertView;

public class CamembertController implements ICamembertController
{
    private boolean selected;
    private int selectedPie;
    private CamembertView view;
    private ICamembertModel model;
    
    public CamembertController(ICamembertModel model)
    {
        super();
        this.model = model;
    }

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

    @Override
    public void deselectItems()
    {
        this.selected = false;
        this.view.repaint();
    }

    @Override
    public void nextPie()
    {
        this.selectedPie = (this.selectedPie + 1) % model.size();
        System.out.println("Selected pie next" + this.selectedPie);
        this.view.repaint();
    }

    @Override
    public void previousPie()
    {
        this.selectedPie = (this.selectedPie + model.size() - 1) % model.size();
        System.out.println("Selected pie previous" + this.selectedPie);
        this.view.repaint();
    }

    @Override
    public void selectPie(int i)
    {
        this.selected = true;
        this.selectedPie = i;
        System.out.println("Selected pie" + i);
        this.view.repaint();
    }
}
