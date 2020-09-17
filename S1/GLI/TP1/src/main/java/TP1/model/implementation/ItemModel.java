package TP1.model.implementation;

import TP1.model.api.IItemModel;

public class ItemModel implements IItemModel
{
    private String title;
    private String description;
    private Double valeur;

    public ItemModel(String title, String description, Double valeur)
    {
        super();
        this.title = title;
        this.description = description;
        this.valeur = valeur;
    }

    @Override
    public String getTitle()
    {
        return this.title;
    }

    @Override
    public void setTitle(String title)
    {
        this.title = title;
    }

    @Override
    public String getDescription()
    {
        return description;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public Double getValeur()
    {
        return valeur;
    }

    @Override
    public void setValeur(Double valeur)
    {
        this.valeur = valeur;
    }
}
