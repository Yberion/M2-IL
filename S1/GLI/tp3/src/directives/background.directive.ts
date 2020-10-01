import Vue from 'vue';

const defaultBackgroundColor = 'lightBlue'

export const Background = {
  bind(el: any, binding: any, vnode: any) {
    // el might not be present for server-side rendering.
    if (el ) {
      // Set the element's background color.
      el.style.backgroundColor = binding.value || defaultBackgroundColor
      el.innerHTML =  'My background color is :  ' + (binding.value || defaultBackgroundColor)
    }
  }, 
};
Vue.directive('background', Background);