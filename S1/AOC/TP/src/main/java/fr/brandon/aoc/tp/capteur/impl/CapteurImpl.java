/*
 * MIT License
 *
 * Copyright (c) 2020 - 2021 Brandon Largeau, David Lafia-Monwoo
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
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionAtomique;
import fr.brandon.aoc.tp.algorithme_diffusion.impl.DiffusionSequentielle;
import fr.brandon.aoc.tp.canal.api.ObserverDeCapteurAsync;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public final class CapteurImpl implements Capteur
{
    private final Set<ObserverDeCapteurAsync> observers;
    private final AlgorithmeDiffusion algorithmeDiffusion;
    private Integer value = 0;
    private Integer lockedValue = 0;

    private CapteurImpl(AlgorithmeDiffusion algorithmeDiffusion)
    {
        this.algorithmeDiffusion = algorithmeDiffusion;
        this.observers = new LinkedHashSet<>();
    }

    /**
     * Permet de creer un CapteurImpl. Utilisation du pattern factory pour
     * securiser la creation du capteur avec un AlgorithmeDiffusion valide.
     *
     * @param algorithmeDiffusion est l'algorithme qui sera utilise pour la distribution des valeurs (non null)
     *
     * @return un CapteurImpl avec AlgorithmeDiffusion valide
     *
     * @throws NullPointerException si algorithmeDiffusion est null
     */
    public static CapteurImpl create(AlgorithmeDiffusion algorithmeDiffusion)
    {
        Objects.requireNonNull(algorithmeDiffusion);
        return new CapteurImpl(algorithmeDiffusion);
    }

    @Override
    public void attach(ObserverDeCapteurAsync observer)
    {
        Objects.requireNonNull(observer);
        this.observers.add(observer);
    }

    @Override
    public void detach(ObserverDeCapteurAsync observer)
    {
        Objects.requireNonNull(observer);
        this.observers.remove(observer);
    }

    @Override
    public Set<ObserverDeCapteurAsync> getObservers()
    {
        return Collections.unmodifiableSet(this.observers);
    }

    @Override
    public Integer getValue()
    {
        //Logger.info("[CapteurImpl] (" + Thread.currentThread().getName() + ")    getValue() Valeur : " + this.value
        //        + ", locked value : " + this.lockedValue);

        if (algorithmeDiffusion instanceof DiffusionAtomique || algorithmeDiffusion instanceof DiffusionSequentielle)
        {
            this.releaseLock();
            return this.lockedValue;
        }
        return this.value;
    }

    @Override
    public void releaseLock()
    {
        this.algorithmeDiffusion.semaphoreReleaseOnce();
    }

    @Override
    public void updateLockedValue()
    {
        this.lockedValue = this.value;
    }

    @Override
    public void tick() throws InterruptedException
    {
        //Logger.info("[CapteurImpl] (" + Thread.currentThread().getName() + ")               tick() Valeur : "
        //        + this.value + ", locked value : " + this.lockedValue);
        this.algorithmeDiffusion.execute();
        this.value++;
    }
}
