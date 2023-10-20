import { defineStore } from 'pinia'

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

const store = defineStore('main', {
  // 定义被状态管理的对象
  state: () => {
    return {
      count: countDao.load(),
    }
  },
  // 定义操作状态管理对象的方法
  actions: {
    increment() {
      this.count = this.count + 1
      countDao.save(this.count)
    },
  },
})

export default store
