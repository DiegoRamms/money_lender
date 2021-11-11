package com.appgame.prestador.utils

import android.graphics.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class SwipeHelper(onSwipeListener: ((Int) -> Unit)): ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
    private var onSwipeListener: ((Int) -> Unit)? = onSwipeListener

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        onSwipeListener?.invoke(viewHolder.adapterPosition)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX , dY, actionState, isCurrentlyActive)
        val itemView = viewHolder.itemView
        val paint = Paint()
        val height = itemView.bottom.toFloat() - itemView.top.toFloat()
        val width = height / 3
        val textSize = 50f

        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
            if (dX < 0){
                val rectF = RectF(itemView.left.toFloat(),itemView.top.toFloat(),itemView.right.toFloat(),itemView.bottom.toFloat())
                paint.color = Color.RED
                c.drawRect(rectF,paint)

                val textPaint = Paint()
                textPaint.color = Color.WHITE
                textPaint.textSize = textSize
                val xValue =itemView.right -(width*3)

                c.drawText("Eliminar ", xValue,itemView.bottom -width -(textSize /3),textPaint)
            }

        }else {
            viewHolder.itemView.setBackgroundColor(Color.TRANSPARENT)
        }

    }

}