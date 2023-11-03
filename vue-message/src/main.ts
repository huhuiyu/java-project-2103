import { createApp } from 'vue'
import { router } from './router/index'
import App from './App.vue'
// 导入pinia
import { createPinia } from 'pinia'

import 'element-plus/dist/index.css'

const app = createApp(App)

app.use(router)
app.use(createPinia())

app.mount('#app')
