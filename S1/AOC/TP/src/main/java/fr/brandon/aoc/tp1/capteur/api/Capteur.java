package fr.brandon.aoc.tp1.capteur.api;

import fr.brandon.aoc.tp1.observer_de_capteur.api.ObserverDeCapteur;

public interface Capteur
{
    void attach(ObserverDeCapteur observer);
    void detach(ObserverDeCapteur observer);
    Integer getValue();
    void tick();
}
