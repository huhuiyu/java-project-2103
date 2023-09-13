// ajax请求封装类

import axios from 'axios'
import { ElMessageBox } from 'element-plus'

/**
 * ajax配置信息对象
 */
class AjaxConfig {
  method: string = 'POST'
  url: string = ''
  param: any = {}
  cb: any = () => {}
  handleMessage: boolean = false
  returnPromise: boolean = false
  headers: any = {}
  paramObj: any = {}
  constructor() {}

  static buildConfig(url: string, param: any, cb: any, method: string, handleMessage: boolean, returnPromise = false): AjaxConfig {
    let config = new AjaxConfig()
    config.url = url
    config.param = param
    config.cb = cb
    config.method = method
    config.handleMessage = handleMessage
    config.returnPromise = returnPromise
    return config
  }

  static buildPostConfig(url: string, param: any, cb: any, handleMessage = false): AjaxConfig {
    return this.buildConfig(url, param, cb, 'POST', handleMessage)
  }

  static buildGetConfig(url: string, param: any, cb: any, handleMessage = false): AjaxConfig {
    return this.buildConfig(url, param, cb, 'GET', handleMessage)
  }

  static buildPostConfigPromise(url: string, param: any, cb: any, handleMessage = false): AjaxConfig {
    let config = this.buildPostConfig(url, param, cb, handleMessage)
    config.returnPromise = true
    return config
  }

  static buildGetConfigPromise(url: string, param: any, cb: any, handleMessage = false): AjaxConfig {
    let config = this.buildGetConfig(url, param, cb, handleMessage)
    config.returnPromise = true
    return config
  }
}

const MAX_FILE_SIZE = 5 * 1024 * 1024
const MAX_FILE_SIZE_MESSAGE = { code: 500, success: false, message: '上传文件大小不能超过' + MAX_FILE_SIZE / 1024 / 1024 + 'MB' }

class Ajax {
  // axios实例
  axiosInstance: any
  // 文件上传的axios实例
  axiosFileInstance: any
  // 发起请求前的处理函数，蚕食是AajxConfig
  before: any
  // 应答回来之后的处理函数，参数是应答服务端数据
  after: any
  constructor() {
    this.axiosInstance = axios.create()
    this.axiosFileInstance = axios.create()
  }

  upload(config: AjaxConfig, file: File) {
    // 保留原始参数对象
    config.paramObj = JSON.parse(JSON.stringify(config.param))
    let instance = this
    console.log('请求参数：', config)
    // 上传大小限制
    if (file.size > MAX_FILE_SIZE) {
      config.cb(MAX_FILE_SIZE_MESSAGE)
      return
    }
    // 前置处理
    instance.before(config)
    // 处理参数
    let formdata = new FormData()
    formdata.append('file', file)
    for (let key in config.paramObj) {
      formdata.append(key, config.paramObj[key])
    }
    // 处理请求类型
    config.method = 'POST'
    config.headers['Content-Type'] = 'multipart/form-data'
    let promise = this.axiosFileInstance({
      url: config.url,
      data: formdata,
      method: config.method,
      headers: config.headers,
    })
    if (config.returnPromise) {
      return promise
    }
    promise
      .then((resp: any) => {
        console.log('请求结果====>', resp)
        // 应答拦截处理
        instance.after(resp.data)
        // 错误自动弹框
        if (!config.handleMessage && !resp.data.success) {
          ElMessageBox.alert(resp.data.message, '错误', { type: 'error' })
        }
        // 应答回调处理
        config.cb(resp.data)
      })
      .catch((err: any) => {
        console.error('请求错误====>', err)
        config.cb({ code: 500, success: false, message: '访问数据失败！', error: err })
      })
  }

  send(config: AjaxConfig) {
    let instance = this
    console.log('请求参数：', config)
    // 前置处理
    instance.before(config)
    let promise = this.axiosInstance({
      url: config.url,
      data: config.param,
      method: config.method,
      headers: config.headers,
    })
    if (config.returnPromise) {
      return promise
    }
    promise
      .then((resp: any) => {
        console.log('请求结果====>', resp)
        // 应答拦截处理
        instance.after(resp.data)
        // 错误自动弹框
        if (!config.handleMessage && !resp.data.success) {
          ElMessageBox.alert(resp.data.message, '错误', { type: 'error' })
        }
        // 应答回调处理
        config.cb(resp.data)
      })
      .catch((err: any) => {
        console.error('请求错误====>', err)
        config.cb({ code: 500, success: false, message: '访问数据失败！', error: err })
      })
  }
}

export { Ajax as default, AjaxConfig as AjaxConfig }