package aikr.demo.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun HomeScreen(
  modifier: Modifier = Modifier,
  homeViewModel: HomeViewModel = hiltViewModel(),
) {
  val uiState by homeViewModel.uiState.collectAsStateWithLifecycle()
  val projectList by homeViewModel.projectList.collectAsStateWithLifecycle()

  HomeContent(
    modifier = modifier,
    uiState = uiState,
    projectList = projectList.toImmutableList(),
    fetchNextProjectList = homeViewModel::fetchNextProjectList
  )
}

@Composable
private fun HomeContent(
  modifier: Modifier,
  uiState: HomeUiState,
  projectList: ImmutableList<Project>,
  fetchNextProjectList: () -> Unit,
) {
  Box(modifier = modifier.fillMaxSize()) {
    LazyVerticalGrid(
      columns = GridCells.Fixed(3),
      contentPadding = PaddingValues(8.dp),
    ) {
      itemsIndexed(
        items = projectList,
        key = { _, project -> project.title }
      ) { index, project ->
        if ((index + 15) >= projectList.size && uiState != HomeUiState.Loading) {
          fetchNextProjectList()
        }

        ProjectItem(project = project)
      }
    }
  }
}

@Composable
private fun ProjectItem(
  project: Project,
) {
  
}