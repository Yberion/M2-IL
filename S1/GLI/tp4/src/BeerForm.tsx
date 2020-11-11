import React, { ChangeEvent, SyntheticEvent, useState } from "react";
import { BeerType } from "./Beer";

type BeerFormProps = {
    onAddBeer: (beer: BeerType) => void;
};

const BeerForm = (props: BeerFormProps) => {
    const [newBeerName, setNewBeerName] = useState("");

    const handleSubmit = (event: SyntheticEvent) => {
        event.preventDefault();

        const id = new Date().getTime();
        const beerName = newBeerName;

        props.onAddBeer({ id, nom: beerName });

        setNewBeerName("");
    };

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setNewBeerName(event.currentTarget.value);
    };

    return (
        <form onSubmit={handleSubmit}>
            <input value={newBeerName} onChange={handleChange} type="text" placeholder="Ajouter une bière" />
            <button>Confirmer</button>
        </form>
    );
};

export default BeerForm;
