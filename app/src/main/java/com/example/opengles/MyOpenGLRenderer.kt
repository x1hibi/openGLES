package com.example.opengles
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10
import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.SystemClock
import android.util.Log

class MyOpenGLRenderer : GLSurfaceView.Renderer {

    //Declare shapes
    private lateinit var myTriangle: Triangle
    private lateinit var myTriangle2 : Triangle2
    private lateinit var mySquare: Square
    private lateinit var mySquare2: Square2

    // Create the surface first time
    override fun onSurfaceCreated(unused: GL10, config: EGLConfig) {
        // Set the background frame color
        GLES20.glClearColor(0.0f, 1.0f, 1.0f, 1.0f)
        // initialize a triangle
        myTriangle = Triangle()
        mySquare = Square()
        myTriangle2 = Triangle2()
        mySquare2 = Square2()
    }

    // Var for rotation matrix
    private val rotationMatrix = FloatArray(16)

    override fun onDrawFrame(unused: GL10) {
        // Redraw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)

        // Code for camera
        // Set the camera position (View matrix)
        Matrix.setLookAtM(viewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1.0f, 0.0f)
        // Calculate the projection and view transformation
        Matrix.multiplyMM(vPMatrix, 0, projectionMatrix, 0, viewMatrix, 0)

        // Code for rotation
        val scratch = FloatArray(16)
        // Create a rotation transformation for the triangle
        // Var change
        //val time = SystemClock.uptimeMillis() % 4000L
        //var angle = 0.090f * time.toInt()
        val angle = 45.0f
        //Log.i("time:",time.toString())
        Matrix.setRotateM(rotationMatrix, 0, angle, 0f, 0f, -1.0f)

        // Combine the rotation matrix with the projection and camera view
        // Note that the vPMatrix factor *must be first* in order
        // for the matrix multiplication product to be correct.
        Matrix.multiplyMM(scratch, 0, vPMatrix, 0, rotationMatrix, 0)


        // Call to draw function
        //myTriangle.draw()
        //mySquare.draw()
        //myTriangle2.draw(vPMatrix)
        mySquare2.draw(vPMatrix)
        //mySquare2.draw(scratch)
    }

    // vPMatrix is an abbreviation for "Model View Projection Matrix"
    private val vPMatrix = FloatArray(16)
    private val projectionMatrix = FloatArray(16)
    private val viewMatrix = FloatArray(16)

    override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
        GLES20.glViewport(0, 0, width, height)

        val ratio: Float = width.toFloat() / height.toFloat()

        // this projection matrix is applied to object coordinates
        // in the onDrawFrame() method
        Matrix.frustumM(projectionMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
    }

}