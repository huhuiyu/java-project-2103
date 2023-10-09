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
