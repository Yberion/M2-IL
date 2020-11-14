import React, { ChangeEvent, SyntheticEvent, useState } from "react";
import { KanbanAddType } from "./Kanban";

type KanbanFormProps = {
    onAddKanban: (kanban: KanbanAddType) => void;
};

const KanbanForm = (props: KanbanFormProps) => {
    const [newKanbanName, setNewKanbanName] = useState("");

    const handleSubmit = (event: SyntheticEvent) => {
        event.preventDefault();

        const kanbanName = newKanbanName;

        props.onAddKanban({ name: kanbanName, sections: [] });

        setNewKanbanName("");
    };

    const handleChange = (event: ChangeEvent<HTMLInputElement>) => {
        setNewKanbanName(event.currentTarget.value);
    };

    return (
        <form onSubmit={handleSubmit} className="form-inline pb-3">
            <input className="form-control" value={newKanbanName} onChange={handleChange} type="text" placeholder="Ajouter un kanban" />
            <button className="btn btn-primary ml-2">Confirmer</button>
        </form>
    );
};

export default KanbanForm;
