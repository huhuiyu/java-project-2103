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
              <ElInput v-model="user.password" placeholder="密码"></ElInput>
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
import { FormRules, ElButton, ElCard, ElForm, ElFormItem, ElInput, FormInstance } from 'element-plus'

const myform = ref<FormInstance>()

const user = ref(new TbUser())

// 校验规则定义
const rules = reactive<FormRules>({
  username: [
    {
      required: true,
      message: '登录名称必须填写',
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
  }
}
</style>
