package aikr.demo.core.remote

import aikr.demo.home.data.model.ProjectsResponse
import javax.inject.Inject

class SketchubClient @Inject constructor(
  private val service: Sketchub,
) {
  suspend fun fetchProjectsByScope(scope: String, page: Int, apiKey: String): ProjectResponse =
    service.fetchProjectsByScope(
      scope = scope,
      page = page,
      apiKey = apiKey,
    )
}
