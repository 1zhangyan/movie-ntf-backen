# movie-ntf-backen

>> movie-ntf项目服务端代码

## 配置文件
- 请尽量将配置写入application.yaml中方便区分环境和部署测试。
- 8080端口暂时开放给jenkins使用，具体服务端口为8081。

## 开发
- 开发时请新建dev分支,分支命名为dev_{dev_name}。
- 请保持main分支log为直线，便于更新。使用rebase合并代码。
- test分支为测试分支，方便部署到服务器端测试，可以随意。test的端口配置为8081，容器映射为8082。

## 构建部署
支持Jenkins自动化部署
- 登陆 http://82.157.174.79:8080/
- 账号:movie_ntf_backen 密码: Qtum2021#
- movie-ntf-backen 项目默认拉取main分支的代码,构建部署即可，默认端口8081，接口文档: http://82.157.174.79:8081/swagger-ui.html
- movie-ntf-backen-test默认拉取test分支的代码 ，构建部署即可，默认端口8082 接口文档 http://82.157.174.79:8082/swagger-ui.html

****由于Github网不好会经常导致构建失败****