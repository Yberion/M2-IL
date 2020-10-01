<template>
    <div class="home">
        <p v-if="visible">Mon message est : {{ message }}</p>
        <button v-on:click="toggle()">Bouton</button>
        <div v-background>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua
        </div>
        <div v-background="color">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
            eiusmod tempor incididunt ut labore et dolore magna aliqua
        </div>
        <todos />
    </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { Background } from "@/directives/background.directive";
import Todos from "../components/Todos.vue";

@Component({
    components: { Todos },
    directives: {
        Background
    }
})
export default class Home extends Vue {
    visible = false;
    message = "Coucou";
    color = "green";

    toggle(): void {
        this.visible = !this.visible;
    }
}
</script>
