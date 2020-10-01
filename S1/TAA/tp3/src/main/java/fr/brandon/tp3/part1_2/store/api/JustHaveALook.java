package fr.brandon.tp3.part1_2.store.api;

public interface JustHaveALook
{
    double getPrice(String article);
    boolean isAvailable(String article, int amount);
}
