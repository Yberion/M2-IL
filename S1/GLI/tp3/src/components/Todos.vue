<template lang="html">

  <section class="todos">
    <h1>todos Component</h1>
    <input type="text">
  </section>

</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import Todo from "@/Todo";

@Component
export default class Todos extends Vue {
    placeHolder = '';
    tasks: Array<Todo> = []

}
</script>

<style scoped>
</style>
