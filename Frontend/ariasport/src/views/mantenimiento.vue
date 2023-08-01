<template>
  <div class="container my-5">
    <MDBCard class="shadow-4-strong w-responsive mx-auto" text="center">
      <MDBCardBody>
        <MDBCardTitle>Tus motocicletas</MDBCardTitle>
        <MDBCardText>
          <div
            class="row-6 bg-image hover-zoom hover-overlay"
            v-for="moto in motosUsuario"
            v-bind:key="moto"
          >
            <img
              id="imagenMoto"
              class="col-6"
              :src="`data:image/png;base64,${moto.imagenData}`"
            />
            <router-link :to="`/mantenimiento/historial/${moto.numBastidor}`">
              <div
                class="mask"
                style="background-color: rgba(251, 251, 251, 0.2)"
              ></div>
            </router-link>
          </div>
        </MDBCardText>
      </MDBCardBody>
    </MDBCard>
  </div>
</template>

<script>
import { useAuthStore } from "@/store/autenticar";
import axios from "axios";
import {
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardTitle,
} from "mdb-vue-ui-kit";
import { ref, onMounted } from "vue";

export default {
  components: {
    MDBCardText,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
  },
  setup() {
    const form1Bastidor = ref("");
    const form1LoginCheck = ref("");
    const bastidorCorrecto = ref("false");
    const motosUsuario = ref([]);
    const store = useAuthStore();

    onMounted(async () => {
      const { data: arrayMotos } = await axios.get(
        `usuarios/${store.username}/motos`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      motosUsuario.value = arrayMotos;
    });

    return {
      form1Bastidor,
      form1LoginCheck,
      bastidorCorrecto,
      motosUsuario,
      store,
    };
  },
};
</script>