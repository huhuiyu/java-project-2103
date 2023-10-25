<template>
  <div style="border: 1px solid #ccc">
    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig" :mode="mode" />
    <Editor style="height: 500px; overflow-y: hidden" v-model="valueHtml" :defaultConfig="editorConfig" :mode="mode" @onCreated="handleCreated" />
  </div>

  <pre>
    {{ valueHtml }}
  </pre>
  <hr>
  <pre v-html="valueHtml"></pre>
</template>

<script lang="ts" setup>
import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import '@wangeditor/editor/dist/css/style.css'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('<p></p>')

const toolbarConfig = {}
const editorConfig = { placeholder: '请输入内容...' }

// 组件销毁时，也及时销毁编辑器
onBeforeUnmount(() => {
  const editor = editorRef.value
  if (editor == null) return
  editor.destroy()
})

const handleCreated = (editor) => {
  editorRef.value = editor // 记录 editor 实例，重要！
}
</script>

<style lang="scss" scoped></style>
