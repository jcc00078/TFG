// Referencia de implementación de código: https://theroadtoenterprise.com/blog/how-to-create-a-debounced-ref-in-vue-3-using-composition-api
// Visualización comparativa demo debounce y throttle: https://web.archive.org/web/20220117092326/http://demo.nimius.net/debounce_throttle/
import { ref, customRef } from "vue";
const debounce = (fn, delay = 0, immediate = false) => {
  let timeout;
  return (...args) => {
    if (immediate && !timeout) fn(...args);
    clearTimeout(timeout);

    timeout = setTimeout(() => {
      fn(...args);
    }, delay);
  };
};
const useDebouncedRef = (initialValue, delay, immediate) => {
  const state = ref(initialValue);
  const debouncedRef = customRef((track, trigger) => ({
    get() {
      track();
      return state.value;
    },
    set: debounce(
      (value) => {
        state.value = value;
        trigger();
      },
      delay,
      immediate
    ),
  }));
  return debouncedRef;
};
export { useDebouncedRef };