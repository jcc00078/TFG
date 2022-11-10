<template>
  <div class="card" style="width: 18rem">
    <img src="../assets/logoHonda.jpg" class="card-img-top" alt="..." />

    <div class="card-body">
      <h5 class="card-title">Primera moto {{ seleccion }}</h5>
    </div>
    <ul class="list-group list-group-flush">
      <li class="list-group-item">
        <select
          v-model="seleccion"
          class="form-select"
          aria-label="Default select example"
        >
          <option selected value="">Selecciona una marca</option>
          <option id="Kawasaki" value="kawasaki">Kawasaki</option>
          <option id="Suzuki" value="suzuki">Suzuki</option>
          <option id="Honda" value="honda">Honda</option>
          <option id="Triumph" value="triumph">Triumph</option>
        </select>
      </li>

      <li class="list-group-item" v-if="seleccion">
        <select class="form-select" aria-label="Default select example">
            <option selected value="">Selecciona un modelo</option>
          <option v-for=" moto in motos" :key="moto">{{moto}}</option>
        </select>
      </li>
    </ul>
  </div>
</template>
  
  <script>
import { ref } from "vue";
export default {
  setup() {
    const seleccion = ref("");
    const modelo = {
      kawasaki: ["Z650", "Ninja 1000", "Vulcan S", "H2"],
      suzuki: ["GSX-S1000", "Hayabusa", "GSX-S125", "Scooter"],
      honda: ["CBR-650R", "Africa twin", "ADV", "CB1000R"],
      triumph: ["Street triple 765", "Speed Triple", "Trident", "Tiger"],
    };
    return {
      seleccion,
      modelo
    };
  },

  computed:{
    motos(){
        return this.modelo[this.seleccion];
    }
  }
  
  //   methods: {
  //     seleccionado(id) {
  //       this.seleccion = id;
  //       console.log(this.seleccion);
  //     },
  //   },
};
</script>