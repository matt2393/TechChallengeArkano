package dev.mcallisaya.techchallenge.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.mcallisaya.techchallenge.R
import dev.mcallisaya.techchallenge.domain.model.ErrorCode
import dev.mcallisaya.techchallenge.domain.model.RickMorty

@Composable
fun RickMortyScreen(
    innerPadding: PaddingValues
) {
    val rickMortyViewModel: RickMortyViewModel = hiltViewModel()
    val listCharacters by rickMortyViewModel.characters.collectAsStateWithLifecycle()
    val status by rickMortyViewModel.status.collectAsStateWithLifecycle()
    val errorCode by rickMortyViewModel.errorCode.collectAsStateWithLifecycle()
    RickMortyContent(
        listCharacters = listCharacters,
        innerPadding = innerPadding,
        loadMore = {
            rickMortyViewModel.getCharacters()
        }
    )

    if (status == RickMortyViewModel.Status.LOADING && listCharacters.isEmpty()) {
        LoadingDialog()
    }
    if (status == RickMortyViewModel.Status.ERROR) {
        ErrorDialog(
            errorCode = errorCode ?: ErrorCode.UNKNOWN,
            onDismissRequest = {
                rickMortyViewModel.resetStatus()
            },
            retry = {
                rickMortyViewModel.resetStatus()
                rickMortyViewModel.getCharacters()
            }
        )
    }

    LaunchedEffect(Unit) {
        rickMortyViewModel.getCharacters()
    }

}

@Composable
private fun RickMortyContent(
    listCharacters: List<RickMorty.Result> = listOf(),
    innerPadding: PaddingValues = PaddingValues(0.dp),
    loadMore: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .background(Color.White),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 80.dp, horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(
                items = listCharacters,
                key = { it.id ?: 0 }
            ) {
                RickMortyItem(
                    character = it,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                if (it.id == listCharacters.lastOrNull()?.id) {
                    loadMore.invoke()
                }
            }
        }
        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color.DarkGray
            ),
            shape = RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            Text(
                text = stringResource(R.string.rick_and_morty_characters),
                color = Color.LightGray,
                fontSize = 21.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 20.dp)

            )
        }

    }
}

@Composable
fun RickMortyItem(
    character: RickMorty.Result,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ImageCustom(
                url = character.image ?: "",
                modifier = Modifier.fillMaxSize()
            )
            Text(
                text = character.name ?: "",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x74000000))
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 20.dp, vertical = 15.dp)
            )
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(15.dp)
                    .background(Color(0x74000000), RoundedCornerShape(20.dp))
                    .padding(horizontal = 10.dp, vertical = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(7.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .background(
                            color = when (character.status?.lowercase()) {
                                "alive" -> Color.Green
                                "dead" -> Color.Red
                                else -> Color.LightGray
                            },
                            shape = RoundedCornerShape(10.dp)
                        )
                )
                Text(
                    text = character.status ?: "",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RickMortyPreview() {
    RickMortyContent()
}

@Preview(showBackground = true)
@Composable
fun RickMortyItemPreview() {
    RickMortyItem(
        modifier = Modifier.size(200.dp),
        character = RickMorty.Result(
            name = "Rick Sanchez",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            status = "Alive"
        )
    )
}