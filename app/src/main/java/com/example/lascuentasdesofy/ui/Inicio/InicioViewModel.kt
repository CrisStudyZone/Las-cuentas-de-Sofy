package com.example.lascuentasdesofy.ui.Inicio

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.lascuentasdesofy.R

class InicioViewModel(application: Application) : AndroidViewModel(application) {

    //LiveData para las imagenes cargadas con Glide
    // El guion bajo al principio sirve como una indicación visual rápida de que la variable está destinada a ser utilizada solo internamente dentro de la clase, y no debería ser accedida directamente desde fuera.

    private val _imagenSuma = MutableLiveData<Drawable>()
    val imagenSuma: LiveData<Drawable> = _imagenSuma

    private val _imagenResta = MutableLiveData<Drawable>()
    val imagenResta: LiveData<Drawable> = _imagenResta

    private val _imagenDivision = MutableLiveData<Drawable>()
    val imagenDivision: LiveData<Drawable> = _imagenDivision

    private val _imagenMultiplicacion = MutableLiveData<Drawable>()
    val imagenMultiplicacion: LiveData<Drawable> = _imagenMultiplicacion

    fun cargaDeImagenSuma(url: String) {
        cargarImagen(url, _imagenSuma)
    }

    fun cargaDeImagenResta(url: String) {
        cargarImagen(url, _imagenResta)
    }

    fun cargaDeImagenDivision(url: String) {
        cargarImagen(url, _imagenDivision)
    }

    fun cargaDeImagenMultiplicacion(url: String) {
        cargarImagen(url, _imagenMultiplicacion)
    }

    fun cargarImagen(url: String, liveData: MutableLiveData<Drawable>) {

        Glide.with(getApplication<Application>().applicationContext)
            .load(url)
            .error(R.drawable.ic_error_placeholder)
            .into(object : CustomTarget<Drawable>(){
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    //Cuando la imagen esta lista la establece en el liveData
                    liveData.value = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    //Barramos el cache para mejor rendimiento
                    Glide.with(getApplication<Application>().applicationContext).clear(this)
                }
            })

    }
}