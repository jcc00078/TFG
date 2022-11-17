<template>
  <div class="container">
    <div class="row">
      <cartaComparador
        class="col"
        v-model:moto-seleccionada="moto1"
        titulo="Primera moto elegida"
        style="font-family: Verdana"
      />
      <cartaComparador
        class="col"
        :desactivado="moto1 === ''"
        v-model:moto-seleccionada="moto2"
        titulo="Segunda moto elegida"
        style="font-family: Verdana"
        @change="getInfo()"
      />
      <!-- <div class="container my-5">
        <div class="row justify-content-center">
          <div class="col-auto">
            <button
              id="comp"
              type="button"
              class="btn btn-success"
              :disabled="moto2 === ''"
              @click="getInfo()"
            >
              Comparar
            </button>
          </div>
        </div>
      </div> -->
    </div>
    <table class="table" v-if="moto2!==''">
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
          <th scope="row">{{item.marca}}</th>
          <td>{{item.modelo}}</td>
          <td>{{item.color}}</td>
          <td>{{item.precio}} euros</td>
          <td>{{item.tipo}}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
  
  <script>
import cartaComparador from "@/components/cartaComparador.vue";
import { ref } from "vue";
import axios from "axios";
export default {
  components: {
    cartaComparador,
  },
  setup() {
    const moto1 = ref("");
    const moto2 = ref("");
    const propiedades=ref([]);
    const muestraTabla = ref(false);

    return {
      moto1,
      moto2,
      propiedades,
      muestraTabla
    };
  },
  methods: {
    async getInfo() {
      this.muestraTabla=true;
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