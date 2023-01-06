package com.example.dogprofilepage

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ProfilePage(){
    Card(elevation = 0.dp, backgroundColor = Color.Transparent,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp)))
    {
        ProfilePageContent()
    }
}



@Composable
fun ProfilePageContent(){
    BoxWithConstraints() {
        val constraints = if (minWidth < 600.dp) {
            portraitConstraints(margin = 16.dp)
        } else {
            landscapeConstraints(margin = 16.dp)
        }

        ConstraintLayout(constraints) {

            Image(
                painter = painterResource(id = R.drawable.dog),
                contentDescription = "Dog",
                modifier = Modifier
                    .layoutId("image")
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, color = Color.Black, shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "Golden Retriever",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("nameText")

            )
            Text(
                text = "Brazil",
                modifier = Modifier.layoutId("countryText")
            )

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .layoutId("rowStats")
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                ProfileStats(count = "150", title = "Followers")
                ProfileStats(count = "100", title = "Following")
                ProfileStats(count = "30", title = "Posts")
            }

            Button(
                onClick = {},
                modifier = Modifier.layoutId("buttonFollow")
            ) {
                Text(text = "Follow User")
            }
            Button(
                onClick = {},
                modifier = Modifier.layoutId("buttonDM")
            ) {
                Text(text = "Direct Message")
            }
        }
    }
}


private fun landscapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet{
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonDM = createRefFor("buttonDM")

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin*5)
        }

        constrain(nameText){
            top.linkTo(image.bottom, margin = margin)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }

        constrain(countryText){
            top.linkTo(nameText.bottom)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }

        constrain(rowStats){
            top.linkTo(parent.top)
            bottom.linkTo(buttonFollow.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end, margin = margin)
        }

        constrain(buttonFollow){
            top.linkTo(rowStats.bottom, margin = margin)
            bottom.linkTo(parent.bottom)
            start.linkTo(rowStats.start)
            end.linkTo(buttonDM.start)
            width = Dimension.wrapContent
        }

        constrain(buttonDM){
            top.linkTo(rowStats.bottom, margin = margin)
            bottom.linkTo(parent.bottom)
            start.linkTo(buttonFollow.end)
            end.linkTo(rowStats.end)
            width = Dimension.wrapContent
        }

    }
}

private fun portraitConstraints(margin: Dp) : ConstraintSet{
    return ConstraintSet{
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val buttonFollow = createRefFor("buttonFollow")
        val buttonDM = createRefFor("buttonDM")

        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(nameText){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(countryText){
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(rowStats){
            top.linkTo(countryText.bottom)
        }

        constrain(buttonFollow){
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(buttonDM.start)
            width = Dimension.wrapContent
        }

        constrain(buttonDM){
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }

    }
}


@Composable
fun ProfileStats(count: String, title: String){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview(){
    ProfilePage()
}