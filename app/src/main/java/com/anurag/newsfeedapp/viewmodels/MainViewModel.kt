package com.anurag.newsfeedapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anurag.newsfeedapp.data.NewsFeedRepository
import com.anurag.newsfeedapp.data.NewsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsFeedRepository
) : ViewModel() {

    private val _newsResponse = MutableLiveData<NewsResponse>()
    val newsResponse: LiveData<NewsResponse>
        get() = _newsResponse

    init {
        viewModelScope.launch {
            _newsResponse.value = withContext(Dispatchers.IO) {
                repository.getNewsFeed()
            }
        }
    }

}