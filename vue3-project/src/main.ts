import { createApp } from 'vue'

// 路由配置导入
import { router } from './router/index'

// 饿了么plus样式
import 'element-plus/dist/index.css'

// 导入pinia
import { createPinia } from 'pinia'

import App from './App.vue'

let app = createApp(App)

// 使用路由
app.use(router)

// 使用pinia
app.use(createPinia())


app.mount('#app')
