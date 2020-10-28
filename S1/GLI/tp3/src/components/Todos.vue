<template>
    <div class="todos">
        <h2>Composant Todos</h2>
        <form>
            <label for="task">Ajouter une tâche</label><br />
            <input type="text" :placeholder="placeholder" v-model="taskName" />
            <button @click.prevent="addTask()">Ajouter</button>
        </form>
        <div class="tasks">
            <h3>Task list</h3>
            <span v-for="task in tasks" :key="task.id">
                <input type="checkbox" v-model="task.checked" />
                <label for="task.name">{{ task.name | taskFilter(task) }}</label>
                <input type="text" placeholder="Nouveau nom" @blur="editName(task)" v-model.lazy="newName" /><br />
            </span>
        </div>
        <br />
        <button @click.prevent="selectAll()">Select all</button>
        <button @click.prevent="deselectAll()">deselect all</button>
        <button @click.prevent="finishSelected()">finish selected</button>
        <button @click.prevent="deleteSelected()">delete selected</button>
        <button @click.prevent="deleteFinished()">delete finished</button>
        <br /><br />
        <footer v-if="tasks.length > 0">
            <span>Il reste {{ remainingTasks }} tâche(s) à faire.</span>
        </footer>
    </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import Todo from "@/models/Todo/Todo";

@Component({
    name: "Todos",
    filters: {
        taskFilter(value: string, task: Todo) {
            if (!task) {
                return "";
            }

            if (task.done) {
                return value + "✅";
            } else {
                return value + "❌";
            }
        },
    },
})
export default class Todos extends Vue {
    private placeholder = "Nom de la tâche";
    private tasks: Array<Todo> = [];
    private taskName = "";
    private newName = "";
    private remainingTasks = 0;

    updateRemaining(): void {
        this.remainingTasks = 0;
        for (const todo of this.tasks) {
            if (!todo.done) {
                this.remainingTasks++;
            }
        }
    }

    addTask(): void {
        if (this.taskName) {
            this.tasks.push(new Todo(this.taskName, false, false));

            this.updateRemaining();
        }
    }

    editName(task: Todo): void {
        if (this.newName) {
            task.name = this.newName;
            this.newName = "";
        }
    }

    selectAll(): void {
        for (const todo of this.tasks) {
            todo.checked = true;
        }
    }

    deselectAll(): void {
        for (const todo of this.tasks) {
            todo.checked = false;
        }
    }

    finishSelected(): void {
        for (const todo of this.tasks) {
            if (todo.checked) {
                todo.done = true;

                this.updateRemaining();
            }
        }
    }

    deleteSelected(): void {
        for (const todo of this.tasks) {
            if (todo.checked) {
                this.tasks = this.tasks.filter((item) => item !== todo);
                this.updateRemaining();
            }
        }
    }

    deleteFinished(): void {
        for (const todo of this.tasks) {
            if (todo.done) {
                this.tasks = this.tasks.filter((item) => item !== todo);
                this.updateRemaining();
            }
        }
    }
}
</script>

<style scoped></style>
