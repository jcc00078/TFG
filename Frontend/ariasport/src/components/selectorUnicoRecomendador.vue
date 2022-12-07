<template>
  <MDBDropdownMenu dark aria-labelledby="dropdownMenuButton">
    <template
      v-for="(seleccionable, index) in seleccionables"
      v-bind:key="seleccionable.texto"
    >
      <MDBDropdownItem>
        <MDBBtn
          :color="seleccionable.valor == seleccionado ? 'primary' : ''"
          @click="actualizarSeleccion(seleccionable.valor)"
          class="text-white primary"
        >
          {{ seleccionable.texto }}
        </MDBBtn>
      </MDBDropdownItem>
      <MDBDropdownItem v-if="index !== seleccionables.length - 1" divider />
    </template>
  </MDBDropdownMenu>
</template>

<script>
import { MDBDropdownMenu, MDBDropdownItem, MDBBtn } from "mdb-vue-ui-kit";
export default {
  components: {
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBBtn,
  },
  props: {
    seleccionables: [Object],
    seleccionado: [String,Boolean],
  },
  emits:["update:seleccionado"],
  methods: {
    actualizarSeleccion(valor) {
      if (valor == this.seleccionado) {
        this.$emit("update:seleccionado",null);
      } else {
        this.$emit("update:seleccionado",valor);
      }
    },
  },
};
</script>