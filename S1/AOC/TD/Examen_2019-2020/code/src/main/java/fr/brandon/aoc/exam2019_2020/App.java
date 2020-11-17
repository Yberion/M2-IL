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

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.tinylog.Logger;

public final class App
{
    private static final int OUTPUT_BUFFER_SIZE = 100000; // Must be greater or equal to the password count, or else...
    private static final int TOTAL_PASSWORD_COUNT = 50000;
    // Must be greater than password count, or else...
    private static final int INPUT_BUFFER_SIZE = 100000;
    // 10 threads for hashing the password
    private static final int PARALLELISM_LEVEL = 10;

    public static void main(String[] args)
    {
        BlockingQueue<String> input = new ArrayBlockingQueue<>(INPUT_BUFFER_SIZE);
        BlockingQueue<Pair<String, String>> output = new ArrayBlockingQueue<>(OUTPUT_BUFFER_SIZE);
        Executor scheduler = Executors.newFixedThreadPool(1); // WHY ALL THIS? ;)
        scheduler.execute(() ->
        {

            for (int i = 0; i < TOTAL_PASSWORD_COUNT; i++)
            {
                Logger.info("Welcome aboard the Titanic!");

                try
                {
                    input.put(Integer.toString(i + 1));
                }
                catch (InterruptedException e)
                {
                    Logger.error(e);
                }
            }
        });
        Launcher launcher = new Launcher(input, output, PARALLELISM_LEVEL);
        launcher.run(new HashoirImpl());
    }
}
