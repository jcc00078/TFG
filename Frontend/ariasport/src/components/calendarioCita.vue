<template>
  <div>
    <div class="d-flex align-items-start justify-content-center h-100 w-100">
      <div class="d-flex flex-column align-items-center">
        <h2 class="mt-5">Selecciona día para reservar la cita</h2>
        <div
          v-if="cargandoCalendario"
          class="spinner-border mt-4"
          style="width: 3rem; height: 3rem"
          role="status"
        >
          <span class="sr-only">Loading...</span>
        </div>
        <v-date-picker
          v-else
          id="calendario"
          class="mt-4"
          style="height: 300px; width: 500px"
          v-model="fecha"
          :min-date="primerDiaReserva()"
          :max-date="fechaLimite()"
          :disabled-dates="fechasDeshabilitadas"
          is-dark
        >
        </v-date-picker>
      </div>
    </div>
    <div class="text-center">
      <MDBModal
        staticBackdrop
        id="exampleModal"
        v-model="modalFormFecha"
        tabindex="-1"
        labelledby="exampleModalLabel"
      >
        <div
          class="alert alert-danger border-color-red"
          border-color="red"
          role="alert"
          v-show="errorReserva != ''"
        >
          {{ this.errorReserva }}
        </div>
        <MDBModalHeader :close="false">
          <MDBModalTitle id="exampleModalLabel">
            {{ fecha == null ? "Modifica tu reserva" : "Crea tu reserva" }}
          </MDBModalTitle>
        </MDBModalHeader>
        <MDBModalBody>
          <form id="formulario" autocomplete="off">
            <label for="modelo"
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
                  v-for="(hora, index) in horas.sort(
                    (a, b) =>
                      new Date(a.replace('T', ' ')) -
                      new Date(b.replace('T', ' '))
                  )"
                  :value="hora"
                  :key="index"
                >
                  {{ getHora(hora) }}
                </option>
              </select>
            </div>
            <div class="text-center my-2" v-if="fecha == null">
              <label for="hora">Seleccione una fecha:</label>
              <v-date-picker
                class="mt-4"
                style="height: 300px; width: 400px"
                v-model="nuevaFecha"
                :min-date="primerDiaModificarReserva()"
                :max-date="fechaLimite()"
                :disabled-dates="fechasDeshabilitadas"
                is-dark
              >
              </v-date-picker>
            </div>
          </form>
        </MDBModalBody>
        <MDBModalFooter>
          <MDBBtn color="secondary" @click="cerrarModal()">Cerrar</MDBBtn>
          <MDBBtn
            v-if="fecha"
            color="primary"
            :disabled="horaSeleccionada == '' || motoSeleccionada == ''"
            @click="realizaReserva()"
            >Reservar</MDBBtn
          >
          <MDBBtn
            v-if="!fecha"
            color="success"
            @click="modificaReserva()"
            :disabled="horaSeleccionada == '' || motoSeleccionada == ''"
            >Modificar</MDBBtn
          >
        </MDBModalFooter>
      </MDBModal>
    </div>
    <div class="text-center mt-5">
      <MDBBtn color="primary" @click="muestraTCitas()">Mis citas</MDBBtn>
    </div>
    <div class="d-flex flex-column align-items-center">
      <MDBCard
        v-show="mostrarTCitas"
        v-if="listaCitas"
        text="body"
        bg="info"
        class="m-5"
        style="width: 500px"
      >
        <MDBCardHeader class="text-center">
          <h4>Datos de mis citas</h4>
        </MDBCardHeader>
        <MDBCardBody>
          <MDBCardTitle>Días que he pedido cita</MDBCardTitle>
          <MDBCardText>
            <ul class="list-group">
              <li
                class="list-group-item"
                v-for="cita in listaCitas.sort(
                  (a, b) =>
                    new Date(a.horario.replace('T', ' ')) -
                    new Date(b.horario.replace('T', ' '))
                )"
                :key="cita.id"
              >
                <p
                  class="text-center"
                  style="text-decoration: underline; font-style: italic"
                >
                  {{ getModeloMoto(cita.numBastidor) }}
                </p>

                <p>
                  Fecha:
                  <strong>{{ getDia(cita.horario) }}</strong>
                  ----> Hora:
                  <strong>{{ getHora(cita.horario) }} </strong>
                  <MDBBtn
                    @click="eliminarCita(cita.id)"
                    class="m-2 d-print-none"
                    outline="danger"
                    style="border-width: 0px; padding: 0cm; height: auto"
                  >
                    <i class="fas fa-trash"></i>
                  </MDBBtn>
                  <MDBBtn
                    @click="mostrarModificarReserva(cita)"
                    class="m-2 d-print-none"
                    outline="warning"
                    style="border-width: 0px; padding: 0cm; height: auto"
                  >
                    <i class="fas fa-edit"></i>
                  </MDBBtn>
                </p>
              </li>
            </ul>
          </MDBCardText>
        </MDBCardBody>
      </MDBCard>
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
  MDBCard,
  MDBCardText,
  MDBCardBody,
  MDBCardTitle,
} from "mdb-vue-ui-kit";
import { ref, watch, onMounted, onBeforeMount } from "vue";

export default {
  components: {
    MDBModal,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
    MDBBtn,
    MDBCard,
    MDBCardText,
    MDBCardBody,
    MDBCardTitle,
  },
  setup() {
    const cargandoCalendario = ref(true);
    const exampleModalButtonWithIcon = ref(false);
    const fecha = ref(null);
    const motosUsuario = ref([]);
    const store = useAuthStore();
    const horaSeleccionada = ref("");
    const motoSeleccionada = ref("");

    const modalFormFecha = ref(false);
    const horas = ref([]);
    const fechasDeshabilitadas = ref([]);
    const errorReserva = ref("");
    const listaCitas = ref([]);
    const mostrarTCitas = ref(false);
    const editando = ref(false);
    const citaModificada = ref(null);
    const nuevaFecha = ref(null);

    onBeforeMount(async () => {
      // Hacer una llamada al servidor para obtener los datos del calendario
      const { data: dias } = await axios.get("citas/diasDeshabilitados", {
        headers: {
          Authorization: `Bearer ${store.jwt}`,
        },
      });
      cargandoCalendario.value = false;

      fechasDeshabilitadas.value = dias.map((f) => {
        let fecha = new Date(f);
        fecha.setHours(0, 0, 0, 0); // Establecer la hora a las 00:00:00
        return fecha;
      });
    });

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

      const { data: arrayCitas } = await axios.get(
        `citas/todas/${store.username}`,
        {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        }
      );
      listaCitas.value = arrayCitas;

      watch([fecha], async ([fechaNueva]) => {
        //fecha corresponde al valor previo de la variable fecha
        errorReserva.value = "";
        if (fechaNueva == null) {
          return;
        }
        await calcularHorasDisponibles(fechaNueva);
        modalFormFecha.value = true;
      });

      watch([nuevaFecha], async ([fechaNueva]) => {
        // nuevaFecha corresponde al valor previo de la variable fechaNueva
        errorReserva.value = "";
        if (fechaNueva == null) {
          return;
        }
        //Desactivo botón modificar cuando cambio de día
        if (getDia(horaSeleccionada.value) != getDia(fechaNueva)) {
          horaSeleccionada.value = "";
        }
        await calcularHorasDisponibles(fechaNueva);
        modalFormFecha.value = true;
        if (getDia(citaModificada.value.horario) == getDia(fechaNueva)) {
          horas.value.push(citaModificada.value.horario);
          horaSeleccionada.value = citaModificada.value.horario;
        }
      });
    });

    function muestraTCitas() {
      mostrarTCitas.value = !mostrarTCitas.value;
    }

    function getDia(fecha) {
      const date = new Date(fecha);
      const dia = date.getDate().toString().padStart(2, "0");
      const mes = (date.getMonth() + 1).toString().padStart(2, "0");
      const anio = date.getFullYear();
      const strFecha = `${anio}-${mes}-${dia}`;
      return strFecha;
    }

    async function calcularHorasDisponibles(diaFecha) {
      const strFecha = getDia(diaFecha);
      const listaHoras = await axios.get(`citas`, {
        headers: {
          Authorization: `Bearer ${store.jwt}`,
        },
        params: {
          fecha: strFecha,
        },
      });
      horas.value = listaHoras.data;
    }

    return {
      cargandoCalendario,
      exampleModalButtonWithIcon,
      fecha,
      motosUsuario,
      store,
      horaSeleccionada,
      motoSeleccionada,
      modalFormFecha,
      horas,
      fechasDeshabilitadas,
      errorReserva,
      listaCitas,
      mostrarTCitas,
      muestraTCitas,
      editando,
      calcularHorasDisponibles,
      citaModificada,
      nuevaFecha,
      getDia,
    };
  },

  methods: {
    getDisabledDates() {
      return this.fechasDeshabilitadas;
    },
    getHora(fecha) {
      const date = new Date(fecha);
      const hora = date.getHours().toString().padStart(2, "0");
      const minuto = date.getMinutes().toString().padStart(2, "0");
      return `${hora}:${minuto}`;
    },

    primerDiaReserva() {
      let fechaComienzo = new Date();
      fechaComienzo.setDate(fechaComienzo.getDate() + 1); // Mañana
      return this.calcularFechaDisponible(fechaComienzo);
    },
    primerDiaModificarReserva() {
      let fechaComienzo = new Date();
      return this.calcularFechaDisponible(fechaComienzo);
    },
    fechaLimite() {
      let fechaUltima = new Date();
      fechaUltima.setDate(fechaUltima.getDate() + 1); //Mañana
      fechaUltima.setMonth(fechaUltima.getMonth() + 2); //Se puede elegir cita hasta 2 meses posteriormente al día de hoy
      return fechaUltima;
    },
    calcularFechaDisponible(fechaComienzo) {
      fechaComienzo.setHours(0, 0, 0, 0); // Establecer la hora a las 00:00:00
      let ultimaFecha = this.fechaLimite();
      let fechaDisponible = null;

      while (!fechaDisponible && fechaComienzo <= ultimaFecha) {
        const esFechaDeshabilitada = this.fechasDeshabilitadas.find(
          (fecha) => fecha.getTime() === fechaComienzo.getTime()
        );
        if (!esFechaDeshabilitada) {
          fechaDisponible = fechaComienzo;
        } else {
          fechaComienzo.setDate(fechaComienzo.getDate() + 1);
        }
      }
      return fechaDisponible;
    },
    realizaReserva() {
      this.errorReserva = "";
      const json = {
        horario: this.horaSeleccionada,
        dni_usuario: this.store.username,
        numBastidor: this.motoSeleccionada.numBastidor,
      };
      axios
        .post(`citas`, json, {
          headers: {
            Authorization: `Bearer ${this.store.jwt}`,
          },
        })
        .then(async () => {
          const { data: arrayCitas } = await axios.get(
            `citas/todas/${this.store.username}`,
            {
              headers: {
                Authorization: `Bearer ${this.store.jwt}`,
              },
            }
          );
          this.listaCitas = arrayCitas;
          this.cerrarModal();
        })
        .catch(() => {
          this.errorReserva = "Error, cita no creada";
        });
    },
    getModeloMoto(numBastidor) {
      const moto = this.motosUsuario.find(
        (moto) => moto.numBastidor === numBastidor
      );
      return moto.modelo;
    },

    eliminarCita(idCita) {
      axios
        .delete(`citas/${idCita}`, {
          headers: {
            Authorization: `Bearer ${this.store.jwt}`,
          },
        })
        .then(async () => {
          const { data: arrayCitas } = await axios.get(
            `citas/todas/${this.store.username}`,
            {
              headers: {
                Authorization: `Bearer ${this.store.jwt}`,
              },
            }
          );
          this.listaCitas = arrayCitas;
        });
    },

    async mostrarModificarReserva(cita) {
      await this.calcularHorasDisponibles(cita.horario);
      this.horas.push(cita.horario);

      this.motoSeleccionada = this.motosUsuario.find(
        (motoUsuario) => motoUsuario.numBastidor === cita.numBastidor
      );
      this.horaSeleccionada = cita.horario;
      this.modalFormFecha = true;
      this.citaModificada = cita;
      this.nuevaFecha = new Date(cita.horario);
    },

    cerrarModal() {
      this.fecha = null;
      this.modalFormFecha = false;
      this.motoSeleccionada = "";
      this.horaSeleccionada = "";
      this.nuevaFecha = null;
    },
    modificaReserva() {
      const idCita = this.citaModificada.id;
      let json = {
        id: idCita,
        horario: this.horaSeleccionada,
        numBastidor: this.motoSeleccionada.numBastidor,
      };
      axios
        .put(`citas/${idCita}`, json, {
          headers: {
            Authorization: `Bearer ${this.store.jwt}`,
          },
        })
        .then(() => {
          const i = this.listaCitas.findIndex((cita) => cita.id === idCita);
          const nuevaCita = this.listaCitas[i];
          nuevaCita.horario = json.horario;
          nuevaCita.numBastidor = json.numBastidor;
          this.listaCitas.splice(i, 1, nuevaCita);
          this.cerrarModal();
        })
        .catch(() => {
          this.errorReserva = "No ha sido posible modificar la cita";
        });
    },
  },
};
</script>