package fr.brandon.aoc.tp1.canal.impl;

import fr.brandon.aoc.tp1.afficheur.impl.Afficheur;
import fr.brandon.aoc.tp1.capteur.api.Capteur;
import fr.brandon.aoc.tp1.capteur.api.ObserverDeCapteur;
import fr.brandon.aoc.tp1.pattern_observer.observer.api.Observer;
import fr.brandon.aoc.tp1.pattern_observer.subject.api.Subject;

public class Canal implements ObserverDeCapteur, Capteur
{
    private Afficheur afficheur;
    private Capteur capteur;

    @Override
    public void update(Subject subject)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(Capteur subject)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void attach(Observer observer)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void detach(Observer observer)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Integer getValue()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        
    }
}
