<template>
  <div class="container">
    <div class="row my-5 text-center bg-light" style="height: 400px">
      <MDBCard>
        <MDBCardBody>
          <MDBCardTitle>Configura una motocicleta</MDBCardTitle>
          <MDBCardText> Elige una motocicleta para configurarla </MDBCardText>
        </MDBCardBody>
        <div class="row">
          <foto-moto class="justify-content-center"
            v-for="moto in motos"
            v-bind:key="moto"
            :imagenData="moto.imagenData"
            :redirectLink="`/configurador/${moto.modelo}`"
          />
        </div>
      </MDBCard>
    </div>
  </div>
</template>

<script>
//import router from "@/router";
import { onMounted, ref } from "vue";
import axios from "axios";
import fotoMoto from "@/components/fotoMoto.vue";

import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
} from "mdb-vue-ui-kit";
export default {
  components: {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    fotoMoto,
  },
  setup() {
    const motos = ref([]);
    onMounted(async () => {
      const { data: arrayTodosModelos } = await axios.get(`motos/modelos`);
      motos.value = arrayTodosModelos;
    });
    return {
      motos,
    };
  },
};
</script>
