# movie-ntf-backen

>> movie-ntf项目服务端代码

## 配置文件
- 请尽量将配置写入application.yaml中方便区分环境和部署测试。
- 8080端口暂时开放给jenkins使用，请具体服务选择其他端口。

## 开发
- 开发时请新建dev分支,分支命名为dev_{dev_name}。
- 请保持main分支log为直线，便于更新。使用rebase合并代码。
- test分支为测试分支，方便部署到服务器端测试，可以随意，但需要保持端口为8082，和部署时一致。

## 构建部署
- 登陆 http://82.157.174.79:8080/
- 账号:movie_ntf_backen 密码: Qtum2021#
- movie-ntf-backen项目默认拉取main分支的代码
- movie-ntf-backen-test默认拉取test分支的代码

****由于服务器只有一台所以可能会出现两个环境端口冲突****
****目前的解决方式是在不同分支的yaml文件中开放不同端口****
