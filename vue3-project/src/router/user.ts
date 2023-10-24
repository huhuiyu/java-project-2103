// 用户模块的路由配置
const routers = [
  {
    path: '/user/login',
    component: () => import('../view/user/Login.vue'),
  },
  {
    path: '/user/main',
    component: () => import('../view/user/Main.vue'),
  },
  {
    path: '/user/message',
    component: () => import('../view/user/Message.vue'),
  },
  {
    path: '/user/messagedetail/:id',
    component: () => import('../view/user/MessageDetail.vue'),
  },
]

export default routers
