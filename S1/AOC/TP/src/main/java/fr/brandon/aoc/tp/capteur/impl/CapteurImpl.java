/*
 * MIT License
 *
 * Copyright (c) 2020 Brandon Largeau, David Lafia-Monwoo
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
package fr.brandon.aoc.tp.capteur.impl;

import fr.brandon.aoc.tp.algorithme_diffusion.api.AlgorithmeDiffusion;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import fr.brandon.aoc.tp.observer_de_capteur.api.ObserverDeCapteur;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class CapteurImpl implements Capteur
{
    private Set<ObserverDeCapteur> observers;
    private Integer value;
    private AlgorithmeDiffusion algorithmeDiffusion;

    public CapteurImpl(AlgorithmeDiffusion algorithmeDiffusion)
    {
        this.observers = new LinkedHashSet<>();
        this.algorithmeDiffusion = algorithmeDiffusion;
    }

    @Override
    public void attach(ObserverDeCapteur observer)
    {
        Objects.requireNonNull(observer);
        this.observers.add(observer);
    }

    @Override
    public void detach(ObserverDeCapteur observer)
    {
        Objects.requireNonNull(observer);
        this.observers.remove(observer);
    }

    @Override
    public Integer getValue()
    {
        return this.value;
    }

    // Synchronize ?
    @Override
    public void tick()
    {
        this.value++;
        this.algorithmeDiffusion.execute();
        /*
         * for (ObserverDeCapteur observer : this.observers)
         * {
         * observer.update(this);
         * }
         */
    }
}