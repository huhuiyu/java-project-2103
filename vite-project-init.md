# vite创建vue3 + typescript带路由和element-plus ui框架项目的简易流程

## 先决条件

- 安装[nodejs](https://nodejs.org)
  - 验证安装的nodejs版本的控制台指令：`node -v`
  - 如果版本过低可以卸载后重新安装
- 确保脚本执行权限
  - 以管理员身份运行`PowerShell`
  - 执行`get-ExecutionPolicy`查看执行脚本的权限
    - 如果显示不是`RemoteSigned`就执行下一步
  - 执行`set-ExecutionPolicy RemoteSigned`修改执行权限

## 创建基本的vite + vue + ts的项目

- `vscode`的必要的插件
  - `Vue Language Features (Volar)`
  - `TypeScript Vue Plugin (Volar)`
  - `Prettier - Code formatter`
  - `SCSS IntelliSense`
  - 如果有`Vetur`插件需要禁用或者卸载
- 执行指令：`npm create vite@latest 项目名称`创建项目
  - 界面是上下方向键选择选项，回车确认
  - 第一个选`Vue`
  - 第二个选`TypeScript`
  - 完成后用`vscode`单独打开创建的项目目录然后继续后面的步骤

## 项目配置和扩展

- 确保是打开的项目所在文件夹
- 执行`npm install`初始化项目（仅需要执行一次）
  - 如果项目莫名的异常也可以删除`node_modules`之后执行`npm install`
- 执行`npm run dev`启动项目开发服务（每次启动开发都需要执行）
- 添加项目其它依赖的功能模块
  - 路由模块：`npm install vue-router@4`
  - pinia状态管理：`npm install pinia`
  - prettier代码格式化：`npm install --save-dev --save-exact prettier`
  - 饿了么ui框架：`npm install element-plus --save`
  - axios网络请求：`npm install axios`
  - qs工具：`npm install qs`
  - qs工具类型定义：`npm i --save--dev @types/qs`
  - spark-md5密码库：`npm install spark-md5`
  - spark-md5密码库类型定义：`npm i --save-dev @types/spark-md5`
  - sass样式：`npm install -D sass`
- 模块的安装可以在`package.json`中验证
- 项目统一格式化配置（非必须）
  - `vscode`工作区配置是在`.vscode/settings.json`文件中
  - 项目的配置是在项目目录中的`.prettierrc.json`文件中
- 项目文件精简和个性化
  - `index.html`修改
    - 去掉`link`标记，也就是拿掉默认的网站图标，或者修改成自己的图标
    - 修改`title`的内容为自己网站标题
  - 删除`public`文件夹
  - 删除`src/assets`文件夹
  - 删除`src/components`文件夹
  - 删除`style.css`文件
  - 修改`main.ts`文件
    - 删除`import './style.css'`导入
  - 替换`src/App.vue`的内容为一行：`<template>网站建设中。。。</template>`
  - 格式化所有文件
  - 控制台执行`npm run dev`启动项目查看效果
- 路由配置
  - 在`src`中创建`view`目录
  - 在`view`目录创建`Home.vue`文件
    - 内容为一行：`<template>网站建设中。。。</template>`
  - 在`view`目录创建`About.vue`文件
    - 内容为一行：`<template>关于黑暗骑士的网站</template>`
  - 在`src`中创建`router`目录
  - 在`router`中创建`index.ts`文件
    - 内容参考文件：[默认路由文件](./index.ts)
  - 修改`src/main.ts`加入路由配置
    - 添加`import { router } from './router/index'`
    - 修改`createApp(App).mount('#app')`这一行为下面的多行
      - `let app = createApp(App)`
      - `app.use(router)`
      - `app.mount('#app')`
  - 修改`src/App.vue`，替换内容为下面的多行来启用路由视图
    - `<template>`
    - `<RouterView></RouterView>`
    - `</template>`
  - 控制的执行`npm run dev`启动项目
    - 默认看到的是`Home.vue`的内容
    - 地址栏加上`/about`看到的是`About.vue`的内容
    - 路由功能到此就配置成功
