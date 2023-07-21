import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

export const useCounterStore = defineStore('counter', () => {
  //state
  const count = ref(0)

  //getter
  const doubleCount = computed(() => count.value * 2)

  //action
  function increment() {
    count.value++
  }

  return { count, doubleCount, increment } //콜백 반환값
})
