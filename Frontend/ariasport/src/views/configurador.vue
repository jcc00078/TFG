<template>
  <div class="container">
    <div class="row my-5 text-center bg-light" style="height: 400px">
      <MDBCard>
        <MDBCardBody>
          <MDBCardTitle>Configura una motocicleta</MDBCardTitle>
          <MDBCardText> Elige una motocicleta para configurarla </MDBCardText>
        </MDBCardBody>
        <div class="row">
          <foto-moto
            class="justify-content-center moto-container my-3"
            v-for="moto in motos"
            v-bind:key="moto"
            :imagenData="moto.imagenData"
            :redirectLink="`/configurador/${moto.modelo}`"
            @mouseover="applyBrightness(1.2, moto)"
            @mouseleave="resetBrightness(moto)"
          />
        </div>
      </MDBCard>
    </div>
  </div>
</template>
<style scoped>
.moto-container {
  transition: filter 0.3s ease-in-out;
  max-width: 100%;
  background: none;
}

.moto-container:hover {
  filter: brightness(var(--brightness, 1)); /* Aplicar el brillo */
}
</style>


<script>
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
  methods: {
    applyBrightness(factor, moto) {
      moto.brightness = factor;
    },
    resetBrightness(moto) {
      moto.brightness = 1.0;
    },
  },
};
</script>
