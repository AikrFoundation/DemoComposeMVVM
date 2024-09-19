package aikr.demo.core.di

import aikr.demo.core.remote.Sketchub
import aikr.demo.core.remote.SketchubClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideSketchub(retrofit: Retrofit): Sketchub =
    retrofit.create(Sketchub::class.java)

  @Provides
  @Singleton
  fun provideRetrofit(): Retrofit =
    Retrofit.Builder()
      .baseUrl("https://sketchub.in/")
      .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
      .build()

  @Provides
  @Singleton
  fun provideSketchubClient(service: Sketchub): SketchubClient =
    SketchubClient(service)
}
