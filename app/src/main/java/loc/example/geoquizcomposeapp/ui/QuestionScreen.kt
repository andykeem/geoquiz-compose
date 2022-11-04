package loc.example.geoquizcomposeapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import loc.example.geoquizcomposeapp.R

@Composable
fun QuestionScreen(modifier: Modifier = Modifier) {
    val ctx = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Question(stringResource(R.string.question_1))
        QuestionOptions(onClick = {
            val answerResId = if (it) R.string.answer_correct else R.string.answer_wrong
            Toast.makeText(ctx, answerResId, Toast.LENGTH_LONG).show()
        })
    }
}

@Composable
fun Question(question: String, modifier: Modifier = Modifier) {
    Text(text = question)
}

@Composable
fun QuestionOptions(onClick: (Boolean) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(8.dp)) {
        Button(onClick = { onClick(false) }) {
            Text(stringResource(R.string.answer_false))
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = { onClick(true) }) {
            Text(stringResource(R.string.answer_true))
        }
    }
}

@Composable
fun QuizNavigation(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun QuestionPreview() {
    QuestionScreen()
}