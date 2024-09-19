package aikr.demo.core.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aikr.demo.home.data.model.Project
import aikr.demo.home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

sealed interface HomeUiState {
  data object Idle : HomeUiState
  data object Loading : HomeUiState
  data class Error(val message: String?) : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val homeRepository: HomeRepository,
) : ViewModel() {
  private val _uiState = MutableStateFlow(HomeUiState.Loading)
  val uiState: StateFlow get() = _uiState

  private val fetchingIndex = MutableStateFlow(0)
  val projectsList: StateFlow<List<Project>> =
    fetchingIndex.flatMapLatest { page ->
      homeRepository.fetchProjectsByScope(
        scope = "recent",
        pageNumber = page,
        onStart = { _uiState.emit(HomeUiState.Loading) },
        onComplete = { _uiState.emit(HomeUiState.Idle) },
        onError = { _uiState.emit(HomeUiState.Error(it)) }
      )
    }.stateIn(
      scope = viewModelScope,
      started = SharingStarted.WhileSubscribed(5_000),
      initialValue = emptyList(),
    )

  fun fetchNextProjectList() {
    if (uiState.value != HomeUiState.Loading) {
      fetchingIndex.value++
    }
  }
}
