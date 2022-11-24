package com.alexa.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexa.superheroes.model.Hero
import com.alexa.superheroes.model.HeroesRepository
import com.alexa.superheroes.ui.theme.Shapes
import com.alexa.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SuperheroApp()
                }
            }
        }
    }
}

@Composable
fun SuperheroApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = { TopAppBar() }) {
        HeroList(heroes = HeroesRepository.heroes)
    }
}

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .size(56.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.h1,
        )
    }
}

@Composable
fun HeroItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        elevation = 2.dp,
        modifier = modifier.padding(8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(id = hero.name),
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = stringResource(id = hero.description),
                    style = MaterialTheme.typography.body1,
                )
            }
            Spacer(Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(8.dp))

            ) {
                Image(
                    painter = painterResource(hero.image),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth,
                )
            }
        }
    }
}

@Composable
fun HeroList(heroes: List<Hero>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(heroes) {
            HeroItem(hero = it)
        }
    }
}

@Preview(showSystemUi = false)
@Composable
fun HeroItemPreview() {
    SuperheroesTheme() {
        HeroItem(
            hero = Hero(
                name = R.string.hero2,
                description = R.string.description2,
                image = R.drawable.android_superhero2
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeroListPreview() {
    SuperheroesTheme() {
        HeroList(heroes = HeroesRepository.heroes)
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    SuperheroesTheme {
        SuperheroApp()
    }
}