package com.example.opengles

import android.content.Context
import android.opengl.GLSurfaceView

class MyOpenGLSurfaceView(context: Context) : GLSurfaceView(context) {

    private val renderer: MyOpenGLRenderer
    init {

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2)

        renderer = MyOpenGLRenderer()

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(renderer)
    }

}
