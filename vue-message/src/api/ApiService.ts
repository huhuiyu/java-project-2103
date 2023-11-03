// https://api.huhuiyu.top的后端接口封装

const BASE_URL = 'https://api.huhuiyu.top'
const TOKEN_KEY = 'api.huhuiyu.top_token'

const tokenDao = {
  save: (data: any) => {
    if (data && data.token) {
      localStorage.setItem(TOKEN_KEY, data.token)
    }
  },
  load: () => {
    let token = localStorage.getItem(TOKEN_KEY)
    return token ? token : ''
  },
  remove: () => {
    localStorage.removeItem(TOKEN_KEY)
  },
}

import axios from 'axios'
import qs from 'qs'
const ajax = axios.create()

const ApiService = {
  // 发起ajax请求
  send: (url: string, params: any, cb: Function, method: string) => {
    // 处理url
    let _url = BASE_URL + url
    // get请求需要将参数通过地址栏传递
    if (method.toLowerCase() === 'get') {
      _url += '?' + qs.stringify(params, { allowDots: true })
      params = ''
    }
    let promise = ajax({
      url: _url,
      method: method,
      data: params,
      headers: {
        Authorization: tokenDao.load(),
      },
    })

    promise
      .then((resp) => {
        tokenDao.save(resp.data)
        cb(resp.data)
      })
      .catch((err) => {
        console.error('代码错误', err)
        cb({ success: false, message: '程序出了bug' })
      })

    // 绝大多数公司的代码都是直接返回promise，而不用回调
    return promise
  },
  get: (url: string, params: any, cb: Function) => {
    return ApiService.send(url, params, cb, 'get')
  },
  post: (url: string, params: any, cb: Function) => {
    return ApiService.send(url, params, cb, 'post')
  },
  put: (url: string, params: any, cb: Function) => {
    return ApiService.send(url, params, cb, 'put')
  },
  delete: (url: string, params: any, cb: Function) => {
    return ApiService.send(url, params, cb, 'delete')
  },
  patch: (url: string, params: any, cb: Function) => {
    return ApiService.send(url, params, cb, 'patch')
  },
}

export { ApiService as default, ApiService as ApiService }
