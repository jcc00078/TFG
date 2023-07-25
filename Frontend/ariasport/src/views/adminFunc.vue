<template>
  <div class="container">
    <div class="row justify-content-center m-4">
      <div class="row justify-content-center">
        <!-- Modal 1 -->
        <div class="col-6">
          <ModalAdmin
            :card-title="'Crear una motocicleta'"
            :card-text="'Abrir formulario para crear una nueva motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleCrearMoto"
            @update:cerrado="muestraError=false"
          >
            <template #error>
              <div
                class="alert alert-danger border-color-red"
                border-color="red"
                role="alert"
                v-show="muestraError"
              >
                Error creando la motocicleta
              </div>
            </template>
            <template #title> Creación de motocicleta </template>
            <template #content>
              <div>
                <h2>Datos para crear motocicleta</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="campo1">Número de bastidor</label>
                    <input
                      v-model="numBastidor"
                      type="text"
                      id="campo1"
                      name="campo1"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo2">Marca</label>
                    <input
                      v-model="marca"
                      type="text"
                      id="campo2"
                      name="campo2"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo3">Modelo</label>
                    <input
                      v-model="modelo"
                      type="text"
                      id="campo3"
                      name="campo3"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo4">Color</label>
                    <input
                      v-model="color"
                      type="text"
                      id="campo4"
                      name="campo4"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo5">Tipo</label>
                    <input
                      v-model="tipo"
                      type="text"
                      id="campo5"
                      name="campo5"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo6">Precio</label>
                    <input
                      v-model="precio"
                      type="number"
                      id="campo6"
                      name="campo6"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo7">Dni asociado</label>
                    <input
                      v-model="dni_usuario"
                      type="text"
                      id="campo7"
                      name="campo7"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo8">Imagen</label>
                    <input
                      type="file"
                      id="campo8"
                      name="campo8"
                      @change="handleImagenFileChange"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo9">Cilindrada</label>
                    <input
                      v-model="cilindrada"
                      type="number"
                      id="campo9"
                      name="campo9"
                      step="1"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo10">¿Es offroad?</label>
                    <input
                      v-model="offRoad"
                      type="checkbox"
                      id="campo10"
                      name="campo10"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo11">Carnet compatible</label>
                    <input
                      v-model="carnetCompatible"
                      type="text"
                      id="campo11"
                      name="campo11"
                    />
                  </div>
                </form>
              </div>
            </template>
          </ModalAdmin>
        </div>

        <!-- Modal 2 -->
        <div class="col-6">
          <ModalAdmin
            :card-title="'Crear accesorio para motocicleta'"
            :card-text="'Abrir formulario para crear accesorio para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
          >
            <template #title> Title modal 2 </template>
            <template #content> Contenido modal 2 </template>
          </ModalAdmin>
        </div>
      </div>

      <div class="row justify-content-center">
        <!-- Modal 3 -->
        <div class="col-6">
          <ModalAdmin
            :card-title="'Crear grupo de accesorios para motocicleta'"
            :card-text="'Abrir formulario para crear grupo de accesorios para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
          >
            <template #title> Title modal 3 </template>
            <template #content> Contenido modal 3 </template>
          </ModalAdmin>
        </div>

        <!-- Modal 4 -->
        <div class="col-6">
          <ModalAdmin
            :card-title="'Crear revisión para motocicleta'"
            :card-text="'Abrir formulario para crear revisión para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
          >
            <template #title> Title modal 4 </template>
            <template #content> Contenido modal 4 </template>
          </ModalAdmin>
        </div>
      </div>

      <div class="row justify-content-center">
        <!-- Modal 5 -->
        <div class="col-6">
          <ModalAdmin
            :card-title="'Actualizar revisión de marca'"
            :card-text="'Abrir formulario para actualizar revisión de marca'"
            :trigger-cerrar-modal="triggerCerrarModal"
          >
            <template #title> Actualizar revisión de marca </template>
            <template #content>
              <div>
                <h2>Datos para actualizar revisión de marca</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="campo1">Nombre de la marca</label>
                    <textarea> </textarea>
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo2">Modificar </label>
                    <input
                      v-model="marca"
                      type="text"
                      id="campo2"
                      name="campo2"
                      required
                    />
                  </div>
                </form>
              </div>
            </template>
          </ModalAdmin>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ModalAdmin from "@/components/modalAdmin.vue";
import { ref } from "vue";
import axios from "axios";
import { useAuthStore } from "@/store/autenticar";

export default {
  components: {
    ModalAdmin,
  },
  setup() {
    const store = useAuthStore();
    const numBastidor = ref("");
    const marca = ref("");
    const modelo = ref("");
    const color = ref("");
    const tipo = ref("");
    const precio = ref();
    const dni_usuario = ref("");
    const selectedImagenFile = ref(null);
    const cilindrada = ref();
    const offRoad = ref(false);
    const carnetCompatible = ref("");
    const triggerCerrarModal = ref(false);
    const muestraError = ref(false);

    const handleCrearMoto = async () => {
      const formData = new FormData();
      formData.append("numBastidor", numBastidor.value);
      formData.append("marca", marca.value);
      formData.append("modelo", modelo.value);
      formData.append("color", color.value);
      formData.append("tipo", tipo.value);
      formData.append("precio", precio.value);
      formData.append("dni_usuario", dni_usuario.value);
      formData.append("imagenFile", selectedImagenFile.value);
      formData.append("cilindrada", cilindrada.value);
      formData.append("offRoad", offRoad.value);
      formData.append("carnetCompatible", carnetCompatible.value);

      try {
        muestraError.value = false;
        await axios.post("motos", formData, {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
            "Content-Type": "multipart/form-data",
          },
        });
        emitirCerrarModal();
      } catch {
        muestraError.value = true;
      }
    };

    const emitirCerrarModal = () => {
      triggerCerrarModal.value = !triggerCerrarModal.value;
    };
    // Función para manejar el cambio en el input de tipo "file"
    const handleImagenFileChange = (event) => {
      selectedImagenFile.value = event.target.files[0];
    };

    return {
      numBastidor,
      marca,
      modelo,
      color,
      tipo,
      precio,
      dni_usuario,
      selectedImagenFile,
      cilindrada,
      offRoad,
      carnetCompatible,
      handleCrearMoto,
      handleImagenFileChange,
      triggerCerrarModal,
      store,
      muestraError,
    };
  },
};
</script>