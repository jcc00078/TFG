import { defineStore } from "pinia";
export const useMotoStore = defineStore("motocicleta", {
  state: () => {
    return {
      marcas: [],
      modelos: {},
      datosModelo: {},
    };
  },
  actions: {
    setMarcas(marcas) {
      this.marcas = marcas;
    },
    setModelo(marca, modelos) {
      this.modelos[marca] = modelos;
    },
    setDatosModelo(modelo, datos){
      this.datosModelo[modelo]=datos;
    }
  },
});
export default useMotoStore;
