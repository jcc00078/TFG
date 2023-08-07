<template>
  <img class="img-thumbnail " :src="imagenSrc" />
</template>
  
  <script>
export default {
  props: {
    imagenData: String,
  },
  computed: {
    imagenSrc() {
      if (this.imagenData) {
        return `data:image/png;base64,${this.imagenData}`;
      } else {
        return require("@/assets/motoPorDefecto.png");
      }
    },
  },
};
</script>