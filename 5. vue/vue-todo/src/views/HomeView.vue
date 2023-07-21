<script setup>
import { ref } from 'vue';
import AppHeader from '../components/AppHeader.vue';
import AppSection from '../components/AppSection.vue';
import DefaultLayout from '../layouts/DefaultLayout.vue';
import { useTodoStore } from '../stores/todo';
import { storeToRefs } from 'pinia';



const todoStore = useTodoStore();
//pinia의 store를 사용하려면 해당 store를 import
//디스트럭처링 할 경우, 반응성이 깨짐
//const {todos}=todoStore;
//console.log(todos);

const { filteredTodosByCategory } = storeToRefs(todoStore);

const dummyTodos = filteredTodosByCategory;


const addTodo = (todo) => dummyTodos.value.push(todo);


</script>

<template>
  <DefaultLayout>
    <AppHeader />
    <AppSection :todos="dummyTodos" @add-todo="addTodo" />
  </DefaultLayout>
</template>
