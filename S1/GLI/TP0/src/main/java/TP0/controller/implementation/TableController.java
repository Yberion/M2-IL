package TP0.controller.implementation;

import TP0.controller.api.ITableController;
import TP0.view.implementation.TableView;

public class TableController implements ITableController
{
    private boolean selected;
    private int selectedPie;
    private TableView view;

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
    public TableView getView()
    {
        return view;
    }

    @Override
    public void setView(TableView view)
    {
        this.view = view;
    }
}
