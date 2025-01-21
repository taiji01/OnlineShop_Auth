# 项目介绍

这是 [全流程展示：使用 Cursor 构建简单毕设项目的全栈用户认证功能](https://github.com) 视频中的代码文件。

## 环境配置

- **Node.js 版本**: v22.11.0
- **MySQL 版本**: MySQL 8.0.40 for Win64 on x86_64 (MySQL Community Server - GPL)
- 其他配置信息请查看 `pom.xml` 文件。

## 使用说明

1. 使用 **IntelliJ IDEA** 打开项目文件。
2. 首先通过 **Maven** 下载后端依赖。
3. 在命令行中使用 `npm install`（按 `Alt+F12` 调出命令行）下载前端依赖。
4. 下载完成后：
   - 启动后端服务。
   - 使用 `npm run dev` 启动前端。
5. 可以使用 **Navicat** 进行数据库的可视化查看。
6.有问题可以在github或者b站中询问

## 提示词

1. 首先不生成代码，基本的项目结构已经构建完毕了，你只需要在这基础上进行生成代码就可以了，你明白了吗
2. 我要开发在线商城系统：
首先使用vue.js构建首页，首页的标题栏包含首页，我的购物车，我的订单，左上角是logo，右上角是登录注册。
首页和购物车已经订单的功能先不实现，先展示一下界面。
3. 接下来使用springboot+mybatis作为后端框架，使用mysql作为数据库，使用restful api进行前后端的交互。不使用JWT安全认证。
后端代码都放在backend文件夹中。
 首先实现登录注册功能： 首先使用mybatis和springboot建立一个user表（属性有用户id，用户名，密码，邮箱，手机号，角色）。默认导入一个管理员账户， 当使用管理员账户登录时会进入管理员界面，这个暂时不开发。 点击登录按钮，出现登录界面输入手机号或者邮箱，然后输入密码，点击登录（系统会在user表中查找相关的用户）。如果没有账户，则点击注册，输入用户名，密码，手机号或者邮箱（系统会在user表中查找是否有相同的手机号或者邮箱，如果有返回已经注册了，手机号或者邮箱选择一个输入即可），然后点击注册，注册成功（系统会将用户信息添加到user表中）。当登录成功后，右上角的登录注册按钮就会消失，会换成用户的用户名。输入手机号或者邮箱后，系统会判断格式是否正确。
注册时可选择角色。登录时也可以选择角色
4. 将一些组件放入到components文件夹中
