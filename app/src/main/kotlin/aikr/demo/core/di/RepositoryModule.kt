package aikr.demo.core.di

import aikr.demo.home.data.repository.HomeRepositoryImpl
import aikr.demo.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindHomeRepository(
    impl: HomeRepositoryImpl
  ): HomeRepository
}
