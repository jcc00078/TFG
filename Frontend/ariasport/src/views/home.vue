<template>
  <div
    class="container-fluid d-flex justify-content-center align-items-center vh-100"
  >
    <div class="d-flex justify-content-center align-items-center">
      <img
        class="img-fluid custom-image-size"
        src="@/assets/logo_index.png"
        alt="2023"
      />
    </div>
  </div>
</template>
<style>
.custom-image-size {
  max-width: 100%;
  height: auto;
}
/* Estilo global para ocultar la barra de desplazamiento vertical */
.hide-scrollbar {
  overflow-y: hidden;
}
</style>
<script>
export default {
  mounted() {
    document.body.classList.add("hide-scrollbar");
  },
  beforeUnmount() {
    document.body.classList.remove("hide-scrollbar");
  },
};
</script>
