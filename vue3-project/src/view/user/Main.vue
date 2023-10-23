<template>
  <div class="user-box">
    <div>用户首页</div>

    <div v-if="loginUser.login">
      <el-dropdown @command="handleCommand" split-button type="primary">
        {{ loginUser.tbUser.username }}
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              {{ loginUser.tbUser.nickname }}
              ({{ loginUser.tbUser.role }})
            </el-dropdown-item>
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
// 获取状态管理中的用户信息
import { storeToRefs } from 'pinia'
import { store } from '../../store/index'
import { ElButton, ElDropdown, ElDropdownItem, ElDropdownMenu } from 'element-plus'
import { ApiService } from '../../ts/ApiService'
import { useRouter } from 'vue-router'
const router = useRouter()

const { loginUser } = storeToRefs(store())
const { updateUserInfo } = store()

const handleCommand = (command: string) => {
  if (command === 'logout') {
    ApiService.post(
      '/user/auth/logout',
      {},
      () => {
        updateUserInfo(() => {
          router.push('/user/login')
        })
      },
      true,
    )
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
