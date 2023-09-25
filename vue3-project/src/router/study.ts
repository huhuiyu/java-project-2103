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
]

export default routers
