<template>
    <select v-model="selctedCategory" @change="todoStore.filterTodos(selctedCategory)"
        class="p-2 text-gray-100 bg-gray-800 rounded" data-cy="todo-filter">
        <option value="all" selected>All</option>
        <option value="todo">📑 To do</option>
        <option value="progress">👀 On progress</option>
        <option value="done">😀 Done</option>
    </select>
</template>

<script setup>
import { ref } from 'vue';
import { useTodoStore } from '@/stores/todo';

const selctedCategory = ref('all');
const todoStore = useTodoStore();

</script>

<style lang="scss" scoped></style>