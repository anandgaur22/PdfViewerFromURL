package com.anand.pdfsampal

import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.*
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle
import java.io.File
import java.util.concurrent.Callable

class MainActivity : AppCompatActivity() {

    lateinit var pdfView: PDFView
    lateinit var progressBar: ProgressBar
    private val fileName = "github-git-cheat-sheet.pdf"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pdfView = findViewById(R.id.pdf_viewer)
        progressBar = findViewById(R.id.progress_bar)


        PDFHelper(this, fileName, Callable<Void> {
            //Callable function if download is successful
            showPDF()
            null
        }, Callable<Void> {
            //Callable function if download isn't successful
            showError()
            progressBar.setVisibility(View.GONE)
            null
        })
    }

    fun showPDF() {
        //Getting the saved PDF
        val file = File(this.getExternalFilesDir("pdfs").toString() + File.separator + fileName)
        //Loading the PDF
        pdfView.fromFile(file)
            .defaultPage(0)
            .enableAnnotationRendering(true)
            .scrollHandle(DefaultScrollHandle(this))
            .load()

        progressBar.setVisibility(View.GONE)

    }

    fun showError() {
        Toast.makeText(this, "Error downloading ", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        File(
            (this.getExternalFilesDir("pdfs")).toString()
                    + File.separator + fileName
        ).delete()

    }
}
