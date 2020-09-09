package TP1.view.api;

import TP1.controller.api.ICamembertController;

public interface ICamembertView
{
    void setController(ICamembertController controller);

    void deselectItems();

    void nextPie();

    void previousPie();

    void selectPie(int i);
}
