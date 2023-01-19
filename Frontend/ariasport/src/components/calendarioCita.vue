<template>
  <div class="container">
    <div class="row justify-content-center" style="height: 100vh">
      <div class="row align-items-end">
        <h2 class="text-center my-1">Selecciona día para reservar la cita</h2>
      </div>

      <div class="row align-items-center justify-content-center">
        <v-date-picker
          v-model="fecha"
          :min-date="primerDia()"
          :max-date="fechaLimite()"
          :disabled-dates="fechasDeshabilitadas"
          is-dark
        >
        </v-date-picker>
      </div>
        <MDBModal
          staticBackdrop
          id="exampleModal"
          v-model="modalFormFecha"
          tabindex="-1"
          labelledby="exampleModalLabel"
        >
          <MDBModalHeader :close="false">
            <MDBModalTitle id="exampleModalLabel">
              Completa tu reserva
            </MDBModalTitle>
          </MDBModalHeader>
          <MDBModalBody>
            <form v-if="fecha" id="formulario" autocomplete="off">
              <label for="numBastidor"
                >Selecciona el modelo para asociar la cita:</label
              >
              <select class="mx-2" v-model="motoSeleccionada" id="moto">
                <option
                  v-for="(moto, index) in motosUsuario"
                  :value="moto"
                  :key="index"
                >
                  {{ moto.modelo }}
                </option>
              </select>

              <div class="text-center my-2">
                <label for="hora">Seleccione una hora:</label>
                <select class="mx-2" v-model="horaSeleccionada" id="hora">
                  <option
                    v-for="(hora, index) in horas"
                    :value="hora"
                    :key="index"
                  >
                    {{ getHora(hora) }}
                  </option>
                </select>
              </div>
            </form>
          </MDBModalBody>
          <MDBModalFooter>
            <MDBBtn color="secondary" @click="fecha = null">Atrás</MDBBtn>
            <MDBBtn
              color="primary"
              :disabled="horaSeleccionada == '' || motoSeleccionada == ''"
              @click="realizaReserva()"
              >Reservar</MDBBtn
            >
          </MDBModalFooter>
        </MDBModal>
    </div>
  </div>
</template>
<style>
.vc-day-content.is-disabled {
  pointer-events: none; /* Desactivo los eventos(click,hover...) para los días que no se pueden elegir cita */
}
</style>
<script>
import "v-calendar/dist/style.css";
import axios from "axios";
import { useAuthStore } from "@/store/autenticar";
import {
  MDBModal,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
  MDBModalFooter,
  MDBBtn,
} from "mdb-vue-ui-kit";
import { ref, watch, onMounted } from "vue";

export default {
  components: {
    MDBModal,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
    MDBBtn,
  },
  setup() {
    const exampleModalButtonWithIcon = ref(false);
    const fecha = ref(null);
    const motosUsuario = ref([]);
    const store = useAuthStore();
    const horaSeleccionada = ref("");
    const motoSeleccionada = ref("");

    const modalFormFecha = ref(false);
    const horas = ref([]);
    const fechasDeshabilitadas = ref([]);

    onMounted(async () => {
      const { data: dias } = await axios.get("citas/diasDeshabilitados", {
        headers: {
          Authorization: `Bearer ${store.jwt}`,
        },
      });
      fechasDeshabilitadas.value = dias.map((f) => new Date(f));

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

    watch([fecha], async ([fechaNueva]) => {
      if (fechaNueva == null) {
        modalFormFecha.value = false;
        motoSeleccionada.value = "";
        horaSeleccionada.value = "";
        return;
      }
      modalFormFecha.value = true;
      const date = new Date(fechaNueva);
      const dia = date.getDate().toString().padStart(2, "0");
      const mes = (date.getMonth() + 1).toString().padStart(2, "0");
      const anio = date.getFullYear();
      const strFecha = `${anio}-${mes}-${dia}`;

      const listaHoras = await axios.get(`citas`, {
        headers: {
          Authorization: `Bearer ${store.jwt}`,
        },
        params: {
          fecha: strFecha,
        },
      });
      horas.value = listaHoras.data;
    });

    return {
      exampleModalButtonWithIcon,
      fecha,
      motosUsuario,
      store,
      horaSeleccionada,
      motoSeleccionada,
      modalFormFecha,
      horas,
      fechasDeshabilitadas,
    };
  },
  methods: {
    getHora(fecha) {
      const date = new Date(fecha);
      const hora = date.getHours().toString().padStart(2, "0");
      const minuto = date.getMinutes().toString().padStart(2, "0");
      return `${hora}:${minuto}`;
    },
    primerDia() {
      let fechaComienzo = new Date();
      fechaComienzo.setDate(fechaComienzo.getDate() + 1); //Mañana
      return fechaComienzo;
    },
    fechaLimite() {
      let fechaUltima = new Date();
      fechaUltima.setDate(fechaUltima.getDate() + 1); //Mañana
      fechaUltima.setMonth(fechaUltima.getMonth() + 2); //Se puede elegir cita hasta 2 meses posteriormente al día de hoy
      return fechaUltima;
    },
    realizaReserva() {
      console.log("Realizando reserva");
    },
  },
};
</script>