package fr.brandon.aoc.tp1.canal.api;

import java.util.concurrent.Future;

public interface CapteurAsync
{
    Future<Integer> getValue();
}
