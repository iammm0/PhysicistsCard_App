package com.example.physicistscard.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.physicistscard.businessLogic.interfaces.IAuthService
import com.example.physicistscard.transmissionModels.auth.responses.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authService: IAuthService // 您的业务逻辑服务层
) : ViewModel() {

    // 定义登录状态数据类
    data class LoginState(
        val identifier: String = "",
        val password: String = "",
        val isLoading: Boolean = false,
        val isLoggedIn: Boolean = false,
        val errorMessage: String? = null
    )

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> get() = _loginState

    // 更新用户名/邮箱/手机号
    fun onIdentifierChange(newIdentifier: String) {
        _loginState.value = _loginState.value.copy(identifier = newIdentifier)
    }

    // 更新密码
    fun onPasswordChange(newPassword: String) {
        _loginState.value = _loginState.value.copy(password = newPassword)
    }

    // 执行登录操作
    fun login() {
        viewModelScope.launch {
            _loginState.value = _loginState.value.copy(isLoading = true, errorMessage = null)

            val identifier = _loginState.value.identifier
            val password = _loginState.value.password

            val result = authService.loginUser(identifier, password)
            result.onSuccess { response ->
                handleLoginSuccess(response)
            }.onFailure { exception ->
                handleLoginFailure(exception)
            }
        }
    }

    private fun handleLoginSuccess(response: LoginResponse) {
        if (response.success) {
            _loginState.value = _loginState.value.copy(
                isLoading = false,
                isLoggedIn = true,
                errorMessage = null
            )
            // 您可以在此处保存令牌，或者执行其他操作
        } else {
            _loginState.value = _loginState.value.copy(
                isLoading = false,
                errorMessage = response.errorMessage ?: "登录失败"
            )
        }
    }

    private fun handleLoginFailure(exception: Throwable) {
        _loginState.value = _loginState.value.copy(
            isLoading = false,
            errorMessage = "登录失败：${exception.localizedMessage}"
        )
    }
}