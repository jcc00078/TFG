<template>
  <div>
    <MDBCard class="col-4 m-2">
      <MDBCardBody>
        <MDBCardTitle>{{ cardTitle }}</MDBCardTitle>
        <MDBCardText>{{ cardText }}</MDBCardText>
        <MDBBtn color="primary" @click="openModal">Elegir</MDBBtn>
      </MDBCardBody>

      <div class="text-center">
        <MDBModal
          staticBackdrop
          id="modalAdmin"
          v-model="modalFormAdmin"
          labelledby="modalTitle"
          style="padding: 0rem"
        >
          <slot name="error"> </slot>
          <MDBModalHeader :close="false">
            <MDBModalTitle id="modalTitle">
              <slot name="title"></slot>
            </MDBModalTitle>
          </MDBModalHeader>
          <MDBModalBody>
            <slot name="content"></slot>
          </MDBModalBody>

          <MDBModalFooter>
            <MDBBtn color="secondary" @click="cerrarModal()">Cerrar</MDBBtn>
            <MDBBtn color="primary" @click="confirmarModal()">Confirmar</MDBBtn>
          </MDBModalFooter>
        </MDBModal>
      </div>
    </MDBCard>
  </div>
</template>

<script>
import { ref, watch } from "vue";

import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBBtn,
  MDBModal,
  MDBModalHeader,
  MDBModalTitle,
  MDBModalBody,
  MDBModalFooter,
} from "mdb-vue-ui-kit";

export default {
  emits: ["update:enviado","update:cerrado"],
  props: {
    cardTitle: String,
    cardText: String,
    triggerCerrarModal: Boolean,
  },
  components: {
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBBtn,
    MDBModal,
    MDBModalHeader,
    MDBModalTitle,
    MDBModalBody,
    MDBModalFooter,
  },
  setup(props, { emit }) {
    // Añade una referencia para almacenar el archivo seleccionado
    const selectedImagenFile = ref(null);
    // Función para manejar el cambio en el input de tipo "file"
    const handleImagenFileChange = (event) => {
      selectedImagenFile.value = event.target.files[0];
    };
    const modalFormAdmin = ref(false);
    const openModal = () => {
      modalFormAdmin.value = true;
    };
    const cerrarModal = () => {
      modalFormAdmin.value = false;
      emit("update:cerrado", true);
    };
    const confirmarModal = () => {
      emit("update:enviado", true); // Emitir el evento para notificar al componente padre que se ha enviado la petición correctamente
    };
    watch(
      () => props.triggerCerrarModal,
      () => cerrarModal()
    );
    // Retorna las variables y funciones que quieres exponer en el template
    return {
      modalFormAdmin,
      openModal,
      cerrarModal,
      confirmarModal,
      handleImagenFileChange,
    };
  },
};
</script>
