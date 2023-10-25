<template>
  <div>
    <UserInfoComp>
      <div>简易留言板</div>
    </UserInfoComp>
  </div>
  <hr />
  <div>
    <ElButton @click="showAdd">发帖</ElButton>
  </div>
  <hr />
  <div class="container">
    <div v-for="d in list">
      <ElCard>
        <template #header>
          <div class="header">
            <div>
              <ElAvatar :src="d.userInfo.img" fit="cover"></ElAvatar>
            </div>

            <div>
              <div>{{ d.user.username }}</div>
              <div>{{ Tools.formatDate(d.lastupdate) }}</div>
            </div>
          </div>
        </template>

        <div>
          <div>
            <a @click="showDetail(d.umid)" href="javascript:void(0)">
              <ElTag type="info" size="large"> {{ d.title }}</ElTag>
            </a>
          </div>
          <div class="info">
            <span>点赞数：</span><span>{{ d.praiseCount }}</span> <span>点击量：</span><span>{{ d.hits }}</span> <span>评论数：</span><span>{{ d.replyCount }}</span>
          </div>
        </div>
      </ElCard>
    </div>
  </div>

  <PageComp :page="pageInfo" @page-change="query"></PageComp>

  <!-- 发帖的表单对话框 -->
  <div>
    <ElDialog title="发帖" v-model="dialogVisible" top="4%">
      <div>
        <ElForm>
          <ElFormItem>
            <ElInput v-model="addinfo.title" placeholder="请输入标题" />
          </ElFormItem>
          <ElFormItem>
            <!-- 富文本框区域开始 -->
            <div style="border: 1px solid #ccc">
              <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode" />
              <Editor style="height: 500px; overflow-y: hidden" v-model="addinfo.info" :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
            </div>
            <!-- 富文本框区域结束 -->
          </ElFormItem>
          <ElFormItem>
            <ElButton type="primary" @click="">发布</ElButton>
            <ElButton type="danger" @click="dialogVisible = false">关闭</ElButton>
          </ElFormItem>
        </ElForm>
      </div>
    </ElDialog>
  </div>
</template>

<script lang="ts" setup>
import { ElForm, ElFormItem, ElInput, ElCard, ElAvatar, ElTag, ElButton } from 'element-plus'
import UserInfoComp from '../../components/UserInfoComp.vue'
import { ref } from 'vue'
import { ApiService } from '../../ts/ApiService'
import { Tools } from '../../ts/Tools'
import { PageInfo } from '../../ts/BaseResult'
import PageComp from '../../components/PageComp.vue'
import { router } from '../../router'

const addinfo = ref({
  title: '',
  info: '<p></p>',
})

const list = ref([])
const pageInfo = ref(new PageInfo())
const queryInfo = ref({
  info: '',
  orderBy: 1,
})
const dialogVisible = ref(false)

function showAdd() {
  dialogVisible.value = true
}

function query() {
  ApiService.post('/message/queryAll', Tools.concatJson(pageInfo.value, queryInfo.value), (data: any) => {
    if (data.success) {
      pageInfo.value = data.page
      list.value = data.list
    }
  })
}

function showDetail(umid) {
  router.push(`/user/messagedetail/${umid}`)
}

query()

//#region 富文本框的部分
import { shallowRef, onBeforeUnmount } from 'vue'
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const editorRef = shallowRef()

// 内容 HTML
// const valueHtml = ref('<p></p>')

const toolbarConfig = {}
const editorConfig = { placeholder: '请输入...' }

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}

//#endregion
</script>

<style lang="scss" scoped>
.container {
  margin: 1rem auto;
  width: 60%;
  > div {
    margin: 1rem;
  }
  .header {
    display: flex;
    align-items: flex-start;
  }
  .info {
    text-align: right;
    padding: 0.5rem;
    font-size: 0.8rem;
    color: #999;
  }
}
</style>
