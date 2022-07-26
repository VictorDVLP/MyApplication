package com.example.myapplication.viewmodel

import android.view.View
import androidx.databinding.BindingAdapter

// A binding adapter responsible for changing the visibility of the delete button

@BindingAdapter("visibility")
 fun setIsVisibility(view :View, isVisibility: Boolean ) {
    when (isVisibility) {
        true -> view.visibility = View.INVISIBLE

        false -> view.visibility = View.VISIBLE
    }
}
