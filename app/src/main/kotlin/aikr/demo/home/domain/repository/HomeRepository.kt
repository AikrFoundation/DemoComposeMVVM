package aikr.demo.home.domain

import androidx.annotation.WorkerThread
import aikr.demo.home.data.model.Project
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
  @WorkerThread
  fun fetchProjectsByScope(
    scope: String,
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ): Flow<List<Project>>
}
