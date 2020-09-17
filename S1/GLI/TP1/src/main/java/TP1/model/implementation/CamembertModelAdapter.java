package TP1.model.implementation;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class CamembertModelAdapter extends CamembertModel
{
    private PropertyChangeSupport observable;

    public CamembertModelAdapter(String title)
    {
        super(title);
        this.observable = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener observer)
    {
        observable.addPropertyChangeListener(observer);
    }

    public void removePropertyChangeListener(PropertyChangeListener observer)
    {
        observable.removePropertyChangeListener(observer);
    }
    
    @Override
    public void setItems(List<ItemModel> items)
    {
        super.setItems(items);
        observable.firePropertyChange("items", null, null);
    }
    
    @Override
    public void setTitle(String title)
    {
        super.setTitle(title);
        observable.firePropertyChange("title", null, null);
    }
    
    @Override
    public void setUnit(String unit)
    {
        super.setUnit(unit);
        observable.firePropertyChange("unit", null, null);
    }
    
    @Override
    public void addItem(ItemModel itemModel)
    {
        super.addItem(itemModel);
        observable.firePropertyChange("addItem", null, null);
    }

    @Override
    public void removeItem(ItemModel itemModel)
    {
        super.removeItem(itemModel);
        observable.firePropertyChange("removeItem", null, null);
    }
}
