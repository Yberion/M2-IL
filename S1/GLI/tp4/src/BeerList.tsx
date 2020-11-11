import React, { useState } from "react";
import Beer, { BeerType } from "./Beer";
import BeerForm from "./BeerForm";

const BeerList = () => {
    const [beers, setBeers] = useState([
        { id: 1, nom: "Coreff" },
        { id: 2, nom: "Corona" },
        { id: 3, nom: "Mort Subite" },
    ]);

    const handleAddBeer = (newBeer: BeerType) => {
        const newBeerList = [...beers];
        newBeerList.push(newBeer);

        setBeers(newBeerList);
    };

    const handleDeleteBeer = (id: number) => {
        const newBeerList = [...beers];
        const index = newBeerList.findIndex(beer => beer.id === id);

        newBeerList.splice(index, 1);

        setBeers(newBeerList);
    };

    return (
        <div>
            <p>Ma liste de bières</p>
            <ul>
                {beers.map((biere) => (
                    <Beer key={biere.id} details={biere} onDeleteBeer={handleDeleteBeer} />
                ))}
            </ul>
            <BeerForm onAddBeer={handleAddBeer} />
        </div>
    );
};

export default BeerList;
