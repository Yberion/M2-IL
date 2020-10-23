package fr.brandon.aoc.tp1.pattern_observer.observer.api;

import fr.brandon.aoc.tp1.pattern_observer.subject.api.Subject;

public interface Observer
{
    void update(Subject subject);
}
