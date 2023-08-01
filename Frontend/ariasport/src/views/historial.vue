<template>
  <div class="container my-5">
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Historial de revisiones</MDBCardTitle>
        <div
          v-if="cargandoDatos"
          class="spinner-border mt-4"
          style="width: 3rem; height: 3rem"
          role="status"
        >
          <span class="sr-only">Loading...</span>
        </div>
        <MDBCardText v-else>
          <div
            id="errorMRevisiones"
            class="alert alert-danger border-color-red"
            border-color="red"
            role="alert"
            v-show="revisionesUsuario.length == 0"
          >
            No hay ninguna revisión
          </div>
          <table v-show="revisionesUsuario.length > 0" class="table">
            <thead>
              <tr style="font-family: Verdana">
                <th scope="col">Cód.Revisión</th>
                <th scope="col">Kilómetros</th>
                <th scope="col">Fecha</th>
                <th scope="col">Precio</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="item in revisionesUsuario.sort(
                  (a, b) => a.kilometros - b.kilometros
                )"
                v-bind:key="item.codRevision"
              >
                <th scope="row">{{ item.codRevision }}</th>
                <td>{{ item.kilometros }}</td>
                <td>
                  {{
                    new Intl.DateTimeFormat("es-ES", {
                      dateStyle: "full",
                      timeStyle: "short",
                    }).format(item.fecha)
                  }}
                </td>
                <td>{{ item.precio }} €</td>
              </tr>
            </tbody>
          </table>
          <div class="row">
            <div class="d-flex justify-content-center">
              <MDBBtn
                class="bg-gradient"
                id="proxR"
                color="success"
                @click="mostrarProxRev()"
                >Mostrar próxima revisión
              </MDBBtn>
            </div>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
    <MDBCard
      v-if="kilometrosMantenimiento"
      class="shadow-4-strong mx-auto mt-5 grad"
      style="width: 250px"
      text="start"
    >
      <MDBCardBody style="color: aliceblue">
        <MDBCardTitle class="text-center">Próxima revisión</MDBCardTitle>
        <MDBCardText style="color: aliceblue">
          <strong v-if="proximaFecha">Fecha</strong>
          <MDBInput
            class="text-center"
            type="date"
            v-if="proximaFecha"
            v-model="proximaFecha"
            readonly
          />

          <strong>Kilómetros</strong>
          <MDBTooltip v-model="tooltip1" direction="right" tag="a">
            <template #reference>
              <i
                id="iconoTabla"
                @click="muestraTMantenimiento()"
                class="far fa-question-circle"
                style="color: black; margin-left: 5px; cursor: pointer"
              >
              </i>
            </template>
            <template #tip> Ver tabla de mantenimientos </template>
          </MDBTooltip>
          <MDBInput
            class="text-center"
            type="number"
            v-model="kilometrosMantenimiento.kilometrajeRevision"
            readonly
          >
          </MDBInput>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>

    <MDBCard
      id="tablaMarca"
      v-show="mostrarTablaM"
      v-if="datosMarca"
      text="body"
      style="background-color: #e2d1c3"
      class="m-5 bg-gradient"
    >
      <MDBCardHeader class="text-center">
        <h4>Tabla de mantenimiento de {{ datosMarca.nombre }}</h4>
      </MDBCardHeader>
      <MDBCardBody>
        <MDBCardTitle
          >Trabajos que se realizan en las distintas revisiones</MDBCardTitle
        >
        <MDBCardText>
          <ul class="list-group">
            <li
              class="list-group-item"
              v-for="revision in datosMarca.kilometrajeRevisiones"
              :key="revision.kilometrajeRevision"
            >
              <p
                :class="[
                  revision.kilometrajeRevision ==
                  kilometrosMantenimiento.kilometrajeRevision
                    ? 'fw-bold'
                    : '',
                ]"
              >
                {{ revision.kilometrajeRevision }} Kilometros
                <i class="fas fa-long-arrow-alt-right"></i>
                {{ revision.descripcion }}
              </p>
            </li>
          </ul>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>
  <style>
.grad {
  background-image: linear-gradient(to right, #868f96 0%, #596164 100%);
}
</style>
  <script>
import { useAuthStore } from "@/store/autenticar";
import { useRoute } from "vue-router";
import axios from "axios";
import {
  MDBInput,
  MDBBtn,
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardTitle,
  MDBTooltip,
} from "mdb-vue-ui-kit";
import { ref, onMounted } from "vue";

export default {
  props: {
    motoSeleccionada: String,
  },
  components: {
    MDBInput,
    MDBBtn,
    MDBCardText,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBTooltip,
  },
  setup() {
    const form1Bastidor = ref("");
    const form1LoginCheck = ref("");
    const bastidorCorrecto = ref("false");
    const revisionesUsuario = ref([]);
    const store = useAuthStore();
    const route = useRoute();
    const kilometrosMantenimiento = ref("");
    const tooltip1 = ref(false);
    const proximaFecha = ref(null);
    const numBast = ref("");
    const datosMarca = ref([]);
    const mostrarTablaM = ref(false);
    const cargandoDatos = ref(true);

    onMounted(async () => {
      const numBastidor = route.params.numBastidor;
      numBast.value = numBastidor;
      const { data: arrayRevisiones } = await axios.get(
        `revisiones/${numBastidor}`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      cargandoDatos.value = false;
      revisionesUsuario.value = arrayRevisiones.map((r) => {
        r.fecha = new Date(r.fecha);
        return r;
      });
    });
    function muestraTMantenimiento() {
      mostrarTablaM.value = !mostrarTablaM.value;
    }
    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
      revisionesUsuario,
      store,
      route,
      kilometrosMantenimiento,
      tooltip1,
      proximaFecha,
      numBast,
      datosMarca,
      mostrarTablaM,
      muestraTMantenimiento,
      cargandoDatos,
    };
  },
  methods: {
    mostrarProxRev() {
      if (this.revisionesUsuario.length > 0) {
        const fecha = new Date(
          this.revisionesUsuario[this.revisionesUsuario.length - 1]?.fecha
        );
        fecha.setFullYear(fecha.getFullYear() + 1);
        const dia = fecha.getDate().toString().padStart(2, "0");
        const mes = (fecha.getMonth() + 1).toString().padStart(2, "0");
        const anio = fecha.getFullYear();
        this.proximaFecha = `${anio}-${mes}-${dia}`;
      }

      const ultimoKilometraje =
        this.revisionesUsuario[this.revisionesUsuario.length - 1]?.kilometros ??
        1;

      axios.get(`motos/${this.numBast}/marca`).then((resp) => {
        this.datosMarca = resp.data;
        this.kilometrosMantenimiento =
          this.datosMarca.kilometrajeRevisiones.reduce(
            (kmAcumulador, kmActual) =>
              kmActual.kilometrajeRevision > ultimoKilometraje &&
              kmActual.kilometrajeRevision < kmAcumulador.kilometrajeRevision
                ? kmActual
                : kmAcumulador,
            this.datosMarca.kilometrajeRevisiones[
              this.datosMarca.kilometrajeRevisiones.length - 1
            ] //Este parámetro del reduce se utiliza para dar el valor inicial del acumulador(kmAcumulador) antes de comenzar la iteración sobre el array. Empieza kmAcumulador en ultimo valor de la lista de km de mantenimiento
            //Math.max(...this.datosMarca)
          );
      });
    },
  },
};
</script>