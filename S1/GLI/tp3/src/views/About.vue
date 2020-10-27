<template>
  <div class="about">
    <DisplayTitle msg="About" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import DisplayTitle from "@/components/DisplayTitle.vue"; // @ is an alias to /src

@Component({
  components: {
    DisplayTitle
  }
})
export default class About extends Vue {}
</script>
