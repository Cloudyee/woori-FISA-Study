import { defineStore } from "pinia";
import { computed, ref } from "vue";


//use~: 일반적인 네이밍 컨벤션
//defineStore('store이름', store에서 관리될 state, getter, actions)
export const useTodoStore = defineStore('todoStore', () => {

    //state - ref, reactive와 같은 반응형 속성
    const todos = ref([
        {
            id: 1,
            title: "물 마시기",
            summary: '물을 마신다',
            category: 'todo',
        },
        {
            id: 2,
            title: "콜라 마시기",
            summary: '콜라를 마신다',
            category: 'progress',
        },
        {
            id: 3,
            title: "우유 마시기",
            summary: '우유를 마신다',
            category: 'done',
        }
    ]); //Homevue에서 쓰던 배열 값

    const selectedCategory = ref('all');

    //actions - state 값을 변경하는 함수
    //todo 추가- addTodo
    const addTodo = (newTodo) => {
        todos.value.push(newTodo)
    }
    //todo 수정 - editTodo

    //todo 삭제 - deleteTodo

    //todo 필터링 - filterTodos
    const filterTodos = (category) => {
        console.log('filterTodos');
        selectedCategory.value = category;
    }

    //getter(computed()와 동일)
    //필터링된 todo 목록 - filteredTodosByCategory
    const filteredTodosByCategory = computed(() => {
        const currentCategory = selectedCategory.value

        if (currentCategory === 'all') return todos.value;
        return todos.value.filter(todo => todo.category === currentCategory);
    })

    return {
        todos, addTodo, filterTodos, filteredTodosByCategory
    }
});