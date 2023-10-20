<template>
  <div class="main">
    <div>
      <ElCard>
        <template #header>用户登录</template>

        <div>
          <ElForm :model="user" ref="myform" :rules="rules">
            <ElFormItem prop="username">
              <ElInput v-model="user.username" placeholder="登录名"></ElInput>
            </ElFormItem>
            <ElFormItem prop="password">
              <ElInput :show-password="true" v-model="user.password" placeholder="密码"></ElInput>
            </ElFormItem>
            <ElFormItem>
              <ElButton @click="login(myform)" type="primary">登录</ElButton>
              <ElButton @click="reset(myform)" type="danger">重置</ElButton>
            </ElFormItem>
          </ElForm>
        </div>
      </ElCard>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive } from 'vue'
import { TbUser, TbUserInfo, UserOtherInfo } from '../../ts/UserInfo'
import { ElMessageBox, FormRules, ElButton, ElCard, ElForm, ElFormItem, ElInput, FormInstance } from 'element-plus'
import { ApiService } from '../../ts/ApiService'
import { BaseResult } from '../../ts/BaseResult'
import Tools from '../../ts/Tools'

const myform = ref<FormInstance>()

const user = ref(new TbUser())
user.value.username = 'user'
user.value.password = 'user-pwd'


const checkUsername = (rule: any, value: string, cb: any) => {
  console.log(rule)
  if (/^[a-zA-Z][a-zA-Z0-9_]{3,15}$/.test(value)) {
    cb()
  } else {
    cb(new Error('用户名必须是字母开头，且长度是4-15位'))
  }
}

// 校验规则定义
const rules = reactive<FormRules>({
  username: [
    {
      required: true,
      message: '登录名称必须填写',
    },
    {
      validator: checkUsername,
    },
  ],
  password: [
    {
      required: true,
      message: '密码必须填写',
    },
  ],
})

function login(myform: FormInstance) {
  myform.validate((valid) => {
    console.log('表单校验结果：', valid)
    if (valid) {
      user.value.password = Tools.md5(user.value.password)
      ApiService.post('/user/auth/login', user.value, (data: BaseResult) => {
        if (data.success) {
          ElMessageBox.alert(data.message)
        }
      })
    }
  })
}

function reset(myform: FormInstance) {
  myform.resetFields()
}
</script>

<style lang="scss" scoped>
.main {
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  > div {
    transform: translateY(-10vh);
    min-width: 20vw;
  }
}
</style>
