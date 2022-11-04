package loc.example.geoquizcomposeapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import loc.example.geoquizcomposeapp.R
import loc.example.geoquizcomposeapp.model.Question

private const val TAG = "QuestionViewModel"

class QuestionViewModel : ViewModel() {
    private val index = mutableStateOf(0)
    val questionList = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private val prevIndex: Int get() = questionList.size % --index.value
    private val nextIndex: Int get() = questionList.size % ++index.value
    val prevQuestion: Question
        get() {
            Log.d(TAG, "prevIndex: $prevIndex")
            return questionList[prevIndex]
        }
    val nextQuestion: Question
        get() {
            Log.d(TAG, "nextIndex: $nextIndex")
            return questionList[nextIndex]
        }
}