package fr.brandon.aoc.tp1.pattern_observer.subject.api;

import fr.brandon.aoc.tp1.pattern_observer.observer.api.Observer;

public interface Subject
{
    void attach(Observer observer);
    void detach(Observer observer);
}
