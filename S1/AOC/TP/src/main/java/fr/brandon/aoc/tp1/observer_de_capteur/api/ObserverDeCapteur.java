package fr.brandon.aoc.tp1.observer_de_capteur.api;

import fr.brandon.aoc.tp1.capteur.api.Capteur;

public interface ObserverDeCapteur
{
    void update(Capteur capteur);
}
