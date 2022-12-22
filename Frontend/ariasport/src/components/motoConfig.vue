<template>
  <div class="container vh-100">
    <div class="row">
      <div class="col align-self-center">
        <lista-foto-moto class="mt-5" :imagenData="fotoMoto" />
      </div>
      <div class="col-4 m-4 d-flex align-items-end justify-content-end">
        <MDBCard class="m-5" style="width: 18rem">
          <MDBCardBody>
            <MDBCardTitle class="text-center">Presupuesto</MDBCardTitle>
            <ul class="list-group">
              <li
                class="list-group-item"
                v-for="item in presupuesto"
                v-bind:key="item.nombre"
              >
                {{ item.nombre }} {{ item.precio }} €
              </li>
            </ul>
            <span>Precio total: {{ totalPrecio }} € </span>
            <div class="col my-4 text-center">
              <MDBBtn tag="a" href="#!" color="primary"
                >Imprimir presupuesto</MDBBtn
              >
            </div>
          </MDBCardBody>
        </MDBCard>
      </div>
    </div>
    <div class="row my-3" style="background-color: transparent">
      <div class="my-5 col" style="position: relative">
        <div class="my-5 border bg-light" style="position: relative">
          <h2
            class="d-flex align-items-center m-0"
            style="
              font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman',
                serif;
            "
          >
            <MDBBtn
              class="m-2"
              outline="success"
              style="border-width: 4px;height: 40px; width: 50px padding: 0cm;"
              floating
            >
              <i
                class="fas fa-plus d-flex align-items-center"
                style="font-size: x-large; margin-left: 4px; marg"
              >
              </i>
            </MDBBtn>
            Añadir accesorios
          </h2>
        </div>
      </div>
      <div class="row">
        <lista-foto-piezas
          class="col-4 mt-4"
          v-for="accesorio in accesorios"
          v-bind:key="accesorio.cod"
          :imagenData="accesorio.imagenData"
          @click="elegirAccesorio(accesorio)"
        />
      </div>
    </div>
  </div>
</template>

<script>
import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBBtn,
  mdbRipple,
} from "mdb-vue-ui-kit";
import axios from "axios";
import { ref, onMounted } from "vue";
import { useRoute } from "vue-router";
import listaFotoMoto from "@/components/listaFotoMoto.vue";
import listaFotoPiezas from "@/components/listaFotoPiezas.vue";

export default {
  components: {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBBtn,
    listaFotoMoto,
    listaFotoPiezas,
  },
  directives: {
    mdbRipple,
  },
  setup() {
    const route = useRoute();
    const moto = ref({});
    const accesorios = ref([]);
    const grupoAccesorios = ref([]);
    const presupuesto = ref([]);
    onMounted(async () => {
      const modelo = route.params.modelo;
      const { data: datosMoto } = await axios.get(`motos/${modelo}`);
      moto.value = datosMoto;
      const { data: datosGAccesoriosMoto } = await axios.get(
        `motos/${modelo}/grupos`
      );
      const { data: datosAccesorioMoto } = await axios.get(
        `motos/${modelo}/accesorios`
      );
      accesorios.value = datosAccesorioMoto;
      grupoAccesorios.value = datosGAccesoriosMoto;
      presupuesto.value.push({
        nombre: datosMoto.modelo,
        precio: datosMoto.precio,
      });
    });
    return {
      route,
      moto,
      accesorios,
      grupoAccesorios,
      presupuesto,
    };
  },
  methods: {
    elegirAccesorio(accesorio) {
      const { nombre, precio, cod } = accesorio;
      if (!this.presupuesto.some((accesorio) => accesorio.nombre === nombre)) {
        this.presupuesto.push({
          nombre,
          precio,
          cod,
        });
      }
    },
  },
  computed: {
    totalPrecio(){
      return this.presupuesto.reduce((precioAcc, accesorio) => precioAcc + accesorio.precio, 0)
    } 
    ,
    fotoMoto(){
      const seleccionados=this.presupuesto.filter(item=>item.cod);
      if(seleccionados.length==0){
      return this.moto.imagenData;
      } 
      const grupos=this.grupoAccesorios.filter(grupo=>grupo.codAccesorios.length===seleccionados.length);
      return grupos.find(grupo=>grupo.codAccesorios===seleccionados)?.imagenData;
    }
  },
};
</script>