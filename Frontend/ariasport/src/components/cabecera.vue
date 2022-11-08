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
        <MDBNavbarItem to="/principal"> Inicio </MDBNavbarItem>
        <MDBNavbarItem to="/configurador"> Configurador </MDBNavbarItem>
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
              <MDBDropdownItem to="/mantenimiento"
              :disabled ="store.noEsanonimo" >Consultar Mantenimiento</MDBDropdownItem>
              <MDBDropdownItem href="#" :disabled ="store.noEsanonimo">Pedir cita</MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </MDBNavbarItem>
        <MDBNavbarItem v-if="store.noEsanonimo" to="/" disabled> Usuario anónimo </MDBNavbarItem>
      </MDBNavbarNav>
      <router-link to="/inicioSesion">
        <MDBBtn color ="primary" class="text-white">
          Iniciar Sesión
        </MDBBtn>
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
import { useStore } from "@/store/autenticar";

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
    MDBBtn
},
  setup() {
    const collapse1 = ref(false);
    const dropdown1 = ref(false);
    const store = useStore();
    

    return {
      collapse1,
      dropdown1,
      store
    };
  },
};
</script>