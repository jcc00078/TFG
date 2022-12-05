<template>
  <div class="container">
    <div class="min-value numberVal">
      <input
        type="number"
        :min="minPosible"
        :max="maxPosible"
        :value="minVal"
        disabled
      />
    </div>
    <div class="range-slider">
      <div
        class="progress"
        :style="{
          left: (minVal / maxPosible) * 100 + '%',
          right: 100 - (maxVal / maxPosible) * 100 + '%',
        }"
      ></div>
      <input
        type="range"
        class="range-min"
        :min="minPosible"
        :max="maxPosible"
        :value="minVal"
        @input="updateMinRange($event)"
      />
      <input
        type="range"
        class="range-max"
        :min="minPosible"
        :max="maxPosible"
        :value="maxVal"
        @input="updateMaxRange($event)"
      />
    </div>
    <div class="max-value numberVal">
      <input
        type="number"
        :min="minPosible"
        :max="maxPosible"
        :value="maxVal"
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
  background: #9c9c9c;
  border-radius: 20px;
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
  background: #ddd;
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
  left: -5px;
  width: 101%;
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
<!-- 
Parecido a como hice en cartaComparador y Comparador.
Hacer 2 variables props con el minValor que se escoge y el maxValor que se escoge.
Luego hacer emits de estos valores(update) ya k cambian dinamicamente segun movamos el slider.
Para hacer esto ponemos en el campo input del valor minimo :value="minValor"  -->

<script>
export default {
  props: {
    minVal: Number,
    maxVal: Number,
    maxPosible: Number,
    minPosible: Number,
  },
  emits: ["update:minVal", "update:maxVal"],
  methods: {
    updateMinRange(event) {
      const gap = (this.maxPosible-this.minPosible)*0.1;
      if (event.target.value > this.maxVal - gap) {
        event.target.value = this.maxVal - gap;
        return;
      }
      this.$emit("update:minVal", parseInt(event.target.value));
    },

    updateMaxRange(event) {
      const gap = (this.maxPosible-this.minPosible)*0.1;
      if (event.target.value < this.minVal + gap) {
        event.target.value = this.minVal + gap;
        return;
      }
      this.$emit("update:maxVal", parseInt(event.target.value));
    },
  },
};

// const range = document.querySelectorAll('.range-slider input'),
// progress = document.querySelector('.range-slider .progress');
// let gap = 1000;
// const inputValue = document.querySelectorAll('.numberVal input');
// range.forEach(input => {
//   input.addEventListener('input', e => {
//     let minrange = parseInt(range[0].value),
//     maxrange = parseInt(range[1].value);
//     if (maxrange - minrange < gap) {
//       if (e.target.className === "range-min") {
//         range[0].value = maxrange - gap;
//       } else {
//         range[1].value = minrange + gap;
//       }
//     } else {
//       progress.style.left = (minrange / range[0].max) * 100 + '%';
//       progress.style.right = 100 - (maxrange / range[1].max) * 100 + '%';
//       inputValue[0].value = minrange;
//       inputValue[1].value = maxrange;
//     }
//   });
// });
</script>