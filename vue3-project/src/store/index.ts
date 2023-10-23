import { defineStore } from 'pinia'
import { ApiService } from '../ts/ApiService'

// 持久化store中的信息
const countDao = {
  save: (count: any) => {
    localStorage.setItem('count', count)
  },
  load: () => {
    let count = localStorage.getItem('count')
    return count ? Number(count) : 0
  },
}

// 用户信息状态管理
const userDao = {
  // 本地保存用户信息的key
  key: 'huhuiyu.top.user.key',
  // 默认的空用户信息
  empty: {
    login: false,
    tbUser: {},
    tbUserInfo: {},
    userOtherInfo: {},
  },
  // 读取本地存储的用户信息
  load: () => {
    let user = localStorage.getItem(userDao.key)
    return user ? JSON.parse(user) : userDao.empty
  },
  save: (user: any) => {
    localStorage.setItem(userDao.key, JSON.stringify(user))
  },
}

const store = defineStore('main', {
  // 定义被状态管理的对象
  state: () => {
    return {
      count: countDao.load(),
      loginUser: userDao.load(),
    }
  },
  // 定义操作状态管理对象的方法
  actions: {
    increment() {
      this.count = this.count + 1
      countDao.save(this.count)
    },
    // 更新用户信息的方法
    updateUserInfo(cb: Function) {
      ApiService.post(
        '/user/auth/getUserInfo',
        {},
        (data: any) => {
          // 用户已经登录的情况
          if (data.success) {
            userDao.save({
              login: true,
              tbUser: data.tbUser,
              tbUserInfo: data.tbUserInfo,
              userOtherInfo: data.userOtherInfo,
            })
          } else {
            userDao.save(userDao.empty)
          }
          // 重新加载用户信息
          this.loginUser = userDao.load()
          // 通知加载完成
          cb()
        },
        true,
      )
    },
  },
})

export default store
export { store as store }
