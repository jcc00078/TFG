<template>
  <div class="container">
    <div class="row">
      <cartaComparador
        class="col"
        v-model:moto-seleccionada="moto1"
        titulo="Primera moto elegida" style="font-family:Verdana;"/>
      <cartaComparador
        class="col"
        :desactivado="moto1 === ''"
        v-model:moto-seleccionada="moto2"
        titulo="Segunda moto elegida" style="font-family:Verdana;"
      />
    </div>
  </div>
</template>
  
  <script>
import cartaComparador from "@/components/cartaComparador.vue";
import { ref } from "vue";
export default {
  components: {
    cartaComparador,
  },
  setup() {
    const moto1 = ref("");
    const moto2 = ref("");
    return {
      moto1,
      moto2,
    };
  },
};
</script>