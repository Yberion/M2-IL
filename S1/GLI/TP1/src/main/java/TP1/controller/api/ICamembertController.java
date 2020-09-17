package TP1.controller.api;

import TP1.view.implementation.CamembertView;

public interface ICamembertController
{
    boolean isSelected();
    void setSelected(boolean selected);
    int getSelectedPie();
    void setSelectedPie(int selectedPie);
    void setView(CamembertView view);
    CamembertView getView();
}
