package TP0.view.implementation;

import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import TP0.controller.api.ITableController;
import TP0.model.implementation.CamembertModelAdapter;
import TP0.view.api.ITableView;

public class TableView extends JTable
        implements ITableView, MouseListener, MouseMotionListener, PropertyChangeListener
{
    private static final long serialVersionUID = 5616325521930755081L;
    private ITableController controller;
    private CamembertModelAdapter model;
    
    
    public TableView(CamembertModelAdapter model) throws HeadlessException
    {
        super();
        
        addMouseListener(this);

        String[] entetes = {"Titre", "Description", "Prix"};
        
        String[][] data = { { "a", "b", "c" }, { "a", "b", "c" } };
 
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, entetes);

        setModel(defaultTableModel);
        
        buildTable();
    }

    void buildTable()
    {
        
    }
    
    @Override
    public void setController(ITableController controller)
    {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }
}
