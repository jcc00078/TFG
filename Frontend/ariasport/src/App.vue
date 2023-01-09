<template> <!--Template general donde voy a cargar la cabecera y las distintas rutas-->
 <div id="app" class="h-100 w-100">
    <cabecera/>
    <router-view></router-view>
   <!-- <piepagina/> -->
 </div>
</template>

<script>
import cabecera from '@/components/cabecera.vue'
//import piepagina from '@/components/piepagina.vue'

export default {
  name: 'App',
  components: {
    cabecera,
   // piepagina,
  }
}
</script>

<style>

</style>