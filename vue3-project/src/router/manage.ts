// manage的路由配置
const routers = [
  {
    path: '/manage/dept',
    component: () => import('../view/manage/Dept.vue'),
  },
  {
    path: '/manage/employee',
    component: () => import('../view/manage/Employee.vue'),
  },
]

export default routers
