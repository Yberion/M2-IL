package TP1.controller.api;

import TP1.view.implementation.TableView;

public interface ITableController
{
    boolean isSelected();
    void setSelected(boolean selected);
    int getSelectedPie();
    void setSelectedPie(int selectedPie);
    void setView(TableView view);
    TableView getView();
}
