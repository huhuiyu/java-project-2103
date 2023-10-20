// study的路由配置
const routers = [
  {
    path: '/study/comp',
    component: () => import('../view/study/Comp.vue'),
  },
  {
    path: '/study/complife',
    component: () => import('../view/study/CompLife.vue'),
  },
  {
    path: '/study/pinia',
    component: () => import('../view/study/Pinia.vue'),
  },
  {
    path: '/study/piniaother',
    component: () => import('../view/study/PiniaOther.vue'),
  },
]

export default routers
