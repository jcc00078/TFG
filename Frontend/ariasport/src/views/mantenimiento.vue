<template>
  <div class="container my-5">
    <!-- <div
      class="alert alert-danger border-color-red"
      border-color="red"
      role="alert"
      v-show="form1LoginCheck != ''"
    >
      {{ this.form1LoginCheck }}
    </div> -->
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Tus motocicletas</MDBCardTitle>
        <MDBCardText>
          <div class="row-6 bg-image hover-zoom hover-overlay" v-for="moto in motosUsuario" v-bind:key="moto">
            <img class="col-6" :src="`data:image/png;base64,${moto.imagenData}`"/>
            <router-link to="/mantenimiento/historial">
              <div class="mask" style="background-color: rgba(251, 251, 251, 0.2)"></div>
            </router-link>
          </div>
        </MDBCardText>
        <!-- <form v-if="bastidorCorrecto"> -->
        <!-- Nº bastidor input -->
        <!-- <MDBInput
      type="email"
      label="Nº de bastidor"
      id="form1Bastidor"
      v-model="form1Bastidor"
      wrapperClass="mb-4"
    /> -->
        <!-- Submit button -->
        <!-- <MDBBtn id="buscar" color="info" block @click="comprobarBastidor()"> Buscar </MDBBtn> -->
        <!-- <cabecera bastidorCorrecto></cabecera> -->
        <!-- </form> -->
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
      console.log(motosUsuario.value);
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