<template>
  <div class="container my-5">
    <div class="card">
      <div class="card-body">
        <h3 class="card-title text-center">Registro de usuarios</h3>
        <form class="row g-3 form-floating" novalidate  @submit.prevent="registro()">
          <div class="col-md-12">
            <label for="dni" class="form-label">Introduce tu DNI</label>
            <input 
            type="text" 
            class="form-control" 
            id="dni" 
            v-model="dni"
            required />
          </div>
          <div class="col-md-12">
            <label for="nombre" class="form-label">Nombre</label>
            <input 
            type="text" 
            class="form-control" 
            id="nombre" 
            v-model="nombre"
            required />
          </div>
          <div class="col-md-12">
            <label for="Apellidos" class="form-label">Apellidos</label>
            <div class="input-group has-validation">
              <input
                type="text"
                class="form-control"
                id="Apellidos"
                v-model="apellidos"
                aria-describedby="inputGroupPrepend"
                required
              />
            </div>
          </div>
          <div class="col-md-12">
            <label for="password" class="form-label">Contraseña</label>
            <input
              type="password"
              class="form-control"
              id="password"
              v-model="password"
              required
            />
          </div>
          <div class="row">
            <div class="d-grid col-4 mx-auto">
              <button class="btn btn-primary center my-5" type="submit" to="/iniciarSesion">
                Registrarme
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import router from "@/router";
  import { ref } from "vue";
  import axios from "axios";

export default{
setup() {
  const dni = ref("");
  const password = ref("");
  const nombre = ref("");
  const apellidos = ref("");
  const creacionCorrecta=ref("false");

  return {
    dni,
    password,
    nombre,
    apellidos,
    creacionCorrecta,
  };
},
  methods: {
    registro(){
      let json = {
        dni_usuario: this.dni,
        contrasena: this.password,
        nombre: this.nombre,
        apellidos: this.apellidos, 
      };
      axios
      .post("usuarios", json)
      .then(() => {
        this.creacionCorrecta =true;
        router.push("inicioSesion");        
      })
      .catch(()=> {
        this.creacionCorrecta = "Error, no se ha podido registrar al usuario";
      });
    },
  }
};
</script>