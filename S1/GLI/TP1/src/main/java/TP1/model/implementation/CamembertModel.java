package TP1.model.implementation;

import java.util.ArrayList;
import java.util.List;

import TP1.model.api.ICamembertModel;

public class CamembertModel implements ICamembertModel
{
    private List<ItemModel> items;
    private String title;
    private String unit;

    public CamembertModel(String title)
    {
        this.title = title;
        this.unit = "euros";
        this.items = new ArrayList<>();
    }
    
    public CamembertModel(String title, String unit)
    {
        this.title = title;
        this.unit = unit;
        this.items = new ArrayList<>();
    }

    @Override
    public List<ItemModel> getItems()
    {
        return items;
    }

    @Override
    public void setItems(List<ItemModel> items)
    {
        this.items = items;
    }

    @Override
    public String getTitle()
    {
        return title;
    }

    @Override
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    @Override
    public String getUnit()
    {
        return unit;
    }

    @Override
    public void setUnit(String unit)
    {
        this.unit = unit;
    }

    @Override
    public int size()
    {
        return this.items.size();
    }
    
    @Override
    public double getValues(int i)
    {
        return this.items.get(i).getValeur();
    }

    @Override
    public double total()
    {
        double total = 0;
        
        for (ItemModel itemModel : this.items)
        {
            total += itemModel.getValeur();
        }
        
        return total;
    }

    @Override
    public String getTitle(int i)
    {
        return this.items.get(i).getTitle();
    }
    
    @Override
    public String getDescription(int i)
    {
        return this.items.get(i).getDescription();
    }

    @Override
    public void addItem(ItemModel itemModel)
    {
        this.items.add(itemModel);
    }

    @Override
    public void removeItem(ItemModel itemModel)
    {
        this.items.remove(itemModel);
    }
}
