package loc.example.geoquizcomposeapp.ui

import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import loc.example.geoquizcomposeapp.R
import loc.example.geoquizcomposeapp.model.QuestionNav
import loc.example.geoquizcomposeapp.viewmodel.QuestionViewModel

private const val TAG = "QuestionScreen"

@Composable
fun QuestionScreen(modifier: Modifier = Modifier, model: QuestionViewModel = viewModel()) {
    var question by remember { mutableStateOf(model.questionList[0]) }
    val ctx = LocalContext.current
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Question(question.textResId) // (R.string.question_sample))
        QuestionOptions(onClick = {
            val answerResId = if (it) R.string.answer_correct else R.string.answer_wrong
            Toast.makeText(ctx, answerResId, Toast.LENGTH_LONG).show()
        })
        QuestionNav(onClick = {
            question = when (it) {
                QuestionNav.NEXT -> model.nextQuestion
                QuestionNav.PREV -> model.prevQuestion
            }
            Log.d(TAG, "Question: $question")
        })
    }
}

@Composable
fun Question(@StringRes resId: Int, modifier: Modifier = Modifier) {
    Text(text = stringResource(id = resId))
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
fun QuestionNav(onClick: (QuestionNav) -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        IconButton(onClick = { onClick(QuestionNav.PREV) }) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.nav_prev)
            )
        }
        IconButton(onClick = { onClick(QuestionNav.NEXT) }) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.nav_next)
            )
        }
    }
}

@Preview
@Composable
fun QuestionPreview() {
    QuestionScreen()
}