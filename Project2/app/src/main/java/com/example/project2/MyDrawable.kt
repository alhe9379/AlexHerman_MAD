package com.example.project2

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class MyDrawable : Drawable() {
    var difficulty: Int = 0
    var pentagonLocationX: Float = -1F
    var pentagonLocationY: Float = -1F
    var pentagonSize: Int = -1

    //https://medium.com/android-news/android-canvas-for-drawing-and-custom-views-e1a3e90d468b
    fun drawTriangle(x: Float, y: Float, width: Int, paint: Paint, canvas: Canvas) {
        val halfWidth = width / 2
        val path = Path()
        path.moveTo(x, y - halfWidth) // Top
        path.lineTo(x - halfWidth, y + halfWidth) // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth) // Bottom right
        path.lineTo(x, y - halfWidth) // Back to Top
        path.close()
        canvas.drawPath(path, paint)
    }

    fun drawRectangle(x: Float, y: Float, width: Int, height: Int, paint: Paint, canvas: Canvas) {
        val path = Path()
        path.moveTo(x, y)
        path.lineTo(x + width, y)
        path.lineTo(x + width, height + y)
        path.lineTo(x, height + y)
        path.lineTo(x, y)
        path.close()
        canvas.drawPath(path, paint)
    }

    //https://math.stackexchange.com/questions/143932/calculate-point-given-x-y-angle-and-distance
    //https://www.daniweb.com/programming/software-development/threads/420620/how-to-draw-a-line-having-length-and-angle
    fun drawHexagon(x: Float, y: Float, faceLength: Int, paint: Paint, canvas: Canvas) {
        val path = Path()
        var newX: Float
        var newY: Float
        path.moveTo(x, y)

        newX = x + (cos(1.0472) * faceLength).toFloat()
        newY = y + (sin(1.0427) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX += faceLength
        path.lineTo(newX, newY)

        newX = newX + (cos(5.23599) * faceLength).toFloat()
        newY = newY + (sin(5.23599) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX + (cos(4.18879) * faceLength).toFloat()
        newY = newY + (sin(4.18879) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX + (cos(3.14159) * faceLength).toFloat()
        newY = newY + (sin(3.14159) * faceLength).toFloat()
        path.lineTo(newX, newY)

        path.close()
        canvas.drawPath(path, paint)
    }

    fun drawHeptagon(x: Float, y: Float, faceLength: Int, paint: Paint, canvas: Canvas) {
        val path = Path()
        var newX: Float
        var newY: Float
        path.moveTo(x, y)

        newX = x + faceLength
        newY = y
        path.lineTo(newX, newY)

        newX = newX - (cos(2.24396982) * faceLength).toFloat()
        newY = newY + (sin(2.24396982) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX + (cos(1.7954202) * faceLength).toFloat()
        newY = newY + (sin(1.7954202) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX - (cos(0.44872415) * faceLength).toFloat()
        newY = newY + (sin(0.44872415) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX - (cos(5.83446116) * faceLength).toFloat()
        newY = newY + (sin(5.83446116) * faceLength).toFloat()
        path.lineTo(newX, newY)


        newX = newX + (cos(1.7954202) * faceLength).toFloat()
        newY = newY - (sin(1.7954202) * faceLength).toFloat()
        path.lineTo(newX, newY)

        path.close()

        canvas.drawPath(path, paint)
    }

    fun drawOctogon(x: Float, y: Float, faceLength: Int, paint: Paint, canvas: Canvas) {
        val path = Path()
        var newX: Float
        var newY: Float
        path.moveTo(x, y)

        newX = x + faceLength
        newY = y
        path.lineTo(newX, newY)

        newX = newX - (cos(2.35619) * faceLength).toFloat()
        newY = newY + (sin(2.35619) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newY = newY + faceLength
        path.lineTo(newX, newY)

        newX = newX - (cos(0.785398) * faceLength).toFloat()
        newY = newY + (sin(0.785398) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX - faceLength
        path.lineTo(newX, newY)

        newX = newX - (cos(5.49779) * faceLength).toFloat()
        newY = newY + (sin(5.49779) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newY = newY - faceLength
        path.lineTo(newX, newY)

        path.close()

        canvas.drawPath(path, paint)
    }

    fun drawPentagon(x: Float, y: Float, faceLength: Int, paint: Paint, canvas: Canvas) {
        val path = Path()
        var newX: Float
        var newY: Float
        path.moveTo(x, y)

        newX = x + faceLength
        newY = y
        path.lineTo(newX, newY)

        newX = newX - (cos(1.88496) * faceLength).toFloat()
        newY = newY + (sin(1.88496) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX - (cos(0.628319) * faceLength).toFloat()
        newY = newY + (sin(0.628319) * faceLength).toFloat()
        path.lineTo(newX, newY)

        newX = newX - (cos(5.65487) * faceLength).toFloat()
        newY = newY + (sin(5.65487) * faceLength).toFloat()
        path.lineTo(newX, newY)

        path.close()
        canvas.drawPath(path, paint)

        pentagonLocationX = x
        pentagonLocationY = y + 395 // for about the height of status bar, and timer
        pentagonSize = faceLength
    }


    override fun draw(canvas: Canvas) {
        // Get the drawable's bounds
        val width: Int = bounds.width()
        val height: Int = bounds.height()

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            drawHeptagon(
                Random.nextInt(65, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(50, 150),
                paint,
                canvas
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            drawOctogon(
                Random.nextInt(65, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(50, 150),
                paint,
                canvas
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            canvas.drawCircle(
                Random.nextInt(75, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(50, 150).toFloat(),
                paint
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )

            //square
            val squareWidth = Random.nextInt(100, 300)
            drawRectangle(
                Random.nextInt(65, width - 200).toFloat(),
                Random.nextInt(18, height - 200).toFloat(),
                squareWidth,
                squareWidth,
                paint,
                canvas
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            drawRectangle(
                Random.nextInt(65, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(100, 300),
                Random.nextInt(100, 300),
                paint,
                canvas
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
            drawTriangle(
                Random.nextInt(65, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(100, 300),
                paint,
                canvas
            )
        }

        for (i in 1..difficulty) {
            val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )

            drawHexagon(
                Random.nextInt(50, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(50, 150),
                paint,
                canvas
            )
        }

        val paint = Paint()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 15F
            paint.setARGB(
                255,
                Random.nextInt(0, 255),
                Random.nextInt(0, 255),
                Random.nextInt(0, 255)
            )
        drawPentagon(
                Random.nextInt(50, width - 300).toFloat(),
                Random.nextInt(18, height - 300).toFloat(),
                Random.nextInt(100, 250),
                paint,
                canvas
            )
    }

    override fun setAlpha(alpha: Int) {
        // This method is required
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        // This method is required
    }

    override fun getOpacity(): Int =
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        PixelFormat.OPAQUE
}