package com.example.opengles
import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle

class MainActivity : Activity() {

    private lateinit var openGLView: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openGLView = MyOpenGLSurfaceView(this)
        // Declare the view openGL ES environment
        setContentView(openGLView)

    }

}