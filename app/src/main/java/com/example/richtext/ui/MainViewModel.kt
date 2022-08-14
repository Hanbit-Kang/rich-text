package com.example.richtext.ui

import android.content.Context
import android.text.SpannableStringBuilder
import androidx.lifecycle.ViewModel
import com.example.richtext.data.RichText
import com.example.richtext.util.SpannableStringBuilderProvider
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainViewModel: ViewModel() {
    private val _spannableStringBuilder = MutableStateFlow(SpannableStringBuilder())
    var spannableStringBuilder: StateFlow<SpannableStringBuilder> = _spannableStringBuilder.asStateFlow()

    private var fetchJob: Job? = null

    fun fetchText(data: String, context: Context) {
        fetchJob?.cancel()
        fetchJob = CoroutineScope(Dispatchers.IO).launch {
            // <!-- TODO: Replace with loading List<RichText> by calling API
            // Json to List<RichText>
            val jsonObject = JSONObject(data)
            val jsonArray = jsonObject.getJSONArray("richTexts")
            val richTextList = Gson().fromJson(jsonArray.toString(), Array<RichText>::class.java).toList()
            // TODO: Replace with loading List<RichText> by calling API -->

            val spannableStringBuilder = SpannableStringBuilderProvider.getSpannableStringBuilder(richTextList, context)
            _spannableStringBuilder.value = spannableStringBuilder
        }
    }
}