package dev.mcallisaya.techchallenge.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mcallisaya.techchallenge.domain.model.ErrorCode
import dev.mcallisaya.techchallenge.domain.model.RickMorty
import dev.mcallisaya.techchallenge.domain.use_case.GetRickMortyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

@HiltViewModel
class RickMortyViewModel @Inject constructor(
    private val rickMortyUseCase: GetRickMortyUseCase
): ViewModel() {
    private var page = 1
    private var isLast = false

    enum class Status {
        NONE,
        LOADING,
        SUCCESS,
        ERROR
    }

    private val mutex = Mutex()

    private val _status = MutableStateFlow(Status.NONE)
    val status = _status.asStateFlow()

    private val _characters = MutableStateFlow<List<RickMorty.Result>>(listOf())
    val characters = _characters.asStateFlow()

    private val _errorCode = MutableStateFlow<ErrorCode?>(null)
    val errorCode = _errorCode.asStateFlow()

    fun getCharacters() {
        if (isLast || status.value == Status.LOADING) {
            _status.value = Status.NONE
            return
        }
        _status.value = Status.LOADING
        viewModelScope.launch {
            mutex.withLock {
                rickMortyUseCase.invoke(page)
                    .catch {
                        Log.e("rickMortyViewModel", it.toString())
                        _errorCode.value = null
                        _status.value = Status.ERROR
                    }
                    .collect {
                        if (it.codeError == null) {
                            page++
                            _status.value = Status.SUCCESS
                            _characters.value = _characters.value + it.results
                            _errorCode.value = null
                            if (it.pages < page) {
                                isLast = true
                            }
                        } else {
                            _status.value = Status.ERROR
                            _errorCode.value = it.codeError
                        }
                    }
            }
        }
    }

    fun resetStatus() {
        _status.value = Status.NONE
    }
}