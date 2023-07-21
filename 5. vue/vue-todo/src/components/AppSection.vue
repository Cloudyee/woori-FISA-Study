<template>
    <div>
        <TodoHeader @add-todo="addTodo" /><br>
        <TodoList :todos="todos" />
    </div>
</template>

<script setup>
import TodoHeader from './todos/TodoHeader.vue';
import TodoList from './todos/TodoList.vue';

const emit = defineEmits('add-todo');

const addTodo = (todo) => {
    emit('add-todo', todo);
}


defineProps({
    todos: {
        type: Array,
    }
})

</script>
