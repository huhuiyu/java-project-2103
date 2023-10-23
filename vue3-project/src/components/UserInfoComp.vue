<template>
  <div class="user-box">
    <div>
      <slot></slot>
    </div>

    <div v-if="loginUser.login">
      <el-dropdown @command="handleCommand" split-button type="primary">
        {{ loginUser.tbUser.username }}
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              {{ loginUser.tbUser.nickname }}
              ({{ loginUser.tbUser.role }})
            </el-dropdown-item>
            <el-dropdown-item divided></el-dropdown-item>

            <el-dropdown-item command="main">首页</el-dropdown-item>
            <el-dropdown-item command="message">简易留言板</el-dropdown-item>

            <el-dropdown-item divided></el-dropdown-item>
            <el-dropdown-item command="logout">安全退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
    <div v-else>
      <ElButton @click="router.push('/user/login')">需要登录</ElButton>
    </div>
  </div>
</template>

<script lang="ts" setup>
// 路由的部分
import { useRouter } from 'vue-router'
const router = useRouter()
// 状态管理的部分
import { storeToRefs } from 'pinia'
import { store } from '../store/index'
const { loginUser } = storeToRefs(store())
const { updateUserInfo } = store()

import { ElButton, ElDropdown, ElDropdownItem, ElDropdownMenu } from 'element-plus'
import { ApiService } from '../ts/ApiService'

const handleCommand = (command: string) => {
  if (command === 'logout') {
    ApiService.post(
      '/user/auth/logout',
      {},
      () => {
        updateUserInfo(() => {
          // router.push('/user/login')
        })
      },
      true,
    )
  } else if (command == 'main') {
    router.push('/user/main')
  } else if (command == 'message') {
    router.push('/user/message')
  }
}
</script>

<style lang="scss" scoped>
.user-box {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 1rem;
}
</style>
