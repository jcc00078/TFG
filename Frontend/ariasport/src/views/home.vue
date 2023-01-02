<template>
    <MDBCarousel
      v-model="carousel1"
      :items="items1"
      fade
    />
  </template>
  
<script>
  import { ref } from "vue";
  import { MDBCarousel } from "mdb-vue-ui-kit";
  export default {
    components: {
      MDBCarousel
    },
    setup() {
      const items1 = [
        {
          src: "https://mdbootstrap.com/img/Photos/Slides/img%20(19).webp",
          alt: "...",
          label: "Elige la motocicleta que te apasione",
          caption: "Imágenes provisionales"
        },
        {
          src: "https://mdbootstrap.com/img/Photos/Slides/img%20(23).webp",
          alt: "...",
          label: "Third slide label",
          caption:
            "Praesent commodo cursus magna, vel scelerisque nisl consectetur."
        }
      ];
      const carousel1 = ref(0);
      return {
        items1,
        carousel1
      };
    }
  };
</script>