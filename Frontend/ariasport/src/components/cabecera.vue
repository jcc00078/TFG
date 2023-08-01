<template>
  <MDBNavbar
    class="d-print-none justify-content-end"
    expand="lg"
    light
    bg="light"
    container
  >
    <MDBNavbarBrand router-link to="/">
      <router-link to="/">
        <img src="@/assets/logo.png" style="width: 2rem" />
      </router-link>
    </MDBNavbarBrand>
    <MDBNavbarToggler
      @click="collapse1 = !collapse1"
      target="#navbarSupportedContent"
    ></MDBNavbarToggler>
    <MDBCollapse v-model="collapse1" id="navbarSupportedContent">
      <MDBNavbarNav>
        <MDBNavbarItem to="/"> Inicio </MDBNavbarItem>
        <MDBNavbarItem to="/configurador"> Configurador </MDBNavbarItem>
        <MDBNavbarItem to="/comparador"> Comparador </MDBNavbarItem>
        <MDBNavbarItem to="/recomendador">Recomendador </MDBNavbarItem>
        <MDBNavbarItem>
          <!-- Navbar dropdown -->

          <MDBDropdown class="nav-item" v-model="dropdown1">
            <MDBDropdownToggle
              tag="a"
              class="nav-link"
              @click="dropdown1 = !dropdown1"
              >Servicios</MDBDropdownToggle
            >
            <MDBDropdownMenu aria-labelledby="dropdownMenuButton">
              <MDBDropdownItem to="/mantenimiento" :disabled="!store.username"
                >Consultar Mantenimiento</MDBDropdownItem
              >
              <MDBDropdownItem to="/citas" :disabled="!store.username"
                >Pedir cita</MDBDropdownItem
              >
            </MDBDropdownMenu>
          </MDBDropdown>
        </MDBNavbarItem>
        <MDBNavbarItem v-if="!store.username" to="/" disabled>
          Usuario anónimo
        </MDBNavbarItem>
        <MDBNavbarItem v-if="store.username" to="/" disabled>
          Bienvenido {{ store.username }}
        </MDBNavbarItem>
      </MDBNavbarNav>
      <div class="d-sm-none my-3"> <!-- Version movil -->
        <router-link to="/admin">
          <MDBBtn v-if="store.esAdmin" class="gradient-custom text-white">
            Funcionalidad de admin
          </MDBBtn>
        </router-link>
      </div>
      <div class="d-none d-sm-block"><!-- Version escritorio -->
        <router-link to="/admin">
          <MDBBtn v-if="store.esAdmin" class="gradient-custom text-white">
            Funcionalidad de admin
          </MDBBtn>
        </router-link>
      </div>
      <router-link to="/inicioSesion">
        <MDBBtn
          v-if="!store.username"
          class="gradient-customInicSes text-white"
        >
          Iniciar Sesión
        </MDBBtn>
      </router-link>

      <router-link to="/">
        <MDBBtn
          v-if="store.username"
          class="gradient-custom2 text-white"
          @click="logout()"
        >
          Cerrar Sesión
        </MDBBtn>
      </router-link>
    </MDBCollapse>
  </MDBNavbar>
</template>
<style >
.gradient-customInicSes {
  background-image: linear-gradient(to top, #09203f 0%, #537895 100%);
}
.gradient-custom {
  background-image: linear-gradient(to right, #868f96 0%, #596164 100%);
}
.gradient-custom2 {
  background-image: radial-gradient(
    circle farthest-corner at 17.1% 22.8%,
    rgba(226, 24, 24, 1) 0%,
    rgba(160, 6, 6, 1) 90%
  );
}
</style>


    <script>
import {
  MDBNavbar,
  MDBNavbarToggler,
  MDBNavbarBrand,
  MDBNavbarNav,
  MDBNavbarItem,
  MDBCollapse,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem,
  MDBBtn,
} from "mdb-vue-ui-kit";
import { ref } from "vue";
import { useAuthStore } from "@/store/autenticar";
import router from "@/router";

export default {
  components: {
    MDBNavbar,
    MDBNavbarToggler,
    MDBNavbarBrand,
    MDBNavbarNav,
    MDBNavbarItem,
    MDBCollapse,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBBtn,
  },
  setup() {
    const collapse1 = ref(false);
    const dropdown1 = ref(false);
    const store = useAuthStore();

    return {
      collapse1,
      dropdown1,
      store,
    };
  },
  methods: {
    logout() {
      this.store.logout();
      router.push("/");
    },
  },
};
</script>