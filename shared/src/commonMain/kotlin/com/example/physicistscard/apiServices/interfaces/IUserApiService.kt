package com.example.physicistscard.apiServices.interfaces

import com.example.physicistscard.transmissionModels.auth.user.User
import com.example.physicistscard.utils.TokenManager

interface IUserApiService {
    suspend fun getUserInfo(targetUserId: String, tokenManager: TokenManager): User?
}
