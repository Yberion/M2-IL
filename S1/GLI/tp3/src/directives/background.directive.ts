import Vue from "vue";

// Initialize the custom-background directive.
export const customBackground = {
  bind(el: any, binding: any, vnode: any) {
    // el might not be present for server-side rendering.
    if (el) {
      const defaultBackgroundColor = "lightblue";

      // Set the element's background color.
      el.style.backgroundColor = binding.value || defaultBackgroundColor;

      if (binding.value) {
        el.innerHTML =
          "My background color is : " +
          (binding.value || defaultBackgroundColor);
      }
    }
  }
};

// You can also make it available globally.
Vue.directive("custom-background", customBackground);
