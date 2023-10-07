# SpringBoot2说明

- 项目结构
  - src/main是源码文件夹
    - java是代码目录，放包和类
    - resources目录是配置和其他相关文件目录
  - src/test是测试代码文件夹(这里面的类会被打包忽略)
- 几个特别的文件说明
  - logback.xml是日志配置文件
  - application.yml是springboot的默认主配置文件
- mybatis配置
  - 实体类和数据表是一一对应关系
  - dao都是接口，带@Mapper注解即可
  - dao的方法由和dao同包同名的xml文件配置,xml的id对应dao里面方法名称
- 注意事项
  - mybatis的驼峰转下划线格式是在application.yml中配置
  - mybatis的实体类引用不需要带包名称也是在application.yml中配置
  - 应用所在端口在application.yml中配置
  - 接口路径由controller定义
  - spring接管容器，所以所有核心业务类都无需通过new创建
- 开发流程
  - 编写实体类
  - 编写dao和dao对应的mapper.xml文件
  - 编写service和service的实现类
  - 编写controller类
  - 通过controller类定义的地址调用接口
  