<template>
  <div class="container my-5">
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Historial de revisiones</MDBCardTitle>
        <MDBCardText>
          <table class="table">
            <thead>
              <tr>
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
                <td>{{ item.fecha }}</td>
                <td>{{ item.precio }} €</td>
              </tr>
            </tbody>
          </table>
          <div class="row">
            <div class="d-flex justify-content-center">
              <MDBBtn color="primary" @click="mostrarProxRev()"
                >Mostrar próxima revisión
              </MDBBtn>
            </div>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
    <MDBCard
      v-if="ultimoKilometraje"
      class="shadow-4-strong mx-auto mt-5"
      style="width: 250px; background-color: lightslategray"
      text="start"
    >
      <MDBCardBody style="color: aliceblue">
        <MDBCardTitle class="text-center">Próxima revisión</MDBCardTitle>
        <MDBCardText style="color: aliceblue">
          <strong>Fecha</strong>
          <MDBInput
            class="text-center"
            type="date"
            v-model="proximaFecha"
            readonly
          />
          <strong>Kilómetros</strong>
          <MDBTooltip v-model="tooltip1" direction="right" tag="a">
            <template #reference>
              <router-link style="color: black; margin-left: 5px" to="/faq">
                <i class="far fa-question-circle"> </i>
              </router-link>
            </template>
            <template #tip> Ver tabla de mantenimientos </template>
          </MDBTooltip>
          <MDBInput
            class="text-center"
            type="number"
            v-model="ultimoKilometraje"
            readonly
          >
          </MDBInput>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>
  
  <script>
//import router from "@/router";
import { useAuthStore } from "@/store/autenticar";
import { useRoute } from "vue-router";
import axios from "axios";
import {
  //MDBRow,
  //   MDBCol,
  MDBInput,
  //   MDBCheckbox,
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
    //MDBRow,
    // MDBCol,
    MDBInput,
    // MDBCheckbox,
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
    const kilometros = ref(10000);
    const ultimoKilometraje = ref(null);
    const tooltip1 = ref(false);
    const proximaFecha = ref(null);

    onMounted(async () => {
      const numBastidor = route.params.numBastidor;
      const { data: arrayRevisiones } = await axios.get(
        `revisiones/${numBastidor}`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      revisionesUsuario.value = arrayRevisiones;
    });

    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
      revisionesUsuario,
      store,
      kilometros,
      ultimoKilometraje,
      tooltip1,
      proximaFecha,
    };
  },
  methods: {
    mostrarProxRev() {
      const fecha = new Date(
        this.revisionesUsuario[this.revisionesUsuario.length - 1].fecha
      );
      fecha.setFullYear(fecha.getFullYear() + 1);
      const dia = fecha.getDate().toString().padStart(2, "0");
      const mes = (fecha.getMonth() + 1).toString().padStart(2, "0");
      const anio = fecha.getFullYear();
      this.proximaFecha = `${anio}-${mes}-${dia}`;
      console.log(this.proximaFecha);

      this.ultimoKilometraje =
        this.revisionesUsuario[this.revisionesUsuario.length - 1].kilometros;
        
        

    },
  },
};
</script>