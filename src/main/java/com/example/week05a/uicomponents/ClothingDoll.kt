package com.example.week05a.uicomponents

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.week05a.R

data class ClothingItem(val name: String, val imageResId: Int)

@Composable
fun ClothingDoll(modifier: Modifier = Modifier) {
    val orientation = LocalConfiguration.current.orientation

    // 의상 아이템 리스트 (Body는 항상 기본 이미지로 포함)
    val clothes = listOf(
        ClothingItem("Arms", R.drawable.arms),
        ClothingItem("Ears", R.drawable.ears),
        ClothingItem("Eyebrows", R.drawable.eyebrows),
        ClothingItem("Eyes", R.drawable.eyes),
        ClothingItem("Glasses", R.drawable.glasses),
        ClothingItem("Hat", R.drawable.hat),
        ClothingItem("Mouth", R.drawable.mouth),
        ClothingItem("Mustache", R.drawable.mustache),
        ClothingItem("Nose", R.drawable.nose),
        ClothingItem("Shoes", R.drawable.shoes)
    )

    // 선택된 아이템을 저장
    val selectedItems = remember { mutableStateListOf<ClothingItem>() }

    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Text("202111304 서범경")
                    ClothingImage(image = painterResource(id = R.drawable.body))

                    // 선택된 아이템들 덧입히기
                    selectedItems.forEach { item ->
                        ClothingImage(image = painterResource(id = item.imageResId))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 의상 목록을 2열로 보여주기
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(clothes.size) { index ->
                    val item = clothes[index]
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 8.dp) // 각 항목 간 간격 설정
                    ) {
                        Checkbox(
                            checked = selectedItems.contains(item),
                            onCheckedChange = {
                                if (it) {
                                    selectedItems.add(item)
                                } else {
                                    selectedItems.remove(item)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp)) // 텍스트와 체크박스 간 간격
                        Text(text = item.name)
                    }
                }
            }
        }
    } else {
        // Landscape mode layout
        Row {
            Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                Box {
                    Text("202111304 서범경")
                    ClothingImage(image = painterResource(id = R.drawable.body))

                    // 선택된 아이템들 덧입히기
                    selectedItems.forEach { item ->
                        ClothingImage(image = painterResource(id = item.imageResId))
                    }
                }
            }

            Column {
                // 의상 목록을 2열로 보여주기
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(clothes.size) { index ->
                        val item = clothes[index]
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp) // 각 항목 간 간격 설정
                        ) {
                            Checkbox(
                                checked = selectedItems.contains(item),
                                onCheckedChange = {
                                    if (it) {
                                        selectedItems.add(item)
                                    } else {
                                        selectedItems.remove(item)
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp)) // 텍스트와 체크박스 간 간격
                            Text(text = item.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ClothingImage(image: Painter) {
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .size(150.dp) // 이미지 크기 조정
            .padding(5.dp) // 이미지 간의 간격을 줄 수 있습니다.
    )
}

@Preview
@Composable
fun ClothingDollPreview() {
    ClothingDoll()
}
