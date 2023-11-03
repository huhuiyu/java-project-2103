import { defineStore } from 'pinia'

export const store = defineStore('main', {
  // state的方法就是用来定义状态管理的变量
  state: () => {
    return {
      count: 0,
    }
  },
  // actions是用来定义状态管理要使用方法
  // 这里面声明的方法可以直接操控state中声明的变量
  actions: {
    increment() {
      this.count++
    },
  },
})

export default store
