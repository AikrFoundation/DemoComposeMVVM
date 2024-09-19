package aikr.demo.core.remote

import aikr.demo.home.data.model.ProjectsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Sketchub {
  @POST("api/v3/get_project_list.php")
  suspend fun fetchProjectsByScope(
    @Field("scope") scope: String = "recent",
    @Field("page_number") page: Int = 1,
    @Field("api_key") apiKey: String
  ): ProjectsResponse
}
