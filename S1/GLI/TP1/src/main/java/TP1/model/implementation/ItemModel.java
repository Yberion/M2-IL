package TP1.model.implementation;

import TP1.model.api.IItemModel;

public class ItemModel implements IItemModel
{
    private String title;
    private String description;
    private Double valeur;
    
    public String getTitle()
    {
        return this.title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public String getDescription()
    {
        return description;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }
    public Double getValeur()
    {
        return valeur;
    }
    public void setValeur(Double valeur)
    {
        this.valeur = valeur;
    }
    
    
}
