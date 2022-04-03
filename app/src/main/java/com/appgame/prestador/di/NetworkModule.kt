package com.appgame.prestador.di

import android.content.Context
import android.content.Intent
import android.util.Log
import com.appgame.prestador.data.localdatasource.UserLocalDataSource
import com.appgame.prestador.data.networkdatasource.service.*
import com.appgame.prestador.utils.CODE_SESSION_EXPIRED
import com.appgame.prestador.utils.SessionExpiredActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        localDataSource: UserLocalDataSource
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(10,TimeUnit.SECONDS)
            .connectTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest = request.newBuilder()
                if (request.url.encodedPath != "auth/login")
                    newRequest.addHeader("x-token", localDataSource.getJWT())
                val response = chain.proceed(newRequest.build())
                val responseString = response.peekBody(Long.MAX_VALUE).string()
                try {
                    val jsonResponse = JSONObject(responseString)
                    if (jsonResponse["code"] == CODE_SESSION_EXPIRED) {
                        val intent = Intent(context,SessionExpiredActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        context.startActivity(intent)
                    }

                } catch (e: Exception) {
                    Log.e("Error", e.message.toString())
                }



                response
            }
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.100.94:8080")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    @Provides
    @Singleton
    fun providesContactsService(retrofit: Retrofit): ContactsService {
        return retrofit.create(ContactsService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideLoanService(retrofit: Retrofit): LoanService{
        return retrofit.create(LoanService::class.java)
    }

    @Provides
    @Singleton
    fun providePaymentService(retrofit: Retrofit): PaymentService{
        return retrofit.create(PaymentService::class.java)
    }
}