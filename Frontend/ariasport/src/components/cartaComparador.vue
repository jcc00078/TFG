<template>
  <div class="card" :class="{ desactivado }" style="height: fit-content;">
    <div class="image-container">
      <img
        :src="`data:image/png;base64,${datosModelo[motoSeleccionada]?.imagenData}`"
        v-if="datosModelo[motoSeleccionada]?.imagenData"
        class="card-img-top custom-image"
        alt="..."
      />
      <img class="default-image" src="../assets/motoPorDefecto.png" v-else />
    </div>
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
            id="selectComparador"
          >
            <option value="" disabled>Selecciona una marca</option>
            <option
              :value="marca"
              v-for="marca in marcas.filter(mostrarMarca)"
              :key="marca"
            >
              {{ marca }}
            </option>
          </select>
        </li>

        <li class="list-group-item" v-if="seleccion">
          <select
            :value="motoSeleccionada"
            @input="$emit('update:motoSeleccionada', $event.target.value)"
            class="form-select"
            id="selectComparador2"
            aria-label="Default select example"
          >
            <option selected value="">Selecciona un modelo</option>
            <option
              :value="moto"
              v-for="moto in motos.filter((m) => m !== modeloNoElegible)"
              :key="moto"
            >
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
.image-container {
  position: relative;
  width: 100%;
  height: 0;
  padding-bottom: 56.25%; /* Aspect ratio 16:9 (9 / 16 * 100) */
}

.custom-image,
.default-image {
  position: absolute;
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.custom-image {
  object-fit: fill;
  max-width: 100%;
  max-height: 100%;
  border-radius: 30px;
}

.default-image {
  object-position: center;
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
    modeloNoElegible: String,
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
    const { marcas, modelos, datosModelo } = storeToRefs(store);

    if (!props.desactivado) {
      onMounted(async () => {
        const { data: arrayMarcas } = await axios.get("motos/marcas");

        store.setMarcas(arrayMarcas);

        arrayMarcas.forEach(async (marca) => {
          const { data: arrayModelos } = await axios.get(
            `motos/${marca}/modelos`
          );
          store.setModelo(marca, arrayModelos);

          arrayModelos.forEach(async (modelo) => {
            const { data: datosModelo } = await axios.get(`motos/${modelo}`);
            store.setDatosModelo(modelo, datosModelo);
          });
        });
      });
    }

    return {
      seleccion,
      marcas,
      modelos,
      datosModelo,
    };
  },
  methods: {
    mostrarMarca(marca) {
      return (
        this.modelos[marca].filter((m) => m !== this.modeloNoElegible).length >
        0
      );
    },
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