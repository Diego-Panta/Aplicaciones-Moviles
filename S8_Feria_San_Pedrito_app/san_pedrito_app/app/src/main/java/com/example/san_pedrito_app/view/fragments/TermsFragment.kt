package com.example.san_pedrito_app.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.san_pedrito_app.databinding.FragmentTermsBinding

class TermsFragment : Fragment() {
    private var _binding: FragmentTermsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el WebView
        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            // Mostrar progress bar mientras carga
            setOnScrollChangeListener { _, _, _, _, _ ->
                binding.progressBar.visibility = View.VISIBLE
            }

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.progressBar.visibility = View.GONE
                }
            }

            loadUrl("https://www.uns.edu.pe/#/transparencia/13d/reglamentos")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.webView?.destroy()
        _binding = null
    }
}