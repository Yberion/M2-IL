package TP1.model.api;

import java.util.List;

import TP1.model.implementation.ItemModel;

public interface ICamembertModel
{
    List<ItemModel> getItems();
    void setItems(List<ItemModel> items);
    String getTitle();
    void setTitle(String titre);
    String getUnit();
    void setUnit(String unit);
    int size();
    double getValues(int i);
    double total();
    String getTitle(int i);
    String getDescription(int i);
    void addItem(ItemModel itemModel);
    void removeItem(ItemModel itemModel);
}
