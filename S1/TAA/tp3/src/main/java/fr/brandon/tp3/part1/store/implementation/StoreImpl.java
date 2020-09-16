package fr.brandon.tp3.part1.store.implementation;

import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1.bank.api.Bank;
import fr.brandon.tp3.part1.provider.api.Provider;
import fr.brandon.tp3.part1.store.api.FastLane;
import fr.brandon.tp3.part1.store.api.JustHaveALook;
import fr.brandon.tp3.part1.store.api.Lane;
import fr.brandon.tp3.part1.store.api.Store;

@Component
public class StoreImpl implements FastLane, JustHaveALook, Lane, Store 
{
    private Bank bank;
    private Provider provider;
    
    public StoreImpl(Bank bank, Provider provider)
    {
        this.bank = bank;
        this.provider = provider;
    }
}
