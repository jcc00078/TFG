<template>
  <div class="container my-5">
    <div
      id="errorMensaje"
      class="alert alert-danger border-color-red"
      border-color="red"
      role="alert"
      v-show="comprobarLogin != ''"
    >
      {{ this.comprobarLogin }}
    </div>
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Inicia sesión</MDBCardTitle>
        <form id="login-form" @submit.prevent="login()">
          <!-- Dni input -->
          <MDBInput
            type="text"
            label="DNI"
            id="dni"
            v-model="dni"
            wrapperClass="mb-4"
          />
          <!-- Password input -->
          <MDBInput
            type="password"
            label="Contraseña"
            id="password"
            v-model="password"
            wrapperClass="mb-4"
          />
          <div class="row">
            <div class="col-12">
            <!-- Submit button -->
            <MDBBtn
              class="bg-gradient"
              color="success"
              id="estoyLogueado"
              type="submit"
              style="color: white"
            >
              Conectarse
            </MDBBtn>
          </div>
          </div>
          <div class="col-12">
          <router-link to="/registrarme">
            <MDBBtn color="link" style="background-color: transparent"
              >¿No tienes cuenta? Regístrate aquí
            </MDBBtn>
          </router-link>
          </div>
        </form>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>

<script>
import router from "@/router";
import {
  MDBInput,
  MDBBtn,
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
} from "mdb-vue-ui-kit";
import { ref } from "vue";
import { useAuthStore } from "@/store/autenticar";
import axios from "axios";
export default {
  components: {
    MDBInput,
    MDBBtn,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
  },
  setup() {
    const dni = ref("");
    const password = ref("");
    const comprobarLogin = ref("");
    const loginCorrecto = ref("false");
    const store = useAuthStore();

    return {
      dni,
      password,
      comprobarLogin,
      loginCorrecto,
      store,
    };
  },
  methods: {
    login() {
      let json = {
        dni: this.dni,
        password: this.password,
      };
      axios
        .post("login", json)
        .then(async ({ data }) => {
          this.loginCorrecto = true;
          router.push("/");
          const usuario = await axios.get(`usuarios/${this.dni}`, {
            headers: {
              Authorization: `Bearer ${data.access_token}`,
            },
          });
          this.store.login(this.dni, data.access_token, usuario.data.admin);
        })
        .catch(() => {
          this.comprobarLogin = "Error, introduce las credenciales de nuevo";
        });
    },
  },
};
</script>