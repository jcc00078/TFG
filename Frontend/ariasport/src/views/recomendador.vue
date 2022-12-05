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
            <MDBDropdownMenu transparent aria-labelledby="dropdownMenuButton">
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
            >
              On/Off
            </MDBDropdownToggle>
            <MDBDropdownMenu dark aria-labelledby="dropdownMenuButton">
              <MDBDropdownItem to="/recomendador">On-road</MDBDropdownItem>
              <MDBDropdownItem divider />
              <MDBDropdownItem to="/recomendador">Off-road</MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdownCarnet">
            <MDBDropdownToggle
              @click="dropdownCarnet = !dropdownCarnet"
              color="secondary"
            >
              Carnet compatible
            </MDBDropdownToggle>
            <MDBDropdownMenu dark aria-labelledby="dropdownMenuButton">
              <MDBDropdownItem to="/recomendador">AM</MDBDropdownItem>
              <MDBDropdownItem divider />
              <MDBDropdownItem to="/recomendador">A2</MDBDropdownItem>
              <MDBDropdownItem divider />
              <MDBDropdownItem to="/recomendador">A</MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdownTipo">
            <MDBDropdownToggle
              @click="dropdownTipo = !dropdownTipo"
              color="secondary"
            >
              Tipo de moto
            </MDBDropdownToggle>
            <MDBDropdownMenu dark aria-labelledby="dropdownMenuButton">
              <MDBDropdownItem to="/recomendador">Naked</MDBDropdownItem>
              <MDBDropdownItem divider />
              <MDBDropdownItem to="/recomendador">Deportiva</MDBDropdownItem>
              <MDBDropdownItem divider />
              <MDBDropdownItem to="/recomendador">Scooter</MDBDropdownItem>
            </MDBDropdownMenu>
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
  <MDBBtn
    v-if="isFiltrado"
    title="Reiniciar filtros"
    @click="reiniciarFiltros()"
    outline="danger"
    style="padding: 0cm; position: fixed; bottom: 0"
    class="text-center-white m-2"
    floating
  >
    <i class="fas fa-redo text"></i>
  </MDBBtn>
</template>

<script>
import sliderRecomendador from "@/components/sliderRecomendador.vue";
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
    //MDBRange
  },
  setup() {
    const minRange = useDebouncedRef(0, 400);
    const maxRange = useDebouncedRef(2500, 400);
    const dropdownCilindrada = ref(false);
    const dropdownOnOffRoad = ref(false);
    const dropdownCarnet = ref(false);
    const dropdownTipo = ref(false);
    const motos = ref([]);
    const isFiltrado = ref(false);

    onMounted(async () => {
      const { data: arrayTodosModelos } = await axios.get(`motos/modelos`);
      motos.value = arrayTodosModelos;
    });

    watch([minRange, maxRange], async ([newMinRange, newMaxRange]) => {
      const { data: arrayMotos } = await axios.get("motos/modelos", {
        params: { cilindradaMin: newMinRange, cilindradaMax: newMaxRange },
      });
      motos.value = arrayMotos;
      isFiltrado.value = true;
    });

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
      motos,
      dropdownCilindrada,
      dropdownOnOffRoad,
      dropdownCarnet,
      dropdownTipo,
      isFiltrado,
    };
  },
  methods: {
    reiniciarFiltros() {
      this.minRange = 0;
      this.maxRange = 2500;
    },
  },
};
</script>