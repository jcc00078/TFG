import { defineStore } from "pinia";
export const useMotoStore = defineStore("motocicleta", {
  state: () => {
    return {
      marcas: [],
      modelos: {},
    };
  },
  actions: {
    setMarcas(marcas) {
      this.marcas = marcas;
    },
    setModelo(marca, modelos) {
      this.modelos[marca] = modelos;
    },
  },
});
export default useMotoStore;
