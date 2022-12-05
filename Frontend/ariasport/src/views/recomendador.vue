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
          <MDBDropdown v-model="dropdown1">
            <MDBDropdownToggle
              @click="dropdown1 = !dropdown1"
              color="secondary"
            >
              Cilindrada
            </MDBDropdownToggle>
            <MDBDropdownMenu transparent aria-labelledby="dropdownMenuButton">
              <MDBDropdownItem>
                <slider-recomendador
                  v-model:minVal="minRange"
                  v-model:maxVal="maxRange"
                  :minPosible="0"
                  :maxPosible="4000"
                />
              </MDBDropdownItem>
            </MDBDropdownMenu>
          </MDBDropdown>
        </div>
        <div class="col-3">
          <MDBDropdown v-model="dropdown2">
            <MDBDropdownToggle
              @click="dropdown2 = !dropdown2"
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
          <MDBDropdown v-model="dropdown3">
            <MDBDropdownToggle
              @click="dropdown3 = !dropdown3"
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
          <MDBDropdown v-model="dropdown4">
            <MDBDropdownToggle
              @click="dropdown4 = !dropdown4"
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
      </MDBCard>
    </div>
  </div>
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
  //MDBRange
} from "mdb-vue-ui-kit";
import { onMounted, ref } from "vue";
import axios from "axios";

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
    //MDBRange
  },
  setup() {
    const minRange = ref(0);
    const maxRange = ref(4000);
    onMounted(async () => {
      const { data: arrayTodosModelos } = await axios.get(`motos/modelos`);
      console.log(arrayTodosModelos);
    });

    const range3 = ref(2.5);
    const dropdown1 = ref(false);
    const dropdown2 = ref(false);
    const dropdown3 = ref(false);
    const dropdown4 = ref(false);

    // onMounted(async () => {
    //   const { data: arrayMotos } = await axios.get(
    //     `usuarios/${store.username}/motos`,
    //     {
    //       headers: {
    //         Authorization: `Bearer ${store.jwt}`,
    //       },
    //     }
    //   );
    //   motosUsuario.value = arrayMotos;
    //   console.log(motosUsuario.value);
    //   console.log("MOTO SELECCIONADA: "+ this.motoSeleccionada);
    // });

    return {
      minRange,
      maxRange,
      range3,
      dropdown1,
      dropdown2,
      dropdown3,
      dropdown4,
    };
  },
};
</script>