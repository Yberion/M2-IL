package fr.brandon.aoc.tp1.capteur.api;

import fr.brandon.aoc.tp1.pattern_observer.subject.api.Subject;

public interface Capteur extends Subject
{
    Integer getValue();
    void tick();
}
