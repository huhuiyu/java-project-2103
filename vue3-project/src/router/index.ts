import { createRouter, RouteRecordRaw, createWebHistory } from 'vue-router'
import manage from './manage'
import study from './study'
import user from './user'

const routes: RouteRecordRaw[] = [
  {
    // path就是地址栏访问的路径
    path: '/',
    // component就是对于地址的页面文件
    component: () => import('../view/Home.vue'),
  },
  {
    path: '/about',
    component: () => import('../view/About.vue'),
  },
  {
    path: '/test',
    component: () => import('../view/Test.vue'),
  },
]
  .concat(manage)
  .concat(study)
  .concat(user)
  .concat([
    {
      path: '/error',
      component: () => import('../view/Error.vue'),
    },
    // 全局路由拦截，一定在最后，也就是所有不在路由表中的都要被它拦截
    {
      path: '/:pathMatch(.*)*',
      redirect: '/error',
    },
  ])

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
