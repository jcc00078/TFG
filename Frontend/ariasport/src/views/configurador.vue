<template>
    <div class = "mx-auto mt-4">
    <h2 v-if="!opcionElegida" class="text-center mb-4">Escoge una marca</h2>
    <h2 v-if="opcionElegida" class="text-center mb-4">Escoge una motocicleta</h2>
  </div>
  <MDBRow :cols="['1', 'md-4']" class="g-0" >
    <MDBCol v-if="!opcionElegida">
      <MDBCard>
        <img src="../assets/logoSuzuki.jpg" class="img-fluid" top alt="logo suzuki"/>
        <MDBCardBody class="text-center">
          <MDBBtn id="Suzuki" color="success" @click="opcion()" rounded>
            Elegir
          </MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol v-if="!opcionElegida">
      <MDBCard>
        <img src="../assets/logoKawasaki.jpg" class="img-fluid" top alt="logo kawasaki"/>
        <MDBCardBody class="text-center">
          <MDBBtn id="Kawasaki" color="success" rounded @click="opcion()">
            Elegir
          </MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard v-if="!opcionElegida">
        <img src="../assets/logoHonda.jpg" class="img-fluid" top alt="logo honda"/>
        <MDBCardBody class="text-center">
          <MDBBtn id="Honda" color="success" rounded @click="opcion()">
            Elegir
          </MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
    <MDBCol>
      <MDBCard v-if="!opcionElegida">
        <img src="../assets/logoTriumph.png" class="img-fluid" top alt="logo triumph"/>
        <MDBCardBody class="text-center">
          <MDBBtn id="Triumph" color="success" rounded @click="opcion()">
            Elegir
          </MDBBtn>
        </MDBCardBody>
      </MDBCard>
    </MDBCol>
  </MDBRow>

</template>
<script>
//import router from "@/router";
import { ref } from "vue";

  import { MDBCol, MDBRow, MDBCard, MDBCardBody, MDBBtn } from "mdb-vue-ui-kit";
  export default {
    components: {
    MDBCol,
    MDBRow,
    MDBCard,
    MDBCardBody,
    MDBBtn
},
setup(){
  const opcionElegida=ref(false);
  return {
    opcionElegida
  };
},
methods: {
  opcion() {
      this.opcionElegida=true;
      //router.push("/configurador-"+this.opcionElegida)
  }
}

  }

</script>
