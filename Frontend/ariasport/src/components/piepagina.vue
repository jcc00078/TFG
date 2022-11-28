<template>
  <MDBFooter :text="['center', 'lg-start']">
    <!-- Copyright -->
    <div class="fixed-bottom text-center p-3" style="background-color: rgba(0, 0, 0, 0.2)">
      © 2023 Copyright: AriaSport
    </div>
    <!-- Copyright -->
  </MDBFooter>
</template>

<script>
  import { MDBFooter } from 'mdb-vue-ui-kit';

  export default {
    components: {
      MDBFooter
    },
  };
</script>

