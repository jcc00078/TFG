<template>
  <div class="container">
    <div class="row justify-content-center m-4">
      <div class="row justify-content-center">
        <!-- Modal 1 -->
        <div class="col-lg-6">
          <ModalAdmin
            :card-title="'Crear una motocicleta'"
            :card-text="'Abrir formulario para crear una nueva motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleCrearMoto"
            @update:cerrado="muestraError = false"
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
                      @change="handleImagenFileMotoChange"
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
        <div class="col-lg-6">
          <ModalAdmin
            :card-title="'Crear accesorio para motocicleta'"
            :card-text="'Abrir formulario para crear accesorio para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleCrearAccesorio"
            @update:cerrado="muestraError = false"
          >
            <template #error>
              <div
                class="alert alert-danger border-color-red"
                border-color="red"
                role="alert"
                v-show="muestraError"
              >
                Error creando el accesorio
              </div>
            </template>
            <template #title> Creación de accesorio </template>
            <template #content>
              <div>
                <h2>Datos para crear el accesorio</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="codAcc">Código </label>
                    <input
                      v-model="codigoAccesorio"
                      type="number"
                      id="codAcc"
                      name="codAcc"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="nombreAcc">Nombre</label>
                    <input
                      v-model="nombreAccesorio"
                      type="text"
                      id="nombreAcc"
                      name="nombreAcc"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="precioAcc">Precio</label>
                    <input
                      v-model="precioAccesorio"
                      type="number"
                      id="precioAcc"
                      name="precioAcc"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="fabAcc">Fabricante</label>
                    <input
                      v-model="fabricanteAccesorio"
                      type="text"
                      id="fabAcc"
                      name="fabAcc"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="modCompatibles"
                      >Modelos compatibles separados por comas</label
                    >
                    <input
                      v-model="modelosCompatiblesAcc"
                      type="text"
                      id="modCompatibles"
                      name="modCompatibles"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="imgAcc">Imagen</label>
                    <input
                      type="file"
                      id="imgAcc"
                      name="imgAcc"
                      @change="handleImagenFileAccChange"
                    />
                  </div>
                </form>
              </div>
            </template>
          </ModalAdmin>
        </div>
      </div>

      <div class="row justify-content-center">
        <!-- Modal 3 -->
        <div class="col-lg-6">
          <ModalAdmin
            :card-title="'Crear grupo de accesorios para motocicleta'"
            :card-text="'Abrir formulario para crear grupo de accesorios para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleCrearGrupoAccesorios"
            @update:cerrado="muestraError = false"
          >
            <template #error>
              <div
                class="alert alert-danger border-color-red"
                border-color="red"
                role="alert"
                v-show="muestraError"
              >
                Error creando el grupo de accesorios
              </div>
            </template>
            <template #title> Creación de grupo de accesorios </template>
            <template #content>
              <div>
                <h2>Datos para crear el grupo de accesorios</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="codgrupoAcc"
                      >Código de los accesorios separados por comas
                    </label>
                    <input
                      v-model="codGrupoAcc"
                      type="text"
                      id="codgrupoAcc"
                      name="codgrupoAcc"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="numBast">Número de bastidor</label>
                    <input
                      v-model="numBastidor"
                      type="text"
                      id="numBast"
                      name="numBast"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="imgGAcc">Imagen</label>
                    <input
                      type="file"
                      id="imgGAcc"
                      name="imgGAcc"
                      @change="handleImagenFileGrupoAccChange"
                    />
                  </div>
                </form>
              </div>
            </template>
          </ModalAdmin>
        </div>

        <!-- Modal 4 -->
        <div class="col-lg-6">
          <ModalAdmin
            :card-title="'Crear revisión para motocicleta'"
            :card-text="'Abrir formulario para crear revisión para motocicleta'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleCrearRevision"
            @update:cerrado="muestraError = false"
          >
            <template #error>
              <div
                class="alert alert-danger border-color-red"
                border-color="red"
                role="alert"
                v-show="muestraError"
              >
                Error creando la revisión.
              </div>
            </template>
            <template #title> Crear revisión para motocicleta </template>
            <template #content>
              <div>
                <h2>Datos para crear revisión de motocicleta</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="idCita">Id de la cita</label>
                    <input
                      v-model="idCitaRev"
                      type="number"
                      id="idCita"
                      name="idCita"
                      required
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="codR">Código de la revisión</label>
                    <input
                      v-model="codRev"
                      type="number"
                      id="codR"
                      name="codR"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="pRev">Precio</label>
                    <input
                      v-model="precioRev"
                      type="text"
                      id="pRev"
                      name="pRev"
                    />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="kmsR">Kilómetros</label>
                    <input v-model="KmsRev" type="text" id="kmsR" name="kmsR" />
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="nBas">Número de bastidor</label>
                    <input
                      v-model="numBastidor"
                      type="text"
                      id="nBas"
                      name="nBas"
                    />
                  </div>
                </form>
              </div>
            </template>
          </ModalAdmin>
        </div>
      </div>

      <div class="row justify-content-center">
        <!-- Modal 5 -->
        <div class="col-lg-6">
          <ModalAdmin
            :card-title="'Actualizar revisión de marca'"
            :card-text="'Abrir formulario para actualizar revisión de marca'"
            :trigger-cerrar-modal="triggerCerrarModal"
            @update:enviado="handleModificarMarca"
            @update:cerrado="muestraError = false"
          >
            <template #error>
              <div
                class="alert alert-danger border-color-red"
                border-color="red"
                role="alert"
                v-show="muestraError"
              >
                Error modificando la marca.
                {{ infoAdicionalError }}
              </div>
            </template>
            <template #title> Actualizar revisión de marca </template>
            <template #content>
              <div>
                <h2>Datos para actualizar revisión de marca</h2>
                <form>
                  <div class="m-4">
                    <label class="mx-2" for="campo1"
                      >Seleccione una marca</label
                    >
                    <select
                      v-model="marcaSeleccionada"
                      id="marcaSelect"
                      name="marcaSelect"
                      required
                    >
                      <option value="" disabled>Seleccione una opción</option>
                      <!-- Aquí cargarías las opciones del select con las marcas disponibles -->
                      <option
                        v-for="marca in marcas"
                        :value="marca"
                        :key="marca"
                      >
                        {{ marca }}
                      </option>
                    </select>
                  </div>
                  <div class="m-4">
                    <label class="mx-2" for="campo2"
                      >Modificar datos {{ marcaSeleccionada }}
                    </label>
                    <textarea
                      class="col-12"
                      v-model="datosMarcaTexto"
                      rows="7"
                      cols="50"
                      :disabled="datosMarcaTexto == ''"
                    ></textarea>
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
import { ref, onMounted, watch } from "vue";
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
    const imagenMoto = ref(null);
    const cilindrada = ref();
    const offRoad = ref(false);
    const carnetCompatible = ref("");
    const triggerCerrarModal = ref(false);
    const muestraError = ref(false);
    const marcaSeleccionada = ref("");
    const marcas = ref([]);
    const datosMarcaTexto = ref("");
    const infoAdicionalError = ref("");
    const codigoAccesorio = ref();
    const nombreAccesorio = ref("");
    const precioAccesorio = ref();
    const fabricanteAccesorio = ref("");
    const modelosCompatiblesAcc = ref("");
    const imagenAccesorio = ref(null);
    const imagenGrupoAcc = ref(null);
    const codGrupoAcc = ref("");
    const datosRevisionTexto = ref("");
    const idCitaRev = ref();
    const codRev = ref();
    const precioRev = ref("");
    const KmsRev = ref("");

    const cargarMarcas = () => {
      const url = "motos/marcas";
      axios
        .get(url)
        .then((response) => {
          marcas.value = response.data;
        })
        .catch((error) => {
          console.error("Error al obtener las marcas:", error);
        });
    };
    const cargarDatosMarca = () => {
      const url = `marca/${marcaSeleccionada.value}`;
      axios
        .get(url, {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        })
        .then((response) => {
          datosMarcaTexto.value = JSON.stringify(response.data, null, 2);
        })
        .catch((error) => {
          console.error("Error al obtener los datos de la marca:", error);
        });
    };
    watch(marcaSeleccionada, cargarDatosMarca);

    const handleCrearMoto = async () => {
      const formData = new FormData();
      formData.append("numBastidor", numBastidor.value);
      formData.append("marca", marca.value);
      formData.append("modelo", modelo.value);
      formData.append("color", color.value);
      formData.append("tipo", tipo.value);
      formData.append("precio", precio.value);
      formData.append("dni_usuario", dni_usuario.value);
      formData.append("imagenFile", imagenMoto.value);
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

    const handleCrearAccesorio = async () => {
      const formData = new FormData();
      formData.append("cod", codigoAccesorio.value);
      formData.append("nombre", nombreAccesorio.value);
      formData.append("precio", precioAccesorio.value);
      formData.append("fabricante", fabricanteAccesorio.value);
      formData.append(
        "compatibles",
        modelosCompatiblesAcc.value.split(",").map((value) => value.trim())
      );
      formData.append("imagenFile", imagenAccesorio.value);

      try {
        muestraError.value = false;
        await axios.post("accesorios", formData, {
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
    const handleCrearGrupoAccesorios = async () => {
      const formData = new FormData();
      formData.append(
        "codAccesorios",
        codGrupoAcc.value.split(",").map((value) => value.trim())
      );
      formData.append("numBastidor", numBastidor.value);
      formData.append("imagenFile", imagenGrupoAcc.value);

      try {
        muestraError.value = false;
        const response = await axios.get("motos/modelos");
        const listaModelos = response.data;
        const motoGrupoAcc = listaModelos.find(
          (modelo) => modelo.numBastidor === numBastidor.value
        );
        await axios.post(`motos/${motoGrupoAcc.modelo}/grupos`, formData, {
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
    const handleCrearRevision = async () => {
      const datos = {
        idCita: idCitaRev.value,
        codRevision: codRev.value,
        precio: precioRev.value,
        kilometros: KmsRev.value,
        numBastidor: numBastidor.value,
      };
      try {
        infoAdicionalError.value = "";
        muestraError.value = false;
        await axios.post("revisiones", datos, {
          headers: {
            Authorization: `Bearer ${store.jwt}`,
          },
        });
        emitirCerrarModal();
      } catch (e) {
        console.log(e);
        muestraError.value = true;
      }
    };
    const handleModificarMarca = async () => {
      try {
        infoAdicionalError.value = "";
        muestraError.value = false;
        await axios.put(
          `marca/${marcaSeleccionada.value}`,
          JSON.parse(datosMarcaTexto.value),
          {
            headers: {
              Authorization: `Bearer ${store.jwt}`,
            },
          }
        );
        emitirCerrarModal();
      } catch (e) {
        infoAdicionalError.value = e.response.data.message;
        muestraError.value = true;
      }
    };

    const emitirCerrarModal = () => {
      triggerCerrarModal.value = !triggerCerrarModal.value;
    };
    // Función para manejar el cambio en el input de tipo "file" de la moto
    const handleImagenFileMotoChange = (event) => {
      imagenMoto.value = event.target.files[0];
    };
    const handleImagenFileAccChange = (event) => {
      imagenAccesorio.value = event.target.files[0];
    };
    const handleImagenFileGrupoAccChange = (event) => {
      imagenGrupoAcc.value = event.target.files[0];
    };

    onMounted(cargarMarcas);

    return {
      numBastidor,
      marca,
      modelo,
      color,
      tipo,
      precio,
      dni_usuario,
      imagenMoto,
      cilindrada,
      offRoad,
      carnetCompatible,
      handleCrearMoto,
      handleImagenFileMotoChange,
      triggerCerrarModal,
      store,
      muestraError,
      marcaSeleccionada,
      marcas,
      cargarDatosMarca,
      cargarMarcas,
      datosMarcaTexto,
      handleModificarMarca,
      infoAdicionalError,
      codigoAccesorio,
      nombreAccesorio,
      precioAccesorio,
      fabricanteAccesorio,
      modelosCompatiblesAcc,
      imagenAccesorio,
      handleImagenFileAccChange,
      handleCrearAccesorio,
      codGrupoAcc,
      imagenGrupoAcc,
      handleImagenFileGrupoAccChange,
      handleCrearGrupoAccesorios,
      datosRevisionTexto,
      handleCrearRevision,
      idCitaRev,
      codRev,
      precioRev,
      KmsRev,
    };
  },
};
</script>