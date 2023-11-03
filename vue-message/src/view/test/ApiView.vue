<template>
  <div>api测试页</div>
  <hr />
  <div style="display: flex">
    <el-input v-model="echo" />
    <el-button @click="sendEcho">发送</el-button>
  </div>
  <hr />
  <div style="display: flex">
    <el-input v-model="email_info.email" />
    <el-button @click="sendEmail">发送邮件验证码</el-button>
  </div>

  <hr />
  <div style="display: flex">
    <el-input v-model="echo" />
    <el-button @click="sendEcho2">发送</el-button>
  </div>
</template>

<script lang="ts" setup>
import { ApiService } from '../../api/ApiService'
import { TeachService } from '../../api/TeachService'
import { ref } from 'vue'
import { ElButton, ElMessageBox, ElInput } from 'element-plus'

const echo = ref('')

const sendEcho = () => {
  ApiService.get(
    '/test/echo',
    {
      echo: echo.value,
    },
    (data: any) => {
      ElMessageBox.alert(JSON.stringify(data))
    },
  )
}

const email_info = ref({
  email: '',
  code: '',
})

const sendEmail = () => {
  ApiService.post('/tools/sendMailCode', email_info.value, (data: any) => {
    ElMessageBox.alert(data.message)
  })
}

const sendEcho2 = () => {
  TeachService.get(
    '/',
    {
      echo: echo.value,
    },
    (data: any) => {
      ElMessageBox.alert(JSON.stringify(data))
    },
  )
}
</script>

<style lang="scss" scoped></style>
