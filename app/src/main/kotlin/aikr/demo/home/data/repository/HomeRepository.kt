package aikr.demo.home.data

import androidx.annotation.WorkerThread
import aikr.demo.core.remote.SketchubClient
import aikr.demo.home.domain.repository.HomeRepository
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
  private val client: SketchubClient,
) : HomeRepository {
  @WorkerThread
  override fun fetchProjectsByScope(
    scope: String,
    page: Int,
    onStart: () -> Unit,
    onComplete: () -> Unit,
    onError: (String?) -> Unit,
  ) = flow {
    val response = client.fetchProjectsByScope(scope = scope, page = page)
    response.suspendOnSuccess {
      emit(data.projects)
    }.onFailure {
      onFailure(message())
    }
  }.onStart { onStart() }.onCompletion { onComplete() }
}
