<template>
  <div style="border: 1px solid #ccc">
    <Toolbar style="border-bottom: 1px solid #ccc" :editor="editorRef" :defaultConfig="toolbarConfig" />
    <Editor @onChange="handleChange" style="min-height: 20vh; max-height: 40vh; overflow-y: hidden" v-model="props.initValue" :defaultConfig="editorConfig" @onCreated="handleCreated" />
  </div>
  <hr />
</template>

<script lang="ts" setup>
import '@wangeditor/editor/dist/css/style.css'

import { onBeforeUnmount, ref, shallowRef, onMounted } from 'vue'
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'

const props = defineProps(['initValue'])
const emits = defineEmits(['value-change'])

const handleChange = (editor: any) => {
  const html = editor.getHtml()
  console.log('内容变化：', html)
  emits('value-change', html)
}

const editorRef = shallowRef()

// 内容 HTML
const valueHtml = ref('<p></p>')

onMounted(() => {
  valueHtml.value = props.initValue
})

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
