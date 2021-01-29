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
package fr.brandon.aoc.tp.canal.impl;

import fr.brandon.aoc.tp.canal.api.CapteurAsync;
import fr.brandon.aoc.tp.canal.api.ObserverDeCapteurAsync;
import fr.brandon.aoc.tp.capteur.api.Capteur;
import fr.brandon.aoc.tp.observer_de_capteur.api.ObserverDeCapteur;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * CanalImpl est une class immutable.
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public final class CanalImpl implements ObserverDeCapteurAsync, CapteurAsync
{
    private static final long MAX_DELAY = 2000;
    private final ScheduledExecutorService scheduledExecutorService;
    private final ObserverDeCapteur afficheur;
    private Capteur capteur;

    private CanalImpl(ScheduledExecutorService scheduledExecutorService, ObserverDeCapteur afficheur)
    {
        this.scheduledExecutorService = scheduledExecutorService;
        this.afficheur = afficheur;
    }

    /**
     * Permet de creer un CanalImpl. Utilisation du pattern factory pour
     * securiser la creation du canal avec un ScheduledExecutorService et ObserverDeCapteur valide.
     *
     * @param scheduledExecutorService qui va contenir les thread cree par le canal (non null)
     * @param afficheur                qui permet d'appeler sa methode Update() (non null)
     *
     * @return un CanalImpl avec ScheduledExecutorService et ObserverDeCapteur valide
     *
     * @throws NullPointerException si scheduledExecutorService ou afficheur est null
     */
    public static CanalImpl create(ScheduledExecutorService scheduledExecutorService, ObserverDeCapteur afficheur)
    {
        Objects.requireNonNull(scheduledExecutorService);
        Objects.requireNonNull(afficheur);
        return new CanalImpl(scheduledExecutorService, afficheur);
    }

    @Override
    public void setCapteur(Capteur capteur)
    {
        this.capteur = capteur;
    }

    @Override
    public Future<Integer> getValue()
    {
        Objects.requireNonNull(capteur);
        return scheduledExecutorService.schedule(() ->
        {
            //Logger.info("[CanalImpl] (" + Thread.currentThread().getName() + ")      getValue() après " + delay + "ms");
            return capteur.getValue();
        }, this.generateRandomDelay(), TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Future<Void> update()
    {
        Objects.requireNonNull(afficheur);
        return (Future<Void>) scheduledExecutorService.schedule(() ->
        {
            //Logger.info("[CanalImpl] (" + Thread.currentThread().getName() + ")      update() après " + delay + "ms");
            afficheur.update(this);
        }, this.generateRandomDelay(), TimeUnit.MILLISECONDS);
    }

    /**
     * Permet de generer aleatoirement un long entre 0 et MAX_DELAY
     *
     * @return un long genere aleatoirement
     */
    private long generateRandomDelay()
    {
        return new Random().longs(0, (MAX_DELAY + 1)).limit(1).findFirst().getAsLong();
    }
}
