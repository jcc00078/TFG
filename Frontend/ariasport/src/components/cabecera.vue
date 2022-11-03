<template>
  <MDBNavbar class="justify-content-end" expand="lg" light bg="light" container>
    <MDBNavbarBrand to="/">LOGO</MDBNavbarBrand>
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
              <MDBDropdownItem href="#"
                >Consultar Mantenimiento</MDBDropdownItem
              >
              <MDBDropdownItem href="#">Pedir cita</MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </MDBNavbarItem>
        <MDBNavbarItem to="#" disabled> Usuario anónimo </MDBNavbarItem>
      </MDBNavbarNav>
      <router-link to="/inicioSesion" class="bg-primary text-white">
        Iniciar Sesión
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
} from "mdb-vue-ui-kit";
import { ref } from "vue";

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
  },
  setup() {
    const collapse1 = ref(false);
    const dropdown1 = ref(false);
    return {
      collapse1,
      dropdown1,
    };
  },
};
</script>