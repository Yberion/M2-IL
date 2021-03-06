package fr.brandon.tp3.part1_2.client.implementation;

import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1_2.client.api.Client;
import fr.brandon.tp3.part1_2.store.api.FastLane;
import fr.brandon.tp3.part1_2.store.api.JustHaveALook;
import fr.brandon.tp3.part1_2.store.api.Lane;

@Component
public class ClientImpl implements Client
{
    private FastLane fastLane;
    private Lane lane;
    private JustHaveALook justHaveALook;

    public ClientImpl(FastLane fastLane, Lane lane, JustHaveALook justHaveALook)
    {
        this.fastLane = fastLane;
        this.lane = lane;
        this.justHaveALook = justHaveALook;
    }

    @Override
    public void run()
    {
        System.out.println("Client running");
        this.fastLane.oneShotOrder("tomate", 2);
        this.justHaveALook.isAvailable("tomate", 3);
        this.lane.pay("Brandon");
    }
}
