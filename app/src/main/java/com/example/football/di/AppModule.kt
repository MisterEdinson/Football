package com.example.football.di

import android.content.Context
import androidx.room.Room
import com.example.football.data.host.SimpleApi
import com.example.football.data.room.FootballDB
import com.example.football.utils.Constants
import com.example.football.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun BaseUrl() = BASE_URL

    @Provides
    fun logging() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor {
        val request = it.request().newBuilder()
            .addHeader("X-Auth-Token", Constants.TOKEN)
            .build()
        it.proceed(request)
    }.addInterceptor(logging()).build()

    @Provides
    fun gson(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetroFit(): SimpleApi =
        Retrofit.Builder()
            .baseUrl(BaseUrl())
            .addConverterFactory(gson())
            .client(okHttpClient())
            .build()
            .create(SimpleApi::class.java)

    @Provides
    @Singleton

    fun provideGameLigDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            FootballDB::class.java,
            "football_game_lig_data"
        ).build()

    @Provides
    fun provideFootballDao(footballdatabase: FootballDB): FootballLigsDao {
        return footballdatabase.footballDao()
    }
}
