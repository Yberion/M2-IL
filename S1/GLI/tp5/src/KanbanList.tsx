import React, { useEffect, useState } from "react";
import Kanban, { KanbanAddType, KanbanType } from "./Kanban";
import KanbanForm from "./KanbanForm";
import axios from "axios";

const KanbanList = () => {
    const [kanbans, setKanbans] = useState<KanbanType[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState("");

    useEffect(() => {
        axios
            .get<KanbanType[]>("api/v1/kanban/get/")
            .then((response) => {
                setKanbans(response.data);
                setLoading(false);
            })
            .catch((ex) => {
                const error = ex.response.status === 404 ? "Resource not found" : "An unexpected error has occurred";
                setError(error);
                setLoading(false);
            });
    }, []);

    const handleAddKanban = (newKanban: KanbanAddType) => {
        axios
            .post<KanbanType>("api/v1/kanban/add/", newKanban)
            .then((res) => {
                const newKanbanList = [...kanbans];
                newKanbanList.push({ id: res.data.id, name: newKanban.name, sections: newKanban.sections });
                setKanbans(newKanbanList);
            })
            .catch((ex) => {
                setError(ex);
            });
    };

    const handleDeleteKanban = (id: number) => {
        axios
            .delete("api/v1/kanban/delete/" + id)
            .then(() => {
                const newKanbanList = [...kanbans];
                const index = newKanbanList.findIndex((kanban) => kanban.id === id);
                newKanbanList.splice(index, 1);
                setKanbans(newKanbanList);
            })
            .catch((ex) => {
                setError(ex);
            });
    };

    return (
        <div>
            <h1>Mes kanbans</h1>
            {!error && <KanbanForm onAddKanban={handleAddKanban} />}

            {error && <p className="alert alert-danger">{error}</p>}

            <div>
                {!error && loading && (
                    <div className="spinner-border text-primary" role="status">
                        <span className="sr-only">Chargement...</span>
                    </div>
                )}
                {kanbans.map((kanban) => (
                    <Kanban key={kanban.id} kanban={kanban} onDeleteKanban={handleDeleteKanban} />
                ))}
            </div>
        </div>
    );
};

export default KanbanList;
