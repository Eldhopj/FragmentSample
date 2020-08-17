package ownmanager.`in`.fragmentruntimesample.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val text = MutableLiveData<CharSequence>()
    fun getText(): LiveData<CharSequence> {
        return text
    }

    fun setText(input: CharSequence) {
        text.value = input
    }
}