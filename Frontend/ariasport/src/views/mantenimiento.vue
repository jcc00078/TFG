<template>
  <div class="container my-5">

    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Tus motocicletas</MDBCardTitle>
        <MDBCardText>
          <div class="row-6 bg-image hover-zoom hover-overlay" v-for="moto in motosUsuario" v-bind:key="moto">
            <img class="col-6" :src="`data:image/png;base64,${moto.imagenData}`"/>
            <router-link :to="`/mantenimiento/historial/${moto.numBastidor}`">
              <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
            </router-link>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>

<script>
//import router from "@/router";
import { useAuthStore } from "@/store/autenticar";
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
    const motosUsuario = ref([]);
    const store = useAuthStore();
  

    onMounted(async () => {
      const { data: arrayMotos } = await axios.get(
        `usuarios/${store.username}/motos`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      motosUsuario.value = arrayMotos;
    });

    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
      motosUsuario,
      store,
    };
  },
  methods: {
    // comprobarBastidor() {
    //   if (this.form1Bastidor == "a") {
    //     this.bastidorCorrecto = true;
    //     router.push("/mantenimiento/historial");
    //   } else
    //     this.form1LoginCheck =
    //       "Error, nº de bastidor no encontrado. Introduce nº de bastidor de nuevo";
    // },
  },
};
</script>