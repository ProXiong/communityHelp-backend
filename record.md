# Record
社区友爱互助平台
参考:https://www.archdaily.cn/search/cn/projects?page=3


# 项目架构
- minio存储图片 
- mysql存储原始数据
- day01 
    -  项目架构
      - springboot模板 
      - minio
    - mysql的链接和项目数据表初始化
    - 项目数据架构 mybatisX  +  mybatisPlus生成表和分层MVC
    - 使用maven配置多模块
      - dependencyManagement作为父工程管理依赖版本
        - 踩坑 孙子模块不会继承爷爷模块的<packaging>pom</packaging>标签因为这是pom的private属性是独立性的 , 版本号是public可以继承
      - 将项目分为model模块,common公用模块
      - core模块
        - userCenter