<template>
  <el-dialog
    v-model="visible"
    title="登录"
    width="400px"
    :close-on-click-modal="false"
  >
    <el-form :model="form" :rules="rules" ref="formRef">
      <el-form-item prop="emailOrPhone" label="邮箱/手机号">
        <el-input v-model="form.emailOrPhone" placeholder="请输入邮箱或手机号"></el-input>
      </el-form-item>
      
      <el-form-item prop="password" label="密码">
        <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      
      <el-form-item prop="role" label="角色">
        <el-select v-model="form.role" placeholder="请选择角色">
          <el-option label="用户" value="USER"></el-option>
          <el-option label="管理员" value="ADMIN"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="handleLogin">登录</el-button>
        <el-button link @click="showRegister">注册账号</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

const visible = ref(false)
const formRef = ref()

const form = reactive({
  emailOrPhone: '',
  password: '',
  role: 'USER'
})

const rules = {
  emailOrPhone: [
    { required: true, message: '请输入邮箱或手机号', trigger: 'blur' },
    { 
      validator: (rule, value, callback) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
        const phoneRegex = /^1[3-9]\d{9}$/
        if (!emailRegex.test(value) && !phoneRegex.test(value)) {
          callback(new Error('请输入正确的邮箱或手机号'))
        } else {
          callback()
        }
      },
      trigger: 'blur'
    }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ],
  role: [
    { required: true, message: '请选择角色', trigger: 'change' }
  ]
}

const emit = defineEmits(['show-register', 'login-success'])

const handleLogin = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await axios.post('http://localhost:8080/api/user/login', form)
        ElMessage.success('登录成功')
        visible.value = false
        emit('login-success', res.data)
      } catch (error) {
        ElMessage.error(error.response?.data || '登录失败')
      }
    }
  })
}

const showRegister = () => {
  visible.value = false
  emit('show-register')
}

defineExpose({ visible })
</script> 