package com.example.travelapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.NonDisposableHandle.parent


class SpacesItemDecoration : RecyclerView.ItemDecoration {
    private var mSpace = 0
    constructor(space: Int) {
        this.mSpace = space
    }
    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.left = mSpace
        outRect.right = mSpace
        outRect.bottom = mSpace
        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0) outRect.top = mSpace
    }
}
