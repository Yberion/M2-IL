package fr.brandon.aoc.tp1.capteur.api;

import fr.brandon.aoc.tp1.pattern_observer.observer.api.Observer;

public interface ObserverDeCapteur extends Observer
{
    void update(Capteur subject);
}
