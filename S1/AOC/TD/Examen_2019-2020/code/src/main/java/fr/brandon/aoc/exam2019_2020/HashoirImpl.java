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
package fr.brandon.aoc.exam2019_2020;

import java.util.concurrent.BlockingQueue;
import org.tinylog.Logger;

public class HashoirImpl implements Hashoir
{
    private BlockingQueue<String> input;
    private BlockingQueue<Pair<String, String>> output;
    private boolean loop = true;

    @Override
    public void configure(BlockingQueue<String> input, BlockingQueue<Pair<String, String>> output)
    {
        this.input = input;
        this.output = output;
    }

    @Override
    public void execute() throws InterruptedException
    {

        while (loop)
        {
            String password = input.take();
            Logger.info("Hashage du mot de passe : " + password);
            output.put(new Pair<>(password, password));
        }
    }
}
