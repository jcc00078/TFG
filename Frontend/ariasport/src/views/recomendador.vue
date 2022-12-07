<template>
  <div class="container">
    <div class="row align-items-center bg-light mb-2" style="height: 200px">
      <h2
        class="text-center"
        style="
          color: darkred;
          font-size: 83px;
          font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
            'Lucida Sans', Arial, sans-serif;
        "
      >
        Tu recomendador
      </h2>
    </div>

    <div class="row my-5 bg-light mb-2" style="height: 200px">
      <div class="row align-items-start" style="height: min-content">
        <h2
          class="text-center"
          style="
            color: black;
            font-size: 40px;
            font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
              'Lucida Sans', Arial, sans-serif;
            padding: 1px;
          "
        >
          Elige estos filtros para buscar los modelos que mejor se adaptan a ti
        </h2>
      </div>
      <div class="row text-center">
        <div class="col-3">
          <MDBDropdown v-model="dropdownCilindrada">
            <MDBDropdownToggle
              @click="dropdownCilindrada = !dropdownCilindrada"
              color="secondary"
            >
              Cilindrada
              <i class="fas fa-cogs mx-1"></i>
            </MDBDropdownToggle>
            <MDBDropdownMenu
              class="my-2"
              style="padding: 0cm"
              aria-labelledby="dropdownMenuButton"
            >
              <MDBDropdownItem>
                <slider-recomendador
                  v-model:minVal="minRange"
                  v-model:maxVal="maxRange"
                  :minPosible="0"
                  :maxPosible="2500"
                />
              </MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdownOnOffRoad">
            <MDBDropdownToggle
              @click="dropdownOnOffRoad = !dropdownOnOffRoad"
              color="secondary"
              class=""
            >
              On/Off
              <i class="fas fa-road mx-1"></i>
            </MDBDropdownToggle>
            <selector-unico-recomendador
              :seleccionables="[
                { texto: 'onRoad', valor: false },
                { texto: 'offRoad', valor: true },
              ]"
              v-model:seleccionado="onOffRoad"
            />
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdownCarnet">
            <MDBDropdownToggle
              @click="dropdownCarnet = !dropdownCarnet"
              color="secondary"
            >
              Carnet compatible
              <i class="far fa-id-card mx-1"></i>
            </MDBDropdownToggle>

            <selector-unico-recomendador
              :seleccionables="[
                { texto: 'AM', valor: 'AM' },
                { texto: 'A2', valor: 'A2' },
                {texto: 'A', valor: 'A'}
              ]"
              v-model:seleccionado="carnet"
            />
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdownTipo">
            <MDBDropdownToggle
              @click="dropdownTipo = !dropdownTipo"
              color="secondary"
            >
              Tipo de moto
              <i class="fas fa-motorcycle mx-1"></i>
            </MDBDropdownToggle>
            <selector-unico-recomendador
              :seleccionables="[
                { texto: 'Naked', valor: 'Naked' },
                { texto: 'Deportiva', valor: 'Deportiva' },
                {texto: 'Scooter', valor: 'Scooter'}
              ]"
              v-model:seleccionado="tipoMoto"
            />
          </MDBDropdown>
        </div>
      </div>
    </div>
    <div class="row my-5 text-center bg-light mb-2" style="height: 400px">
      <MDBCard>
        <MDBCardBody>
          <MDBCardTitle>Modelos recomendados</MDBCardTitle>
          <MDBCardText> Elige 2 motocicletas para compararlas </MDBCardText>
        </MDBCardBody>
        <div
          class="row-6 bg-image hover-zoom hover-overlay"
          v-for="moto in motos"
          v-bind:key="moto"
        >
          <img
            class="col-6"
            :src="`data:image/png;base64,${moto.imagenData}`"
          />
        </div>
      </MDBCard>
    </div>
  </div>

  <MDBTooltip
    v-model="tooltip1"
    direction="top"
    style="padding: 0cm; position: fixed; bottom: 1.75cm"
  >
    <template #reference>
      <MDBBtn
        @click="reiniciarFiltros()"
        outline="danger"
        style="padding: 0cm; position: fixed; bottom: 0"
        class="text-center-white m-2"
        floating
      >
        <i class="fas fa-redo text"></i>
      </MDBBtn>
    </template>
    <template #tip> Reiniciar filtros </template>
  </MDBTooltip>
</template>

<script>
import sliderRecomendador from "@/components/sliderRecomendador.vue";
import selectorUnicoRecomendador from "@/components/selectorUnicoRecomendador.vue";
import {
  MDBCard,
  MDBCardBody,
  MDBCardTitle,
  MDBCardText,
  MDBDropdown,
  MDBDropdownToggle,
  MDBDropdownMenu,
  MDBDropdownItem,
  MDBBtn,
  MDBTooltip,
  //MDBRange
} from "mdb-vue-ui-kit";
import { onMounted, ref, watch } from "vue";
import axios from "axios";
import { useDebouncedRef } from "@/composables/useDebouncedRef.js";

export default {
  components: {
    sliderRecomendador,
    MDBDropdown,
    MDBDropdownToggle,
    MDBDropdownMenu,
    MDBDropdownItem,
    MDBCard,
    MDBCardBody,
    MDBCardTitle,
    MDBCardText,
    MDBBtn,
    MDBTooltip,
    selectorUnicoRecomendador
    //MDBRange
  },
  setup() {
    const minRange = useDebouncedRef(0, 400);
    const maxRange = useDebouncedRef(2500, 400);
    const onOffRoad = ref(null);
    const carnet = ref(null);
    const tipoMoto = ref(null);
    const dropdownCilindrada = ref(false);
    const dropdownOnOffRoad = ref(false);
    const dropdownCarnet = ref(false);
    const dropdownTipo = ref(false);
    const motos = ref([]);
    const tooltip1 = ref(false);

    onMounted(async () => {
      const { data: arrayTodosModelos } = await axios.get(`motos/modelos`);
      motos.value = arrayTodosModelos;
    });

    watch(
      [minRange, maxRange, onOffRoad, carnet, tipoMoto],
      async ([newMinRange, newMaxRange, newOnOffRoad,newCarnet,newTipoMoto]) => {
        const { data: arrayMotos } = await axios.get("motos/modelos", {
          params: {
            cilindradaMin: newMinRange,
            cilindradaMax: newMaxRange,
            offRoad: newOnOffRoad,
            carnetCompatible: newCarnet,
            tipo:newTipoMoto,
          },
        });
        motos.value = arrayMotos;
      }
    );

    // onMounted(async () => {
    //   const { data: motos } = await axios.get(
    //     `usuarios/${store.username}/motos`,
    //     {
    //       headers: {
    //         Authorization: `Bearer ${store.jwt}`,
    //       },
    //     }
    //   );
    //   motosUsuario.value = motos;
    //   console.log(motosUsuario.value);
    //   console.log("MOTO SELECCIONADA: "+ this.motoSeleccionada);
    // });

    return {
      minRange,
      maxRange,
      onOffRoad,
      carnet,
      tipoMoto,
      motos,
      dropdownCilindrada,
      dropdownOnOffRoad,
      dropdownCarnet,
      dropdownTipo,
      tooltip1,
    };
  },
  methods: {
    reiniciarFiltros() {
      this.minRange = 0;
      this.maxRange = 2500;
      this.onOffRoad= null;
      this.carnet= null;
      this.tipoMoto = null;

    },
  },
};
</script>