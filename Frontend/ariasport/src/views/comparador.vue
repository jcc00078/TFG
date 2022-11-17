<template>
  <div class="container">
    <div class="row">
      <cartaComparador
        v-for="(moto, i) in motos"
        v-bind:key="moto"
        class="col"
        v-model:moto-seleccionada="motos[i].value"
        :desactivado="i > 0 && motos[i - 1].value === ''"
        titulo="Primera moto elegida"
        style="font-family: Verdana"
      />
    </div>
    <table class="table" v-if="propiedades.length !== 0">
      <thead>
        <tr>
          <th scope="col">Marca</th>
          <th scope="col">Modelo</th>
          <th scope="col">Color</th>
          <th scope="col">Precio</th>
          <th scope="col">Tipo de moto</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in propiedades" v-bind:key="item.num_bastidor">
          <th scope="row">{{ item.marca }}</th>
          <td>{{ item.modelo }}</td>
          <td>{{ item.color }}</td>
          <td>{{ item.precio }} euros</td>
          <td>{{ item.tipo }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
  
  <script>
import cartaComparador from "@/components/cartaComparador.vue";
import { ref, watch } from "vue";
import axios from "axios";
export default {
  components: {
    cartaComparador,
  },
  setup() {
    const motos = [ref("Z650"), ref("Z650")];
    const propiedades = ref([]);
    const muestraTabla = ref(false);

    watch([...motos], (modelos) => {
      Promise.all(
        modelos.map(async (modelo) => {
          const { data: infoModelo } = await axios.get(
            `http://localhost:8084/motos/${modelo}`
          );
          return infoModelo;
        })
      )
        .then((propiedadesMotos) => {
          propiedades.value = propiedadesMotos;
        })
        .catch(() => {
          propiedades.value = [];
        });
    });

    return {
      propiedades,
      muestraTabla,
      motos,
    };
  },
  computed: {},
  methods: {
    async getInfo() {
      this.muestraTabla = true;
      const { data: infoModelo } = await axios.get(
        `http://localhost:8084/motos/${this.moto1}`
      );
      const { data: infoModelo2 } = await axios.get(
        `http://localhost:8084/motos/${this.moto2}`
      );
      this.propiedades.push(infoModelo);
      this.propiedades.push(infoModelo2);
      console.log(this.propiedades);
    },
  },
};
</script>