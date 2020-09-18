package TP0.view.api;

import TP0.controller.api.ICamembertController;

public interface ICamembertView
{
    void setController(ICamembertController controller);

    void deselectItems();

    void nextPie();

    void previousPie();

    void selectPie(int i);
}
