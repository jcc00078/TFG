<template>
  <div class="card" :class="{ desactivado }" style="width: 18rem; border: 0px">
    <img src="../assets/logoComparador.png" class="card-img-top" alt="..." />

    <div class="card-body">
      <h5 class="card-title">
        {{ this.titulo }} {{ ": " + motoSeleccionada }}
      </h5>
      <ul class="list-group list-group-flush">
        <li class="list-group-item">
          <select
            :disabled="desactivado"
            v-model="seleccion"
            class="form-select"
            aria-label="Default select example"
          >
            <option value="">Selecciona una marca</option>
            <option id="Kawasaki" value="kawasaki">Kawasaki</option>
            <option id="Suzuki" value="suzuki">Suzuki</option>
            <option id="Honda" value="honda">Honda</option>
            <option id="Triumph" value="triumph">Triumph</option>
          </select>
        </li>

        <li class="list-group-item" v-if="seleccion">
          <select
            :value="motoSeleccionada"
            @input="$emit('update:motoSeleccionada', $event.target.value)"
            class="form-select"
            aria-label="Default select example"
          >
            <option selected value="">Selecciona un modelo</option>
            <option :value="moto" v-for="moto in motos" :key="moto">
              {{ moto }}
            </option>
          </select>
        </li>
      </ul>
    </div>
  </div>
</template>
<style>
.desactivado {
  filter: brightness(75%);
}
</style>
  
    <script>
import { ref, watchEffect } from "vue";

export default {
  props: {
    titulo: String,
    motoSeleccionada: String,
    desactivado: Boolean,
  },
  emits: ["update:motoSeleccionada"],
  setup(props, { emit }) {
    const seleccion = ref("");
    const modelo = {
      kawasaki: ["Z650", "Ninja 1000", "Vulcan S", "H2"],
      suzuki: ["GSX-S1000", "Hayabusa", "GSX-S125", "Scooter"],
      honda: ["CBR-650R", "Africa twin", "ADV", "CB1000R"],
      triumph: ["Street triple 765", "Speed Triple", "Trident", "Tiger"],
    };
    watchEffect(() => {
      if (seleccion.value === "") {
        emit("update:motoSeleccionada", "");
      }
    });

    return {
      seleccion,
      modelo,
    };
  },
  computed: {
    motos() {
      if (this.seleccion != "") {
        return this.modelo[this.seleccion];
      } else {
        return [];
      }
    },
  },
};
</script>