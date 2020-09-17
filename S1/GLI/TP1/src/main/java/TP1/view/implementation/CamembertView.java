package TP1.view.implementation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextLayout;
import java.awt.geom.Arc2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import TP1.controller.api.ICamembertController;
import TP1.model.api.ICamembertModel;
import TP1.model.implementation.CamembertModel;
import TP1.model.implementation.CamembertModelAdapter;
import TP1.view.api.ICamembertView;

// this should actually implement an ICamembertView
public class CamembertView extends JComponent
        implements ICamembertView, MouseListener, MouseMotionListener, PropertyChangeListener
{
    private static final long serialVersionUID = -3093964322108092959L;
    static final Point2D pieCenter = new Point2D.Double(300, 300);
    static final Dimension pieSize = new Dimension(300, 300);
    static final double pieThickness = 50;
    static final Dimension selectedPieSize = new Dimension(320, 320);
    static final double tagOffset = 20;
    static final Dimension tagSizeSelected = new Dimension(130, 100);
    static final Dimension tagSizeNotSelected = new Dimension(100, 30);
    static final Dimension pieCenterSize = new Dimension(150, 150);
    static final double pieRadialGap = 1.0; // < one degree
    ArrayList<Arc2D> arcs;
    ArrayList<Arc2D> selectedArcs;
    Arc2D emptyCenter;
    Arc2D center;
    double startingAngle;
    GeneralPath previous;
    GeneralPath next;
    double prevPosX;
    double prevPosY;
    Graphics2D g2d;
    Image offscreen;
    Font fontCenter;
    Font fontTags;
    private CamembertModelAdapter model;
    private ICamembertController controller;

    public CamembertView(CamembertModelAdapter model)
    {
        this.model = model;
        startingAngle = 0.0;
        // reminder: we don't want the model to have an oberserver: use an adapter
        this.model.addPropertyChangeListener(this);
        addMouseListener(this);
        arcs = new ArrayList<Arc2D>();
        selectedArcs = new ArrayList<Arc2D>();
        setSize(600, 600);
        buildGraphics();
    }

    // build the Arc2D (the pieces of pie)
    public void buildGraphics()
    {
        // create previous button
        int x1Points[] = { 20, 40, 20 };
        int y1Points[] = { 25, 45, 45 };
        previous = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1Points.length);
        previous.moveTo(x1Points[0], y1Points[0]);

        for (int index = 1; index < x1Points.length; index++)
        {
            previous.lineTo(x1Points[index], y1Points[index]);
        } ;
        previous.closePath();
        // create next button
        int x1PointsN[] = { 25, 45, 45 };
        int y1PointsN[] = { 20, 20, 40 };
        next = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x1PointsN.length);
        next.moveTo(x1PointsN[0], y1PointsN[0]);

        for (int index = 1; index < x1Points.length; index++)
        {
            next.lineTo(x1PointsN[index], y1PointsN[index]);
        } ;
        next.closePath();
        // create non-selected arcs
        arcs.clear();
        double angle = startingAngle;

        for (int i = 0; i < model.size(); i++)
        {
            Arc2D arc = new Arc2D.Double(pieCenter.getX() - pieSize.width / 2, pieCenter.getY() - pieSize.height / 2,
                    pieSize.width, pieSize.height, angle, model.getValues(i) / model.total() * 360 - pieRadialGap,
                    Arc2D.PIE);
            arcs.add(arc);
            angle += model.getValues(i) / model.total() * 360;
        }
        // create selected arcs
        selectedArcs.clear();
        angle = startingAngle;

        for (int i = 0; i < model.size(); i++)
        {
            Arc2D arc = new Arc2D.Double(pieCenter.getX() - selectedPieSize.width / 2,
                    pieCenter.getY() - selectedPieSize.height / 2, selectedPieSize.width, selectedPieSize.height, angle,
                    model.getValues(i) / model.total() * 360 - pieRadialGap, Arc2D.PIE);
            selectedArcs.add(arc);
            angle += model.getValues(i) / model.total() * 360;
        }
        // create central arcs
        emptyCenter = new Arc2D.Double(pieCenter.getX() - (pieSize.width / 2 - pieThickness),
                pieCenter.getY() - (pieSize.height / 2 - pieThickness), pieSize.width - 2 * pieThickness,
                pieSize.width - 2 * pieThickness, 0, 360, Arc2D.PIE);
        center = new Arc2D.Double(pieCenter.getX() - pieCenterSize.width / 2,
                pieCenter.getY() - pieCenterSize.height / 2, pieCenterSize.width, pieCenterSize.height, 0, 360,
                Arc2D.PIE);
        fontCenter = new Font("Arial", Font.BOLD, 14);
        fontTags = new Font("Serial", Font.PLAIN, 12);
    }

    public void setController(ICamembertController controller)
    {
        this.controller = controller;
    }

    // Those function should be in the controller
    
    // deselect all pieces
    public void deselectItems()
    {
        controller.setSelected(false);
        paint(getGraphics());
    }

    // select the next piece of pie
    public void nextPie()
    {
        controller.setSelectedPie((controller.getSelectedPie() + 1) % model.size());
        System.out.println("Selected pie next" + controller.getSelectedPie());
        paint(getGraphics());
    }

    // select the previous piece of pie
    public void previousPie()
    {
        controller.setSelectedPie((controller.getSelectedPie() + model.size() - 1) % model.size());
        System.out.println("Selected pie previous" + controller.getSelectedPie());
        paint(getGraphics());
    }

    // select a piece of pie
    public void selectPie(int i)
    {
        controller.setSelected(true);
        controller.setSelectedPie(i);
        System.out.println("Selected pie" + i);
        paint(getGraphics());
    }

    private void drawPreviousNextButtons(Graphics2D g2d)
    {
        g2d.setColor(Color.RED);
        g2d.fill(previous);
        g2d.fill(next);
    }

    public double positionXOnCircle(double radius, double angle)
    {
        return radius * Math.cos(angle * Math.PI / 180.0);
    }

    public double positionYOnCircle(double radius, double angle)
    {
        return radius * Math.sin(angle * Math.PI / 180.0);
    }

    // How to draw the Pie Chart. called everytime a refresh is performed by the UI
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Dimension d = getSize();

        if (offscreen == null || offscreen.getWidth(null) != d.width || offscreen.getHeight(null) != d.height)
        {
            offscreen = createImage(d.width, d.height);
        }
        Graphics offG = offscreen.getGraphics();
        offG.setColor(getBackground());
        offG.fillRect(0, 0, d.width, d.height);
        // Draw into the offscreen image.
        // g2d = (Graphics2D) g;
        g2d = (Graphics2D) offG;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.WHITE);
        Rectangle2D rect2D = new Rectangle(0, 0, d.width, d.height);
        g2d.fill(rect2D);

        if (controller.isSelected())
        {
            drawPreviousNextButtons(g2d);
        }
        double angle = startingAngle;

        for (int i = 0; i < model.size(); i++)
        {

            if (controller.isSelected() && controller.getSelectedPie() == i)
            {
                g2d.setColor(new Color(0, 100, (100 + 20 * i) % 255));
                Arc2D arc = selectedArcs.get(i);
                arc.setAngleStart(angle);
                arc.setAngleExtent(model.getValues(i) / model.total() * 360 - pieRadialGap);
                g2d.fill(arc);
                // draw detailed information
            }
            else
            {
                g2d.setColor(new Color(100, 100, (100 + 20 * i) % 255));
                Arc2D arc = arcs.get(i);
                arc.setAngleStart(angle);
                arc.setAngleExtent(model.getValues(i) / model.total() * 360 - pieRadialGap);
                g2d.fill(arc);
            }
            angle += model.getValues(i) / model.total() * 360;
        }
        g2d.setColor(Color.WHITE);
        g2d.fill(emptyCenter);
        GradientPaint gp = new GradientPaint(0, 110, Color.WHITE, 0, 150, Color.BLUE);
        g2d.setPaint(gp);
        // g2d.setColor(Color.BLUE);
        g2d.fill(center);
        g2d.setFont(fontCenter);
        g2d.setColor(Color.WHITE);
        g2d.drawString(model.getTitle(),
                (int) (pieCenter.getX() - model.getTitle().length() / 2 * fontCenter.getSize() * 0.7),
                (int) pieCenter.getY() - fontCenter.getSize());
        g2d.setFont(fontTags);
        g2d.setColor(Color.WHITE);
        String total = "" + model.total() + " " + model.getUnit();
        g2d.drawString(total, (int) (pieCenter.getX() - total.length() / 2 * fontTags.getSize() * 0.7),
                (int) pieCenter.getY() - fontTags.getSize() + 20);

        // draw tags
        if (!controller.isSelected())
        {
            angle = startingAngle;

            for (int i = 0; i < model.size(); i++)
            {
                double midangle = angle + model.getValues(i) / model.total() * 360.0 / 2.0;
                double x = pieCenter.getX() + positionXOnCircle(pieSize.getWidth() / 2 + tagOffset, midangle);
                double y = pieCenter.getY() - positionYOnCircle(pieSize.getHeight() / 2 + tagOffset, midangle);
                g2d.setColor(new Color(100, 100, (100 + 20 * i) % 255));

                if (y < pieCenter.getY())
                {

                    if (x > pieCenter.getX())
                    { // top right
                        Rectangle2D tag = new Rectangle2D.Double(x, y - tagSizeNotSelected.height,
                                tagSizeNotSelected.width, tagSizeNotSelected.height);
                        g2d.fill(tag);
                        GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                        stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                pieCenter.getY() - positionYOnCircle(pieSize.width / 2, midangle));
                        stem.lineTo(x + 1, y - tagSizeNotSelected.height);
                        stem.lineTo(x + 1, y + 3);
                        stem.closePath();
                        g2d.fill(stem);
                        g2d.setColor(Color.black);
                        Rectangle2D tagUnderline = new Rectangle2D.Double(x, y, tagSizeNotSelected.width, 3);
                        g2d.fill(tagUnderline);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString(model.getTitle(i), (int) x + 15, (int) y - tagSizeNotSelected.height + 15);
                    }
                    else
                    { // top left
                        Rectangle2D tag = new Rectangle2D.Double(x - tagSizeNotSelected.width,
                                y - tagSizeNotSelected.height, tagSizeNotSelected.width, tagSizeNotSelected.height);
                        g2d.fill(tag);
                        GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                        stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                pieCenter.getY() - positionYOnCircle(pieSize.width / 2, midangle));
                        stem.lineTo(x - 1, y - tagSizeNotSelected.height);
                        stem.lineTo(x - 1, y + 3);
                        stem.closePath();
                        g2d.fill(stem);
                        g2d.setColor(Color.black);
                        Rectangle2D tagUnderline = new Rectangle2D.Double(x - tagSizeNotSelected.width, y,
                                tagSizeNotSelected.width, 3);
                        g2d.fill(tagUnderline);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString(model.getTitle(i), (int) x - tagSizeNotSelected.width + 15,
                                (int) y - tagSizeNotSelected.height + 15);
                    }
                }
                else
                {

                    if (x > pieCenter.getX())
                    { // bottom right
                        Rectangle2D tag = new Rectangle2D.Double(x, y, tagSizeNotSelected.width,
                                tagSizeNotSelected.height);
                        g2d.fill(tag);
                        GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                        stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                pieCenter.getY() - positionYOnCircle(pieSize.width / 2, midangle));
                        stem.lineTo(x + 1, y);
                        stem.lineTo(x + 1, y + tagSizeNotSelected.height + 3);
                        stem.closePath();
                        g2d.fill(stem);
                        g2d.setColor(Color.black);
                        Rectangle2D tagUnderline = new Rectangle2D.Double(x, y + tagSizeNotSelected.height,
                                tagSizeNotSelected.width, 3);
                        g2d.fill(tagUnderline);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString(model.getTitle(i), (int) x + 15, (int) y + 15);
                    }
                    else
                    { // bottom left
                        Rectangle2D tag = new Rectangle2D.Double(x - tagSizeNotSelected.width, y,
                                tagSizeNotSelected.width, tagSizeNotSelected.height);
                        g2d.fill(tag);
                        GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                        stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                pieCenter.getY() - positionYOnCircle(pieSize.width / 2, midangle));
                        stem.lineTo(x - 1, y);
                        stem.lineTo(x - 1, y + tagSizeNotSelected.height + 3);
                        stem.closePath();
                        g2d.fill(stem);
                        g2d.setColor(Color.black);
                        Rectangle2D tagUnderline = new Rectangle2D.Double(x - tagSizeNotSelected.width,
                                y + tagSizeNotSelected.height, tagSizeNotSelected.width, 3);
                        g2d.fill(tagUnderline);
                        g2d.setColor(Color.WHITE);
                        g2d.drawString(model.getTitle(i), (int) x - tagSizeNotSelected.width + 15, (int) y + 15);
                    }
                }
                angle += model.getValues(i) / model.total() * 360;
            }
        }

        if (controller.isSelected())
        {
            angle = startingAngle;

            for (int i = 0; i < model.size(); i++)
            {

                if (i == controller.getSelectedPie())
                {
                    double midangle = angle + model.getValues(i) / model.total() * 360.0 / 2.0;
                    double x = pieCenter.getX() + positionXOnCircle(160, midangle);
                    double y = pieCenter.getX() - positionYOnCircle(160, midangle);
                    g2d.setColor(new Color(100, 100, (100 + 20 * i) % 255));

                    if (y < pieCenter.getY())
                    {

                        if (x > pieCenter.getX())
                        { // top right
                            Rectangle2D tag = new Rectangle2D.Double(x, y - tagSizeSelected.height,
                                    tagSizeSelected.width, tagSizeSelected.height);
                            g2d.fill(tag);
                            GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                            stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                    pieCenter.getY() - positionYOnCircle(pieSize.height / 2, midangle));
                            stem.lineTo(x + 1, y - tagSizeSelected.height);
                            stem.lineTo(x + 1, y + 3);
                            stem.closePath();
                            g2d.fill(stem);
                            g2d.setColor(Color.black);
                            Rectangle2D tagUnderline = new Rectangle2D.Double(x, y, tagSizeSelected.width, 3);
                            g2d.fill(tagUnderline);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString(model.getTitle(i), (int) x + 15, (int) y - tagSizeSelected.height + 15);
                            g2d.drawString("" + model.getValues(i) + " " + model.getUnit(), (int) x + 15,
                                    (int) y - tagSizeSelected.height + 30);
                            //
                            x += 15;
                            y = y - tagSizeSelected.height + 45;
                            AttributedCharacterIterator characterIterator = new AttributedString(
                                    model.getDescription(i)).getIterator();
                            FontRenderContext fontRenderContext = g2d.getFontRenderContext();
                            LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);

                            while (measurer.getPosition() < characterIterator.getEndIndex())
                            {
                                TextLayout textLayout = measurer.nextLayout(tagSizeSelected.width - 15);
                                y += textLayout.getAscent();
                                textLayout.draw(g2d, (int) x, (int) y);
                                y += textLayout.getDescent() + textLayout.getLeading();
                            }
                        }
                        else
                        { // top left
                            Rectangle2D tag = new Rectangle2D.Double(x - tagSizeSelected.width,
                                    y - tagSizeSelected.height, tagSizeSelected.width, tagSizeSelected.height);
                            g2d.fill(tag);
                            GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                            stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                    pieCenter.getY() - positionYOnCircle(pieSize.height / 2, midangle));
                            stem.lineTo(x - 1, y - tagSizeSelected.height);
                            stem.lineTo(x - 1, y + 3);
                            stem.closePath();
                            g2d.fill(stem);
                            g2d.setColor(Color.black);
                            Rectangle2D tagUnderline = new Rectangle2D.Double(x - tagSizeSelected.width, y,
                                    tagSizeSelected.width, 3);
                            g2d.fill(tagUnderline);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString(model.getTitle(i), (int) x - tagSizeSelected.width + 15,
                                    (int) y - tagSizeSelected.height + 15);
                            g2d.drawString("" + model.getValues(i) + " " + model.getUnit(),
                                    (int) x - tagSizeSelected.width + 15, (int) y - tagSizeSelected.height + 30);
                            x = x - tagSizeSelected.width + 15;
                            y = y - tagSizeSelected.height + 45;
                            AttributedCharacterIterator characterIterator = new AttributedString(
                                    model.getDescription(i)).getIterator();
                            FontRenderContext fontRenderContext = g2d.getFontRenderContext();
                            LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);

                            while (measurer.getPosition() < characterIterator.getEndIndex())
                            {
                                TextLayout textLayout = measurer.nextLayout(tagSizeSelected.width - 15);
                                y += textLayout.getAscent();
                                textLayout.draw(g2d, (int) x, (int) y);
                                y += textLayout.getDescent() + textLayout.getLeading();
                            }
                        }
                    }
                    else
                    {

                        if (x > pieCenter.getX())
                        { // bottom right
                            Rectangle2D tag = new Rectangle2D.Double(x, y, tagSizeSelected.width,
                                    tagSizeSelected.height);
                            g2d.fill(tag);
                            GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                            stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                    pieCenter.getY() - positionYOnCircle(pieSize.height / 2, midangle));
                            stem.lineTo(x + 1, y);
                            stem.lineTo(x + 1, y + tagSizeSelected.height + 3);
                            stem.closePath();
                            g2d.fill(stem);
                            g2d.setColor(Color.black);
                            Rectangle2D tagUnderline = new Rectangle2D.Double(x, y + tagSizeSelected.height,
                                    tagSizeSelected.width, 3);
                            g2d.fill(tagUnderline);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString(model.getTitle(i), (int) x + 15, (int) y + 15);
                            g2d.drawString("" + model.getValues(i) + " " + model.getUnit(), (int) x + 15, (int) y + 30);
                            x = x + 15;
                            y = y + 45;
                            AttributedCharacterIterator characterIterator = new AttributedString(
                                    model.getDescription(i)).getIterator();
                            FontRenderContext fontRenderContext = g2d.getFontRenderContext();
                            LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);

                            while (measurer.getPosition() < characterIterator.getEndIndex())
                            {
                                TextLayout textLayout = measurer.nextLayout(tagSizeSelected.width - 15);
                                y += textLayout.getAscent();
                                textLayout.draw(g2d, (int) x, (int) y);
                                y += textLayout.getDescent() + textLayout.getLeading();
                            }
                        }
                        else
                        { // bottom left
                            Rectangle2D tag = new Rectangle2D.Double(x - tagSizeSelected.width, y,
                                    tagSizeSelected.width, tagSizeSelected.height);
                            g2d.fill(tag);
                            GeneralPath stem = new GeneralPath(GeneralPath.WIND_EVEN_ODD, 3);
                            stem.moveTo(pieCenter.getX() + positionXOnCircle(pieSize.width / 2, midangle),
                                    pieCenter.getY() - positionYOnCircle(pieSize.height / 2, midangle));
                            stem.lineTo(x - 1, y);
                            stem.lineTo(x - 1, y + tagSizeSelected.height + 3);
                            stem.closePath();
                            g2d.fill(stem);
                            g2d.setColor(Color.black);
                            Rectangle2D tagUnderline = new Rectangle2D.Double(x - tagSizeSelected.width,
                                    y + tagSizeSelected.height, tagSizeSelected.width, 3);
                            g2d.fill(tagUnderline);
                            g2d.setColor(Color.WHITE);
                            g2d.drawString(model.getTitle(i), (int) x - tagSizeSelected.width + 15, (int) y + 15);
                            g2d.drawString("" + model.getValues(i) + " " + model.getUnit(),
                                    (int) x - tagSizeSelected.width + 15, (int) y + 30);
                            x = x - tagSizeSelected.width + 15;
                            y = y + 45;
                            AttributedCharacterIterator characterIterator = new AttributedString(
                                    model.getDescription(i)).getIterator();
                            FontRenderContext fontRenderContext = g2d.getFontRenderContext();
                            LineBreakMeasurer measurer = new LineBreakMeasurer(characterIterator, fontRenderContext);

                            while (measurer.getPosition() < characterIterator.getEndIndex())
                            {
                                TextLayout textLayout = measurer.nextLayout(tagSizeSelected.width - 15);
                                y += textLayout.getAscent();
                                textLayout.draw(g2d, (int) x, (int) y);
                                y += textLayout.getDescent() + textLayout.getLeading();
                            }
                        }
                    }
                }
                angle += model.getValues(i) / model.total() * 360;
            }
        }
        // Put the offscreen image on the screen.
        g.drawImage(offscreen, 0, 0, null);
    }

    public void computeRotation(int x, int y)
    {
        double dx = pieCenter.getX() - x;
        double dy = pieCenter.getY() - y;
        double angle1 = Math.atan2(dy, dx) / Math.PI * 180;
        dx = pieCenter.getX() - prevPosX;
        dy = pieCenter.getY() - prevPosY;
        double angle2 = Math.atan2(dy, dx) / Math.PI * 180;
        startingAngle += (angle2 - angle1);
        prevPosX = x;
        prevPosY = y;
    }
    // if the user clicks on a pie, it gets selected, otherwise you deselect all.

    @Override
    public void mouseClicked(MouseEvent arg0)
    {

        if (center.contains(arg0.getX(), arg0.getY()))
        {
            this.deselectItems();
        }
        else
        {

            for (int i = 0; i < arcs.size(); i++)
            {

                if (arcs.get(i).contains(arg0.getX(), arg0.getY()) && !emptyCenter.contains(arg0.getX(), arg0.getY()))
                {
                    this.selectPie(i);
                }
            }
        }

        if (previous.contains(arg0.getX(), arg0.getY()))
        {
            this.nextPie();
        }

        if (next.contains(arg0.getX(), arg0.getY()))
        {
            this.previousPie();
        }
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    {
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        prevPosX = arg0.getX();
        prevPosY = arg0.getY();
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
    }

    // if the user drags a pie we rotate it by a given angle 'angle1'
    @Override
    public void mouseDragged(MouseEvent e)
    {
        // difference in x from center:
        double dx = pieCenter.getX() - e.getX();
        double dy = pieCenter.getY() - e.getY();
        double angle1 = Math.atan2(dy, dx) / Math.PI * 180;
        dx = pieCenter.getX() - prevPosX;
        dy = pieCenter.getY() - prevPosY;
        double angle2 = Math.atan2(dy, dx) / Math.PI * 180;
        startingAngle += (angle2 - angle1);
        prevPosX = e.getX();
        prevPosY = e.getY();
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt)
    {
        buildGraphics();
        paint(getGraphics());
    }
}
