/*
 * MIT License
 *
 * Copyright (c) 2020 Brandon Largeau
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package fr.brandon.aoc.tp1.capteur.impl;

import fr.brandon.aoc.tp1.algorithm_diffusion.api.AlgorithmDiffusion;
import fr.brandon.aoc.tp1.capteur.api.Capteur;
import fr.brandon.aoc.tp1.pattern_observer.observer.api.Observer;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
