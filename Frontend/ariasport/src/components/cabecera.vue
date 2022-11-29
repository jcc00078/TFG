<template>
  <MDBNavbar class="justify-content-end" expand="lg" light bg="light" container>
    <MDBNavbarBrand router-link to="/">
      <!-- <img src="../assets/logo.png"> -->
      LOGO
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
        <MDBNavbarItem to="/">Recomendador </MDBNavbarItem>
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
              <MDBDropdownItem to="/mantenimiento"  :disabled="!store.username"
                >Consultar Mantenimiento</MDBDropdownItem
              >
              <MDBDropdownItem to="/" :disabled="!store.username"
                >Pedir cita</MDBDropdownItem
              >
            </MDBDropdownMenu>
          </MDBDropdown>
        </MDBNavbarItem>
        <MDBNavbarItem v-if="!store.username" to="/" disabled>
          Usuario anónimo
        </MDBNavbarItem>
      </MDBNavbarNav>
      <router-link to="/inicioSesion">
        <MDBBtn v-if="!store.username" color="primary" class="text-white">
          Iniciar Sesión
        </MDBBtn>
        <router-link to="/">
          <MDBBtn
            v-if="store.username"
            color="danger"
            class="text-white"
            @click="logout()"
          >
            Cerrar Sesión
          </MDBBtn>
        </router-link>
      </router-link>
    </MDBCollapse>
  </MDBNavbar>
</template>


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
import {useAuthStore } from "@/store/autenticar";
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