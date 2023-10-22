package com.gbesteban.chat.presentatition

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.burguer.presentation.MainModelView
import com.example.chat.domain.Dog
import com.example.chat.domain.GetDogUseCase
import com.faltenreich.skeletonlayout.Skeleton
import com.gbesteban.chat.R
import com.gbesteban.chat.app.ErrorApp
import com.gbesteban.chat.app.extension.load
import com.gbesteban.chat.data.DogDataRepository
import com.gbesteban.chat.data.remote.ApiMockRemoteDataSource
import com.gbesteban.chat.app.serialization.GsonSerialization
import com.gbesteban.chat.data.local.XmlLocalDataSource
import com.gbesteban.chat.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val viewModel: MainModelView by lazy {
        MainModelView(
            GetDogUseCase(
                DogDataRepository(
                    XmlLocalDataSource(this, GsonSerialization()),
                    ApiMockRemoteDataSource()
                )
            )
        )
    }
    private lateinit var skeleton: Skeleton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupView()
        setupObserver()
        viewModel.loadDog()

    }

    private lateinit var binding: ActivityMainBinding
    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView() {
//skeleton
    }


    private fun setupObserver() {
        val observer = Observer<MainModelView.UiState> { uiModel ->


            uiModel.errorApp?.let {
                showError(it)
            }

            uiModel.dog?.let {
                bindData(it)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showError(error: ErrorApp) {
        Snackbar.make(
            binding.root,
            getString(R.string.message_error),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    //se introduce el texto en la vista.


    private fun bindData(chat: List<Dog>) {
        binding.apply {
            avatar.load(chat[0].url_image)
            titulo.text = chat[0].title
            texto.text = chat[0].subtitle
            hora.text = chat[0].time
            mensajes.text = chat[0].unread_messages
            avatar1.load(chat[1].url_image)
            titulo1.text = chat[1].title
            texto1.text = chat[1].subtitle
            hora1.text = chat[1].time
            mensajes1.text = chat[1].unread_messages
            avatar2.load(chat[2].url_image)
            titulo2.text = chat[2].title
            texto2.text = chat[2].subtitle
            hora2.text = chat[2].time
            mensajes2.text = chat[2].unread_messages

        }
    }
}




