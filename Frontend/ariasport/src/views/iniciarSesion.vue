<template>
<div class="container my-5">
      <div class="alert alert-danger border-color-red" border-color="red" role="alert" v-show="form1LoginCheck !=''">
        {{this.form1LoginCheck}}
    </div>
      <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
        <MDBCardBody>
          <MDBCardTitle>Inicia sesión</MDBCardTitle>
    <form v-if="loginCorrecto">
      <!-- Email input -->
      <MDBInput
        type="email"
        label="Email"
        id="form1Email"
        v-model="form1Email"
        wrapperClass="mb-4"
      />
      <!-- Password input -->
      <MDBInput
        type="password"
        label="Contraseña"
        id="form1Password"
        v-model="form1Password"
        wrapperClass="mb-4"
      />
      <!-- Submit button -->
      <MDBBtn id="estoyLogueado" color="primary" block @click="logueado()"> Conectarse </MDBBtn>
      <cabecera logincorrecto></cabecera>
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
    const form1Email = ref("");
    const form1Password = ref("");
    const form1LoginCheck = ref("");
    const loginCorrecto = ref("false");

    return {
      form1Email,
      form1Password,
      form1LoginCheck,
      loginCorrecto
    };
  },
  methods: {
    logueado(){
      if(this.form1Email =="a" && this.form1Password =="b"){
         this.loginCorrecto=true;
          router.push("/");
      } else
          this.form1LoginCheck = "Error, introduce las credenciales de nuevo"
    }
  },
};
</script>