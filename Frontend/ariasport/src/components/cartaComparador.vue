<template>
  <div class="card" :class="{ desactivado }">
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
            <option :value="marca" v-for="marca in marcas" :key="marca">
              {{ marca }}
            </option>
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
  <style scoped>
.card {
  margin-top: 40px;
  margin-right: 20px;
  width: 18rem;
  border: 0px;
}
</style>
<script>
import { ref, watchEffect, onMounted } from "vue";
import { storeToRefs } from "pinia";
import axios from "axios";
import useMotoStore from "@/store/motos";
export default {
  props: {
    titulo: String,
    motoSeleccionada: String,
    desactivado: Boolean,
  },
  emits: ["update:motoSeleccionada"],
  setup(props, { emit }) {
    const seleccion = ref("");
    watchEffect(() => {
      if (seleccion.value === "") {
        emit("update:motoSeleccionada", "");
      }
      if (props.desactivado) {
        emit("update:motoSeleccionada", "");
        seleccion.value = "";
      }
    });
    const store = useMotoStore();
    const { marcas, modelos } = storeToRefs(store);

    if (!props.desactivado) {
      onMounted(async () => {
        const { data: arrayMarcas } = await axios.get(
          "motos/marcas"
        );

        store.setMarcas(arrayMarcas);

        arrayMarcas.forEach(async (marca) => {
          const { data: arrayModelos } = await axios.get(
            `motos/${marca}/modelos`
          );
          store.setModelo(marca, arrayModelos);
        });
      });
    }

    return {
      seleccion,
      marcas,
      modelos,
    };
  },

  computed: {
    motos() {
      if (this.seleccion != "") {
        return this.modelos[this.seleccion];
      } else {
        return [];
      }
    },
  },
};
</script>