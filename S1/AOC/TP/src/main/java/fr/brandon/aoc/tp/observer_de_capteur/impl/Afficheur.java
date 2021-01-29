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
package fr.brandon.aoc.tp.observer_de_capteur.impl;

import fr.brandon.aoc.tp.canal.api.CapteurAsync;
import fr.brandon.aoc.tp.observer_de_capteur.api.ObserverDeCapteur;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.tinylog.Logger;

public final class Afficheur implements ObserverDeCapteur
{
    private final List<Integer> data;
    //private static int nombreCapteur = 0;
    //private int numeroCapteur;
    private int maxValue;
    private final Object object = new Object();

    public Afficheur()
    {
        this.data = new ArrayList<>();
        //nombreCapteur++;
        //this.numeroCapteur = nombreCapteur;
        this.maxValue = 0;
    }

    @Override
    public List<Integer> getData()
    {
        return Collections.unmodifiableList(this.data);
    }

    @Override
    public void update(CapteurAsync canal)
    {
        //Logger.info("[Afficheur " + this.numeroCapteur + "] (" + Thread.currentThread().getName() + ")    update()");
        Future<Integer> futureValue = canal.getValue();
        Integer value;

        try
        {
            value = futureValue.get();

            synchronized (this.object)
            {

                if (value > maxValue)
                {
                    this.data.add(value);
                    maxValue = value;
                }
            }
            //Logger.info("[Afficheur " + this.numeroCapteur + "] (" + Thread.currentThread().getName()
            //        + ")    update() value : " + value);
        }
        catch (InterruptedException | ExecutionException e1)
        {
            Logger.trace(e1);
            Thread.currentThread().interrupt();
        }
    }
}
