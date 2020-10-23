package fr.brandon.aoc.tp1.capteur.impl;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import fr.brandon.aoc.tp1.algorithm_diffusion.api.AlgorithmDiffusion;
import fr.brandon.aoc.tp1.capteur.api.Capteur;
import fr.brandon.aoc.tp1.pattern_observer.observer.api.Observer;

public class CapteurImpl implements Capteur
{
    private Set<Observer> observers;
    private AlgorithmDiffusion algorithmDiffusion;
    
    public CapteurImpl()
    {
        this.observers = new LinkedHashSet<>();
    }

    @Override
    public void attach(Observer observer)
    {
        Objects.requireNonNull(observer);
        
        this.observers.add(observer);
    }

    @Override
    public void detach(Observer observer)
    {
        Objects.requireNonNull(observer);
        
        this.observers.remove(observer);
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
