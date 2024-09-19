package aikr.demo.home.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class Project(
  @SerialName("title")
  val title: String,
  @SerialName("username")
  val username: String? = null,
  @SerialName("user_name")
  val user_name: String? = null,
  @SerialName("icon")
  val icon: String,
)
