<template>
<div class="container my-5">
    <div class="alert alert-danger border-color-red" border-color="red" role="alert" v-show="form1LoginCheck !=''">
      {{this.form1LoginCheck}}
  </div>
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Historial de motocicleta</MDBCardTitle>
  <form v-if="bastidorCorrecto">
    <!-- Nº bastidor input -->
    <MDBInput
      type="email"
      label="Nº de bastidor"
      id="form1Bastidor"
      v-model="form1Bastidor"
      wrapperClass="mb-4"
    />
    <!-- Submit button -->
    <MDBBtn id="buscar" color="info" block @click="comprobarBastidor()"> Buscar </MDBBtn>
    <!-- <cabecera bastidorCorrecto></cabecera> -->
  </form>
</MDBCardBody>
</MDBCard>
  </div>
  </template>

<script>
import router from "@/router";
import {
//   MDBRow,
//   MDBCol,
  MDBInput,
//   MDBCheckbox,
  MDBBtn,
MDBCard,
MDBCardBody,
MDBCardTitle
} from "mdb-vue-ui-kit";
import { ref } from "vue";

export default {
  components: {
    // MDBRow,
    // MDBCol,
    MDBInput,
    // MDBCheckbox,
    MDBBtn,
    MDBCard,
    MDBCardBody,
    MDBCardTitle
},
  setup() {
    const form1Bastidor = ref("");
    const form1LoginCheck = ref("");
    const bastidorCorrecto = ref("false");

    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
    };
  },
  methods: {
    comprobarBastidor(){
      if(this.form1Bastidor =="a"){
         this.bastidorCorrecto=true;
          router.push("/mantenimiento/historial");
      } else
          this.form1LoginCheck = "Error, nº de bastidor no encontrado. Introduce nº de bastidor de nuevo"
    }
  },
};
</script>