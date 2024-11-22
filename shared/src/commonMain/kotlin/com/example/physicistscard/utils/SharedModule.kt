package com.example.physicistscard.utils

import com.example.physicistscard.apiServices.impl.AuthApiService
import com.example.physicistscard.apiServices.impl.ChatApiService
import com.example.physicistscard.apiServices.impl.CommunityApiService
import com.example.physicistscard.apiServices.impl.FriendshipApiService
import com.example.physicistscard.apiServices.interfaces.IAuthApiService
import com.example.physicistscard.apiServices.interfaces.IChatApiService
import com.example.physicistscard.apiServices.interfaces.ICommunityApiService
import com.example.physicistscard.apiServices.interfaces.IFriendshipApiService
import com.example.physicistscard.apiServices.interfaces.IUserApiService
import com.example.physicistscard.apiServices.impl.UserApiService
import com.example.physicistscard.businessLogic.impl.AuthService
import com.example.physicistscard.businessLogic.impl.ChatService
import com.example.physicistscard.businessLogic.impl.CommunityService
import com.example.physicistscard.businessLogic.impl.FriendshipService
import com.example.physicistscard.businessLogic.interfaces.IAuthService
import com.example.physicistscard.businessLogic.interfaces.IChatService
import com.example.physicistscard.businessLogic.interfaces.ICommunityService
import com.example.physicistscard.businessLogic.interfaces.IFriendshipService
import com.example.physicistscard.businessLogic.interfaces.IUserService
import com.example.physicistscard.businessLogic.impl.UserService
import commonHttpClientConfig
import org.koin.dsl.module

val sharedModule = module {
    // 提供网络客户端，注入到API服务中
    single { commonHttpClientConfig() {} }

    // 提供 TokenManager 的单例
    single { TokenManager(get()) }

    // API 服务
    single<IAuthApiService> { AuthApiService(get()) }
    single<ICommunityApiService> { CommunityApiService(get()) }
    single<IChatApiService> { ChatApiService(get()) }
    single<IFriendshipApiService> { FriendshipApiService(get(), get()) }
    single<IUserApiService> { UserApiService(get()) }

    // 服务层
    single<IAuthService> { AuthService(get(), get()) }
    single<ICommunityService> { CommunityService(get(), get()) }
    single<IChatService> { ChatService(get()) }
    single<IFriendshipService> { FriendshipService(get()) }
    single<IUserService> { UserService(get(), get()) }

}
