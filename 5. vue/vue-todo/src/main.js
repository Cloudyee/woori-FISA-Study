import './assets/main.css'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

const app = createApp(App) //우리가 만든 뷰 어플리케이션

app.use(createPinia()) //어플에 피냐를 사용하겠다

app.use(router)

app.mount('#app')
