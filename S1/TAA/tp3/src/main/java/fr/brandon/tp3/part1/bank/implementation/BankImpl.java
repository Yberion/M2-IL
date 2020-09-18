package fr.brandon.tp3.part1.bank.implementation;

import org.springframework.stereotype.Component;

import fr.brandon.tp3.part1.bank.api.Bank;

@Component
public class BankImpl implements Bank
{
    @Override
    public void transfert(String from, String to, int amount)
    {
        System.out.println("Transfert de fond d'un montant de " + amount + "€ du compte de " + from + " vers le compte de " + to);
    }
}
