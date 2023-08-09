<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-8 align-self-start">
        <foto-moto
          class="mt-5 justify-content-end"
          :imagenData="fotoMoto"
        />
      </div>
      <div class="col col-md-4 align-self-center d-flex justify-content-end">
        <MDBCard class="m-5 w-100 bg-light">
          <MDBCardBody>
            <MDBCardTitle style="font-family: Verdana" class="text-center"
              >Presupuesto</MDBCardTitle
            >
            <ul class="list-group">
              <li
                style="font-family: Verdana"
                class="d-flex list-group-item justify-content-between"
                v-for="(item, index) in presupuesto"
                v-bind:key="item.nombre"
              >
                <div class="align-middle">
                  {{ item.nombre }}
                </div>
                <span class="align-middle"
                  >{{ item.precio }} €
                  <MDBBtn
                    @click="eliminarAccesorio(item)"
                    v-if="index > 0"
                    class="m-2 d-print-none"
                    outline="danger"
                    style="border-width: 0px; padding: 0cm; height: auto"
                  >
                    <i class="fas fa-backspace"></i>
                  </MDBBtn>
                </span>
              </li>
            </ul>
            <div class="m-3 text-end" style="font-size: large">
              <span style="font-family: Verdana"
                >Precio total: {{ totalPrecio }} €
              </span>
            </div>
            <div
              class="d-print-none col my-4 text-center d-flex justify-content-center"
            >
              <MDBBtn
                class="bg-gradient"
                color="secondary"
                @click="impPag"
                style="font-family: Verdana; color: white"
                >Imprimir presupuesto</MDBBtn
              >
            </div>
          </MDBCardBody>
        </MDBCard>
      </div>
    </div>
    <div class="d-print-none row my-2" style="background-color: transparent">
      <div class="my-2 col" style="position: relative">
        <div
          class="my-2 border bg-success bg-gradient text-white"
          style="position: relative"
        >
          <h2 class="d-flex align-items-center m-0">
            <MDBBtn
              @click="muestraAccesorio()"
              class="m-2"
              outline="dark"
              :disabled="botonDeshabilitado"
              style="border-width: 4px;height: 40px; width: 50px padding: 0cm;"
              floating
            >
              <i
                v-if="!botonDeshabilitado"
                class="fas fa-plus d-flex align-items-center"
                style="font-size: x-large; margin-left: 4px"
              >
              </i>
              <i
                v-else
                class="fas fa-ban d-flex align-items-center"
                style="font-size: x-large; margin-left: 6px"
              ></i>
            </MDBBtn>
            Añadir accesorios
          </h2>
        </div>
        <div
          v-if="sinAccesorios && muestraError"
          id="errorMensaje"
          class="alert alert-danger border-color-red"
          border-color="red"
          role="alert"
        >
          No hay accesorios disponibles para este modelo
        </div>
      </div>
      <div class="row" style="margin: 0" v-if="mostrarAccesorio">
        <lista-foto-piezas
          class="col-4"
          v-for="accesorio in accesorios.sort((a, b) => a.cod - b.cod)"
          v-bind:key="accesorio.cod"
          :imagenData="accesorio.imagenData"
          @click="elegirAccesorio(accesorio)"
        />
      </div>
    </div>
  </div>
</template>
<style>
.align-middle {
  display: flex;
  align-items: center;
}
</style>
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
import fotoMoto from "@/components/fotoMoto.vue";
import listaFotoPiezas from "@/components/listaFotoPiezas.vue";

export default {
  components: {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBBtn,
    fotoMoto,
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
    const mostrarAccesorio = ref(false);
    const sinAccesorios = ref(false);
    const muestraError = ref(false);
    const botonDeshabilitado = ref(false);
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
      if (datosGAccesoriosMoto.length === 0) {
        sinAccesorios.value = true;
      } else {
        accesorios.value = datosAccesorioMoto;
        grupoAccesorios.value = datosGAccesoriosMoto;
      }
      presupuesto.value.push({
        nombre: datosMoto.modelo,
        precio: datosMoto.precio,
      });
    });

    function muestraAccesorio() {
      if (sinAccesorios.value) {
        //alert("Este modelo de moto no tiene accesorios disponibles.");
        muestraError.value = true;
        botonDeshabilitado.value = true;
      } else {
        mostrarAccesorio.value = !mostrarAccesorio.value;
      }
    }

    return {
      route,
      moto,
      accesorios,
      grupoAccesorios,
      presupuesto,
      mostrarAccesorio,
      muestraAccesorio,
      sinAccesorios,
      muestraError,
      botonDeshabilitado,
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

    mismosAccesorios(arr1, arr2) {
      return arr1.every((element) => arr2.includes(element));
    },

    eliminarAccesorio(accesorio) {
      for (let i = 0; i < this.presupuesto.length; i++) {
        if (this.presupuesto[i].nombre === accesorio.nombre) {
          this.presupuesto.splice(i, 1);
        }
      }
    },
    impPag() {
      window.print();
    },
  },
  computed: {
    totalPrecio() {
      return this.presupuesto.reduce(
        (precioAcc, accesorio) => precioAcc + accesorio.precio,
        0
      );
    },
    fotoMoto() {
      const seleccionados = this.presupuesto
        .filter((item) => item.cod)
        .map((item) => item.cod);
      if (seleccionados.length == 0) {
        return this.moto.imagenData;
      }
      const grupos = this.grupoAccesorios.filter(
        (grupo) => grupo.codAccesorios.length === seleccionados.length
      );

      return grupos.find((grupo) =>
        this.mismosAccesorios(seleccionados, grupo.codAccesorios)
      )?.imagenData;
    },
  },
};
</script>