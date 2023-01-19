<template>
  <div class="container">
    <div class="min-value numberVal">
      <input
        type="number"
        :min="minPosible"
        :max="maxPosible"
        :value="minActual"
        disabled
      />
    </div>
    <div class="range-slider">
      <div
        class="progress"
        :style="{
          left: (minActual / maxPosible) * 100 + '%',
          right: 100 - (maxActual / maxPosible) * 100 + '%',
        }"
      ></div>
      <input
        type="range"
        class="range-min"
        :min="minPosible"
        :max="maxPosible"
        :value="minActual"
        @input="updateMinRange($event)"
      />
      <input
        type="range"
        class="range-max"
        :min="minPosible"
        :max="maxPosible"
        :value="maxActual"
        @input="updateMaxRange($event)"
      />
    </div>
    <div class="max-value numberVal">
      <input
        type="number"
        :min="minPosible"
        :max="maxPosible"
        :value="maxActual"
        disabled
      />
    </div>
  </div>
</template>

<style scoped>
.container {
  position: relative;
  place-items: center;
  display: flex;
  justify-content: center;
  width: 600px;
  height: 80px;
  background-color: #9c9c9c;
  border-radius: 40px;
  
  
  
  
}
.container input[type="number"] {
  width: 50px;
  height: 30px;
  background: #fff;
  border: 1px solid #ddd;
  font-size: 15px;
  font-weight: 700;
  text-align: center;
  border-radius: 5px;
}
.container input[type="number"]::-webkit-outer-spin-button,
.container input[type="number"]::-webkit-inner-spin-button {
  -webkit-appearance: none;
}
.container .range-slider {
  position: relative;
  width: 400px;
  height: 5px;
  background: #fff;
  outline: none;
  top: 1px;
  margin: 10px;
}

.container .range-slider .progress {
  height: 100%;
  background: #17a288;
  border-radius: 50px;
  position: absolute;
}

.container .range-slider input[type="range"] {
  position: absolute;
  top: -8px;
  left: -4px;
  width: 102%;
  -webkit-appearance: none;
  pointer-events: none;
  background: none;
  outline: none;
}

.container .range-slider input::-webkit-slider-thumb {
  pointer-events: auto;
  -webkit-appearance: none;
  width: 17px;
  height: 17px;
  background: #17a288;
  border-radius: 50px;
}
</style>

<script>
import {ref} from "vue";

export default {
  props: {
    minVal: Number,
    maxVal: Number,
    maxPosible: Number,
    minPosible: Number,
  },
  setup(props) {
    const minActual= ref(props.minVal);
    const maxActual = ref(props.maxVal);
 
  return {
    minActual,maxActual
  };
},

  emits: ["update:minVal", "update:maxVal"],
  methods: {
    updateMinRange(event) {
      const gap = (this.maxPosible-this.minPosible)*0.1;
      if (event.target.value > this.maxVal - gap) {
        event.target.value = this.maxVal - gap;
        return;
      }
      this.minActual=parseInt(event.target.value);
      this.$emit("update:minVal", parseInt(event.target.value));
    },

    updateMaxRange(event) {
      const gap = (this.maxPosible-this.minPosible)*0.1;
      if (event.target.value < this.minVal + gap) {
        event.target.value = this.minVal + gap;
        return;
      }
      this.maxActual=parseInt(event.target.value);
      this.$emit("update:maxVal", parseInt(event.target.value));
    },
  },
};
</script>