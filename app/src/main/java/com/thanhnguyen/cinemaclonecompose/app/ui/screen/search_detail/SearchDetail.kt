package com.thanhnguyen.cinemaclonecompose.app.ui.screen.search_detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thanhnguyen.cinemaclonecompose.R
import com.thanhnguyen.cinemaclonecompose.common.components.ActionBar
import com.thanhnguyen.cinemaclonecompose.common.components.SingleMovie
import com.thanhnguyen.cinemaclonecompose.app.ui.screen.destinations.MovieDetailScreenDestination
import com.thanhnguyen.cinemaclonecompose.app.ui.theme.*

@Destination
@Composable
fun SearchDetailScreen(nav: DestinationsNavigator, viewModel: SearchDetailViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()){
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {

            val (actionBar, searchBar, body) = createRefs()

            ActionBar(
                title = "Search",
                modifier = Modifier
                    .padding(top = 32.dp)
                    .constrainAs(actionBar) {
                        top.linkTo(parent.top)
                        width = Dimension.matchParent
                    },
                iconBack = painterResource(id = R.drawable.ic_round_left_32),
                iconRight = painterResource(id = R.drawable.ic_filter),
                onBackPressed = {
                    nav.popBackStack()
                },
                onActionPressed = {
                    //setting
                }
            )
            EditableSearchBar(
                modifier = Modifier
                    .constrainAs(searchBar){
                    top.linkTo(actionBar.bottom)
                    width = Dimension.matchParent
                },
                viewModel
            )

            Box(modifier = Modifier
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                .constrainAs(body) {
                    top.linkTo(searchBar.bottom)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                }){

                val state = uiState.value

                when{
                    state.keyword.isEmpty() -> {
                        buildInitialize(state)
                    }

                    !state.data.isNullOrEmpty() -> {
                        buildBody(viewModel, nav, state)

                    }

                    state.keyword.isNotEmpty() && state.data.isNullOrEmpty() -> {
                        buildNoResult(state)
                    }
                }

                if (uiState.value.isLoading) {
                    buildLoading(uiState.value)
                }
            }
        }
    }
}

@Composable
fun buildInitialize(state: SearchDetailState) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(painter = painterResource(id = R.drawable.img_no_result), contentDescription = "")
            Spacer(modifier = Modifier.padding(top = 14.dp))
            Text(text = "Type any thing which you wanna find...", style = CommonStyle.normal(), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun buildLoading(state: SearchDetailState) {
    Box(modifier =
    Modifier
        .fillMaxSize()
        .background(
            color = ColorPrimaryDark50
        )
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun buildBody(
    viewModel: SearchDetailViewModel,
    nav: DestinationsNavigator,
    state: SearchDetailState
) {
    val data = state.data ?: return
    val listState = rememberLazyListState()

    LazyColumn(
        state = listState,
        userScrollEnabled = true,
    ){
        itemsIndexed(data) { index, itemData ->
            if (index == data.size - 1 && !state.isLoadingMore){
                viewModel.loadMoreResults(state.keyword)
            }
            SingleMovie(
                movie = itemData,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable {
                        nav.navigate(
                            MovieDetailScreenDestination(movie = itemData)
                        )
                    }
            )
        }
    }
}

@Composable
fun buildNoResult(state: SearchDetailState) {
    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(painter = painterResource(id = R.drawable.img_no_result), contentDescription = "")
            Spacer(modifier = Modifier.padding(top = 14.dp))
            Text(text = "We are sorry, we can't find the movie :(", style = CommonStyle.bold(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(top = 12.dp))
            Text(text = "Try another keyword to find more magic movies :>", style = CommonStyle.custom(
                fontWeight = FontWeight.W300
            ), textAlign = TextAlign.Center)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EditableSearchBar(
    modifier: Modifier = Modifier,
    viewModel: SearchDetailViewModel
) {
    val textValue = remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    val focusRequest = FocusRequester()

    LaunchedEffect(Unit){
        focusRequest.requestFocus()
    }

    DisposableEffect(Unit){
        onDispose {
          keyboardController?.hide()
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = true, enter = fadeIn()
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentHeight()
            .background(
                color = ColorPrimarySoft,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
//            nav.navigate(Route.SEARCH)
            }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
            ) {
                val (icSearch, edtSearch, vLine, icFilter)  = createRefs()

                Image(
                    painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    modifier = Modifier
                        .constrainAs(icSearch) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }
                        .width(20.dp)
                        .height(20.dp)
                )

                Box(modifier = Modifier
                    .constrainAs(edtSearch){
                        start.linkTo(icSearch.end, margin = 8.dp)
                        top.linkTo(icSearch.top)
                        bottom.linkTo(icSearch.bottom)
                        end.linkTo(vLine.start, margin = 4.dp)
                        width = Dimension.fillToConstraints
                    }
                ){
               BasicTextField(
                   value = textValue.value,
                   onValueChange = {
                       textValue.value = it
                   },
                   modifier = Modifier
                       .fillMaxWidth()
                       .focusRequester(focusRequest),
                   textStyle = TextStyle(
                       color = TextColor.White,
                       fontSize = 16.sp
                   ),
                   singleLine = true,
                   keyboardOptions = KeyboardOptions(
                       autoCorrect = false,
                       imeAction = ImeAction.Search
                   ),
                   keyboardActions = KeyboardActions(
                       onSearch = {
                           keyboardController?.hide()
                           focusManager.clearFocus()
                           viewModel.submitTextSearch(textValue.value.text)
                       }
                   ),
               )
               if (textValue.value.text.isEmpty())
                    Text(text = "Search a movie...", style = TextStyle(
                        color = TextColor.Grey,
                        fontSize = 16.sp
                    ),
                        modifier = Modifier.align(alignment = Alignment.CenterStart))
                }
            }
        }
    }
}
