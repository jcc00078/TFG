<template>
  <div class="container">
    <div class="row justify-content-center">
      <cartaComparador
        class="col-md"
        v-for="(moto, i) in motos"
        v-bind:key="moto"
        v-model:moto-seleccionada="motos[i].value"
        :desactivado="i > 0 && motos[i - 1].value === ''"
        :modelo-no-elegible="motos[i === 0 ? 1 : 0].value"
        titulo="Moto elegida"
        style="font-family: Verdana"
      />
    </div>
    <table class="table" v-if="propiedades.length !== 0">
      <thead>
        <tr style="font-family: Verdana">
          <th scope="col">Marca</th>
          <th scope="col">Modelo</th>
          <th scope="col">Color</th>
          <th scope="col">Precio</th>
          <th scope="col">Tipo de moto</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in propiedades" v-bind:key="item.modelo">
          <th scope="row">{{ item.marca }}</th>
          <td>{{ item.modelo }}</td>
          <td>{{ item.color }}</td>
          <td>{{ item.precio }} €</td>
          <td>{{ item.tipo }}</td>
        </tr>
      </tbody>
    </table>
    <div class="row justify-content-between" v-if="propiedades.length !== 0">
      <a
        style="font-family: Verdana"
        class="col-4 text-center link-primary"
        id="competidores"
        v-for="moto in propiedades"
        v-bind:key="moto.modelo"
        @click="calcularCompetidores(moto)"
        >Mostrar modelos competidores de {{ moto.modelo }}
      </a>

      <div class="row justify-content-center">
        <div class="mt-4 card" style="width: 18rem">
          <div class="text-center" v-if="competidores.length !== 0">
            <p style="font-family: Verdana">
              Los competidores de la moto seleccionada son
            </p>
          </div>
          <ul class="list-group list-group-light">
            <li
              class="list-group-item col align-self-center"
              v-for="comp in competidores"
              v-bind:key="comp"
            >
              {{ comp }}
            </li>
          </ul>
        </div>
      </div>
    </div>
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
    const motos = [ref(""), ref("")];
    const propiedades = ref([]);
    const muestraTabla = ref(false);
    const competidores = ref([]);
    const todosModelos = ref([]);

    watch([...motos], (modelos) => {
      Promise.all(
        modelos.map(async (modelo) => {
          const { data: infoModelo } = await axios.get(`motos/${modelo}`);
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
      competidores,
      todosModelos,
    };
  },
  methods: {
    calcularCompetidores(moto) {
      this.competidores = [];
      axios.get("motos/modelos").then((resp) => {
        this.todosModelos = resp.data;
        for (let i = 0; i < this.todosModelos.length; i++) {
          //Filtro por mismo tipo de moto
          if (
            moto.tipo == this.todosModelos[i].tipo &&
            moto.modelo != this.todosModelos[i].modelo
          ) {
            this.competidores.push(this.todosModelos[i].modelo);
          }
        }
      });
    },
  },
};
</script>