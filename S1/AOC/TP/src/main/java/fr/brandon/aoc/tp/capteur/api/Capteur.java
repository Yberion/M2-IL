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
package fr.brandon.aoc.tp.capteur.api;

import fr.brandon.aoc.tp.canal.api.ObserverDeCapteurAsync;
import java.util.Set;

/**
 * <b>Represente un capteur</b>
 *
 * @author LARGEAU Brandon, LAFIA-MONWOO David
 * @version 1
 */
public interface Capteur
{
    /**
     * Permet de lier un observer de capteur asynchrone (canal) au capteur, en ajoutant l'observer au Set d'observers.
     *
     * @param observer (non null)
     *
     * @throws NullPointerException si l'observer est null
     */
    void attach(ObserverDeCapteurAsync observer);

    /**
     * Permet de retirer un observer de capteur asynchrone (canal) en le retirant du Set d'observers.
     *
     * @param observer (non null)
     *
     * @throws NullPointerException si l'observer est null
     */
    void detach(ObserverDeCapteurAsync observer);

    /**
     * Permet d'obtenir la collection de tous les observers du capteur.
     *
     * @return Set<ObserverDeCapteurAsync> un Set contenant tout les observers du capteur
     */
    Set<ObserverDeCapteurAsync> getObservers();

    /**
     * Permet d'obtenir la valeur du compteur du capteur.
     *
     * @return Integer un Integer contenant la valeur du capteur
     */
    Integer getValue();

    /**
     * Permet de mettre a jour la valeur du compteur du capteur qui sera verouillee.
     */
    void updateLockedValue();

    /**
     * Permet de notifier l'algo de diffusion lorsque que l'execution d'un geValue est termine.
     */
    void releaseLock();

    /**
     * Permet de simuler une tick dans le capteur.
     *
     * @throws InterruptedException
     */
    void tick() throws InterruptedException;
}
