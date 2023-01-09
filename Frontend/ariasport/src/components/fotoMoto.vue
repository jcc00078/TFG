<template>
  <div
    class="row-6 d-flex"
    :class="redirectLink ? 'bg-image hover-zoom hover-overlay' : ''"
  >
    <img class="col-6" :src="imagenSrc" />
    <router-link v-if="redirectLink" :to="redirectLink">
      <div
        class="mask"
        style="background-color: rgba(251, 251, 251, 0.2)"
      ></div>
    </router-link>
  </div>
</template>

<script>
export default {
  props: {
    imagenData: String,
    redirectLink: String,
  },
  computed: {
    imagenSrc() {
      if (this.imagenData) {
        return `data:image/png;base64,${this.imagenData}`;
      } else {
        return require("@/assets/moto por defecto.png");
      }
    },
  },
};
</script>