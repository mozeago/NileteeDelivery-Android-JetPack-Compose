package com.mozeago.nileteedelivery

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mozeago.nileteedelivery.ui.theme.NileteeDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NileteeDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    DefaultPreview()
                }
            }
        }
    }
}

data class OrderList(
    val orderItemImage: String,
    val businessName: String,
    val location: String,
    val orderStatus: Boolean,
    val orderAmount: Number,
)

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}


@Composable
fun OrderLIst(order: OrderList) {
    NileteeDeliveryTheme {
        val isExpanded by remember {
            mutableStateOf(false)
        }

        Card(
            modifier = Modifier
                .padding(4.dp)

        ) {
            Row(modifier = Modifier.clickable { isExpanded != isExpanded }) {
                Image(
                    painter = painterResource(R.drawable.pizza_32),
                    contentDescription = "Order description",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.secondary, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = order.businessName,
                        color = MaterialTheme.colors.secondaryVariant,
                        style = MaterialTheme.typography.subtitle2
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = order.location,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = order.orderStatus.toString(),
                        style = MaterialTheme.typography.body2
                    )

                }
                Spacer(modifier = Modifier.width(8.dp))
                Row {
                    Column() {
                        Row() {
                            Text(
                                text = "KES  ",
                                style = MaterialTheme.typography.body2,
                            )
                            Text(
                                text = order.orderAmount.toString(),
                                style = MaterialTheme.typography.body2
                            )
                            Text(
                                text = "/=",
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }

                }

            }
        }

    }
}

@Composable
fun Order(orders: List<OrderList>) {
    LazyColumn {
        items(orders) { order ->
            OrderLIst(order = order)
        }
    }
}

//@Composable
//fun TopAppBar(
//    title: @Composable() () -> Unit,
//    color: Color = MaterialTheme.colors.primary,
//    navigationIcon: @Composable() (() -> Unit)?null
//)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NileteeDeliveryTheme {
        Order(orders = SampleData.OrderListsSample)
//        OrderLIst(
//            order = OrderList(
//                "",
//                "AL-Tamuu Hotel",
//                "Lenana Road Nairobi",
//                false,
//                650,
//
//                )
//        )
    }
}