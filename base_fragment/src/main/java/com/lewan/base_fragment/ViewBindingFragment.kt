package com.lewan.base_fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar


abstract class ViewBindingFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : Fragment() {
    private var internalBinding: ViewBinding? = null

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = requireNotNull(internalBinding) as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        internalBinding = bindingInflater.invoke(inflater, container, false)
        return requireNotNull(internalBinding).root
    }

    override fun onDestroyView() {
        internalBinding = null
        super.onDestroyView()
    }

    protected fun showSnackBar(resId: Int? = null, text: String? = null) {

        val inputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().window.decorView.rootView.windowToken,
            0
        )

        val v = view
        if (v != null && this.isVisible && this.isAdded){
            when {
                resId != null -> {
                    Snackbar.make(v, resId, Snackbar.LENGTH_SHORT).show()
                }
                text != null -> {
                    Snackbar.make(v, text, Snackbar.LENGTH_SHORT).show()
                }
            }
        }
    }
}