import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.insync.R
import com.example.insync.presentation.constants.OnboardData
import com.example.insync.presentation.constants.onboardData
import com.example.insync.presentation.navigation.NavDestination
import com.example.insync.presentation.ui.theme.AppTypography
import com.example.insync.presentation.ui.theme.InsyncTheme
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(
        initialPage = 0,
    ) {
        3 // Number of pages
    }
    val scope = rememberCoroutineScope()

    InsyncTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    // Horizontal Pager for onboarding pages
                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.weight(1f)
                    ) { page ->
                        OnboardingPageContent(
                            data = onboardData[page],
                        )
                    }

                    // Pager Indicators and Navigation Controls
                    PagerControls(
                        pagerState = pagerState,
                        modifier = Modifier,
                        onSkip = { /* Navigate to home screen */ },
                        onNext = {
                            scope.launch {
                                if (pagerState.currentPage < pagerState.pageCount - 1) {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                } else {
                                    // Navigate to home screen
                                    navController.navigate(NavDestination.Home.route);
                                }
                            }
                        }
                    )

                    // bottom spacer
                    Spacer(
                        modifier = Modifier
                            .fillMaxHeight(0.1f)
                    )
                }
            }
        )
    }
}

@Composable
fun OnboardingPageContent(
    data: OnboardData
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)), // Shape for the card
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {
                    Image(
                        painter = rememberAsyncImagePainter(
                            model = data.imageResId
                            ),
                        contentDescription = "Onboarding GIF",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
                            .padding(16.dp)
                    )
            }

            Spacer(
                modifier = Modifier
                    .fillMaxHeight(0.1f)
            )


            // text contents
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = data.title,
                    style = AppTypography.bodyLarge,
                )
                Spacer(
                    modifier = Modifier.
                        height(16.dp)
                )
                Text(
                    text = data.description,
                    style = AppTypography.labelSmall
                )
            }
        }
    }
}


@Composable
fun PagerControls(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {

    val skipButtonVisible = pagerState.currentPage != onboardData.size - 1
    val nextButtonWeight by animateFloatAsState(
        targetValue = if (skipButtonVisible) 1f else 2f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = ""
    )


    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1f)
            .padding(vertical = 8.dp, horizontal = 16.dp)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Skip Button
        if(pagerState.currentPage != onboardData.size - 1){
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(end=16.dp)
                ,
            ) {
                TextButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 2.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )
                    ,
                    onClick = onSkip

                ) {
                    Text(
                        text = "Skip",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            letterSpacing = 0.5.sp
                        )
                    )
                }
            }
        }


        // Next Button
        TextButton(
            modifier = Modifier
                .weight(nextButtonWeight)
                .clip(RoundedCornerShape(30))
                .background(Color.Black)
                ,
            onClick = onNext
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp)
                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Next",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        letterSpacing = 0.5.sp
                    ),
                )
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(30))
                        .background(Color.White)
                        ,
                    contentAlignment = Alignment.Center
                ){
                    Image(
                        modifier = Modifier.fillMaxSize(0.6f),
                        painter = painterResource(R.drawable.arrow_right),
                        contentDescription = "Next Icon",
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun OnboardingScreenPreview() {
//    InsyncTheme {
//        OnboardingScreen(
//            navController = NavHostController
//        )
//    }
//}