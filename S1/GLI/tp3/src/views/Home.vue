<template>
  <div class="home">
    <div v-if="visible">
      <DisplayTitle msg="Home" />
    </div>
    <button @click="hide()">
      <span v-if="visible">Hide</span>
      <span v-else>Display</span></button
    ><br />
    <p v-custom-background>No value</p>
    <p v-custom-background="color"></p>
    <Todos />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { customBackground } from "@/directives/background.directive";
import DisplayTitle from "@/components/DisplayTitle.vue"; // @ is an alias to /src
import Todos from "@/components/Todos.vue";

@Component({
  components: {
    DisplayTitle,
    Todos
  },
  directives: {
    customBackground
  }
})
export default class Home extends Vue {
  private visible = true;

  private color = "green";

  hide(): void {
    this.visible = !this.visible;
  }
}
</script>
