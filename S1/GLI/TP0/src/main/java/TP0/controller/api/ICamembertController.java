package TP0.controller.api;

import TP0.view.implementation.CamembertView;

public interface ICamembertController
{
    boolean isSelected();
    void setSelected(boolean selected);
    int getSelectedPie();
    void setSelectedPie(int selectedPie);
    void setView(CamembertView view);
    CamembertView getView();
}
