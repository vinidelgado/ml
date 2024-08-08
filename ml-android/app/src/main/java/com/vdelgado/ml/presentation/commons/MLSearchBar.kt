package com.vdelgado.ml.presentation.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vdelgado.ml.R
import com.vdelgado.ml.presentation.home.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MLSearchBar(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit,
    onQueryChange: (String) -> Unit,
    state: HomeViewModel.HomeState,
) {
    SearchBar(
        modifier = modifier
            .padding(horizontal = 16.dp),
        placeholder = {
            Text(text = stringResource(R.string.mlsearchbar_placeholder))
        } ,
        trailingIcon = {
            if(state.searchQuery.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.testTag("clear-icon"),
                    onClick = {
                        onQueryChange("")
                    },
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = stringResource(id = R.string.mlsearchbar_clean_icon_button_content_description),
                    )
                }
            }
        },
        query = state.searchQuery,
        onQueryChange = onQueryChange,
        onSearch = {
            onSearchClick()
        },
        active = false,
        onActiveChange = {},
        content = {},
    )
}