/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TP0;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import TP0.controller.api.ICamembertController;
import TP0.controller.api.ITableController;
import TP0.controller.implementation.CamembertController;
import TP0.controller.implementation.TableController;
import TP0.model.implementation.CamembertModelAdapter;
import TP0.model.implementation.ItemModel;
import TP0.view.implementation.CamembertView;
import TP0.view.implementation.TableView;

public class App
{
    // this main method should actually be placed in another class (it's here just to avoid having multiple files)
    public static void main(String... args)
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 400, 400);
        // Create an instance of the model
        CamembertModelAdapter model = new CamembertModelAdapter("Pizza");
        // Maybe put some data in the model
        
        model.addItem(new ItemModel("P�te", "P�te �paisse", 0.84));
        model.addItem(new ItemModel("Fromage", "Mozzarella", 1.25));
        model.addItem(new ItemModel("Jambon", "Agriculture locale", 2.20));
        model.addItem(new ItemModel("Sauce tomate", "Tomate du jardin", 1.20));
        
        // Create the controller and the view and link all together
        ICamembertController controller = new CamembertController(model);
        CamembertView view = new CamembertView(model);
        view.setController(controller);
        controller.setView(view);
        
        ITableController controllerTable = new TableController();
        TableView viewTable = new TableView(model);
        viewTable.setController(controllerTable);
        controllerTable.setView(viewTable);
        
        // display layout
        GridLayout layout = new GridLayout(1, 3);
        JButton ajoutItem = new JButton("Ajout toto item");
        
        ajoutItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.addItem(new ItemModel("toto", "oui", 1.0));
            }
        });
        
        window.getContentPane().add(controller.getView());
        window.getContentPane().add(controllerTable.getView());
        window.getContentPane().add(ajoutItem);
        window.setLayout(layout);
        window.pack();
        window.setVisible(true);
        // window.pack();
    }
}
