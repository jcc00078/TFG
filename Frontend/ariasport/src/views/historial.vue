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
              <tr v-for="item in revisionesUsuario" v-bind:key="item.codRevision">
                <th scope="row">{{item.codRevision}}</th>
                <td>{{item.kilometros}}</td>
                <td>{{item.fecha}}</td>
                <td>{{item.precio}} €</td>
              </tr>
            </tbody>
          </table>
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
  //   MDBRow,
  //   MDBCol,
  // MDBInput,
  //   MDBCheckbox,
  //MDBBtn,
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardTitle,
} from "mdb-vue-ui-kit";
import { ref, onMounted } from "vue";

export default {
  props: {
    motoSeleccionada: String,
  },
  components: {
    // MDBRow,
    // MDBCol,
    // MDBInput,
    // MDBCheckbox,
    // MDBBtn,
    MDBCardText,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
  },
  setup() {
    const form1Bastidor = ref("");
    const form1LoginCheck = ref("");
    const bastidorCorrecto = ref("false");
    const revisionesUsuario = ref([]);
    const store = useAuthStore();
    const route = useRoute();


    onMounted(async () => {
      const numBastidor = route.params.numBastidor;
      console.log(numBastidor);
      const { data: arrayRevisiones } = await axios.get(
        `revisiones/${numBastidor}`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      revisionesUsuario.value = arrayRevisiones;
      console.log(revisionesUsuario.value);
    });

    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
      revisionesUsuario,
      store,
    };
  },
};
</script>