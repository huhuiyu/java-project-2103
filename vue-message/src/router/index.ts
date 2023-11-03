import { createRouter, RouteRecordRaw, createWebHistory } from 'vue-router'
import test from './test'

const routes: RouteRecordRaw[] = [
  {
    // path就是地址栏访问路径
    path: '/',
    // component表示path路径对应的页面文件
    component: () => import('../view/Home.vue'),
  },
  {
    path: '/about',
    component: () => import('../view/About.vue'),
  },
  {
    path: '/pinia',
    component: () => import('../view/Pinia.vue'),
  },
  {
    path: '/news',
    component: () => import('../view/News.vue'),
  },
].concat(test)

// 创建router
const router = createRouter({
  // 配置为Hash模式
  history: createWebHistory(import.meta.env.BASE_URL),
  // 配置toures
  routes,
  // 路由跳转时返回顶部
  scrollBehavior() {
    return { top: 0 }
  },
})

// 设置前置路由守卫
router.beforeEach((to, from, next) => {
  console.log('路由前置：', to, from)
  next()
})

// 设置后置路由守卫
router.afterEach((to, from, failure) => {
  console.log('路由后置：', to, from, failure)
})

export { router }
