package aikr.demo.home.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class ProjectsResponse(
  @SerialName("status")
  val status: String,
  @SerialName("status_code")
  val statusCode: String,
  @SerialName("total_pages")
  val totalPages: String,
  @SerialName("projects")
  val projects: List<Project>,
)
