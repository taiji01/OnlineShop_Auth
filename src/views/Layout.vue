<template>
  <div class="layout">
    <el-container>
      <el-header>
        <div class="header-content">
          <div class="left">
            <router-link to="/">
              <img src="/logo.png" alt="Logo" class="logo">
            </router-link>
          </div>
          
          <div class="center">
            <el-menu mode="horizontal" router>
              <el-menu-item index="/">首页</el-menu-item>
              <el-menu-item index="/cart">购物车</el-menu-item>
              <el-menu-item index="/orders">我的订单</el-menu-item>
            </el-menu>
          </div>
          
          <div class="right">
            <template v-if="!currentUser">
              <el-button type="primary" plain @click="showLoginDialog">登录</el-button>
              <el-button type="primary" @click="showRegisterDialog">注册</el-button>
            </template>
            <template v-else>
              <span>欢迎，{{ currentUser.username }}</span>
              <el-button link @click="handleLogout">退出</el-button>
            </template>
          </div>
        </div>
      </el-header>
      
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
  
  <Login ref="loginRef" @show-register="showRegisterDialog" @login-success="handleLoginSuccess" />
  <Register ref="registerRef" @show-login="showLoginDialog" @register-success="handleRegisterSuccess" />
</template>

<script setup>
import { ref } from 'vue'
import Login from '../components/user/Login.vue'
import Register from '../components/user/Register.vue'

const currentUser = ref(null)
const loginRef = ref()
const registerRef = ref()

const showLoginDialog = () => {
  loginRef.value.visible = true
}

const showRegisterDialog = () => {
  registerRef.value.visible = true
}

const handleLoginSuccess = (user) => {
  currentUser.value = user
}

const handleRegisterSuccess = () => {
  showLoginDialog()
}

const handleLogout = () => {
  currentUser.value = null
}
</script>

<style scoped>
.layout {
  min-height: 100vh;
}

.el-header {
  padding: 0;
  background-color: white;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.logo {
  height: 40px;
  margin-right: 20px;
}

.left {
  display: flex;
  align-items: center;
}

.center {
  flex-grow: 1;
}

.right {
  display: flex;
  gap: 10px;
}

.el-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

:deep(.el-menu) {
  border-bottom: none;
}

:deep(.el-menu-item) {
  font-size: 16px;
}
</style> 