<template>
  <el-dialog
    v-model="visible"
    title="注册"
    width="400px"
    :close-on-click-modal="false"
  >
    <el-form :model="form" :rules="rules" ref="formRef">
      <el-form-item prop="username" label="用户名">
        <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
      </el-form-item>
      
      <el-form-item prop="password" label="密码">
        <el-input v-model="form.password" type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      
      <el-form-item prop="email" label="邮箱">
        <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      
      <el-form-item prop="phone" label="手机号">
        <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
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
        <el-button type="primary" @click="handleRegister">注册</el-button>
        <el-button link @click="showLogin">已有账号，去登录</el-button>
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
  username: '',
  password: '',
  email: '',
  phone: '',
  role: 'USER'
})

const rules = {
  username: [
    { required: true, message: '请输入用户名', trigger: 'blur' },
    { min: 2, message: '用户名长度不能小于2位', trigger: 'blur' }
  ],
  password: [
    { required: true, message: '请输入密码', trigger: 'blur' },
    { min: 6, message: '密码长度不能小于6位', trigger: 'blur' }
  ],
  email: [
    { 
      pattern: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
      message: '请输入正确的邮箱格式',
      trigger: 'blur'
    }
  ],
  phone: [
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '请输入正确的手机号格式',
      trigger: 'blur'
    }
  ],
  role: [
    { required: true, message: '请选择角色', trigger: 'change' }
  ]
}

const emit = defineEmits(['show-login', 'register-success'])

const handleRegister = async () => {
  if (!formRef.value) return
  
  if (!form.email && !form.phone) {
    ElMessage.error('邮箱和手机号至少填写一个')
    return
  }
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        const res = await axios.post('http://localhost:8080/api/user/register', form)
        ElMessage.success('注册成功')
        visible.value = false
        emit('register-success', res.data)
      } catch (error) {
        ElMessage.error(error.response?.data || '注册失败')
      }
    }
  })
}

const showLogin = () => {
  visible.value = false
  emit('show-login')
}

defineExpose({ visible })
</script> 