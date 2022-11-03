<template> <!--Template general donde voy a cargar la cabecera y las distintas rutas-->
 <div id="app">
    <cabecera/>
    <router-view/>
 </div>
</template>

<script>
import cabecera from '@/components/cabecera.vue'

export default {
  name: 'App',
  components: {
    cabecera
  }
}
</script>