// 用户模块的路由配置
const routers = [
  {
    path: '/user/login',
    component: () => import('../view/user/Login.vue'),
  },
]

export default routers
