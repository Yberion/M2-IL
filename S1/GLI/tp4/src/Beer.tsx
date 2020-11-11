import React from "react";

export type BeerType = {
    id: number;
    nom: string;
};

type BeerProps = {
    details: BeerType;
    onDeleteBeer: (id: number) => void;
};

const Beer = (props: BeerProps) => {
    const onHandleDelete = () => {
        props.onDeleteBeer(props.details.id);
    };

    return (
        <li>
            [{props.details.id}] Bière : {props.details.nom} <button onClick={onHandleDelete}>Supprimer</button>
        </li>
    );
};

export default Beer;
