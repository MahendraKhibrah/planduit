package com.pens.planduit.common.components.option

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pens.planduit.common.components.button.PlanDuitCheckBox

@Composable
fun CheckBoxList(
    items : List<String>,
    onChanged : (Int) -> Unit = {},
    initialSelected : Int = -1
)
{
    var selectedIndex by remember {mutableIntStateOf(initialSelected)}
    Column {
        items.forEachIndexed {index, value ->
            Box (
                modifier = Modifier.padding(bottom = 20.dp)
            ){
                PlanDuitCheckBox(
                    text = value,
                    isChecked = selectedIndex == index,
                    onTap = {
                        onChanged(index)
                        selectedIndex = index
                    }
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewCheckBoxList() {
//    CheckBoxList(
//        items = listOf("Option 1", "Option 2", "Option 3")
//    )
//}