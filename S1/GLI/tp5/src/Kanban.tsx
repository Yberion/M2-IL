import React from "react";

export type KanbanType = {
    id: number;
    sections: SectionType[];
    name: string;
};

export type KanbanAddType = {
    sections: SectionType[];
    name: string;
};

// Devrait être dans son propre fichier, mais on implémente juste Kanban
export type SectionType = {
    id?: number;
    name: string;
    kanbanId: number;
    cartes: any[];
};

type KanbanProps = {
    kanban: KanbanType;
    onDeleteKanban: (id: number) => void;
};

const Kanban = (props: KanbanProps) => {
    const onHandleDelete = () => {
        props.onDeleteKanban(props.kanban.id);
    };

    return (
        <div className="kanban card bg-light mr-3 mb-3">
            <div className="card-header">
            <span className="badge badge-secondary">name</span> <span className="badge badge-primary">{props.kanban.name}</span>
                <button type="button" className="close text-danger" data-dismiss="modal" aria-label="Close" onClick={onHandleDelete}>
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div className="card-body">
                <span className="badge badge-secondary">id</span> <span className="badge badge-primary">{props.kanban.id}</span>
                <br />
                <span className="badge badge-secondary">sections</span>
                {props.kanban.sections.map((section) => 
                <div key={section.id} className="card mb-3">
                    <div className="card-body">
                        <span className="badge badge-secondary">id</span> <span className="badge badge-primary">{section.id}</span>
                        <br />
                        <span className="badge badge-secondary">name</span> <span className="badge badge-primary">{section.name}</span>
                        <br />
                        <span className="badge badge-secondary">kanbanId</span> <span className="badge badge-primary">{section.kanbanId}</span>
                        <br />
                        <span className="badge badge-secondary">Cartes</span>
                    </div>
                </div>
                )}
            </div>
        </div>
    );
};

export default Kanban;
