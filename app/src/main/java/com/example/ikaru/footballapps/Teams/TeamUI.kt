package com.example.ikaru.footballapps.Teams

import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.ikaru.footballapps.R
import org.jetbrains.anko.*

class TeamUI: AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui){
        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            padding = dip(8)
            orientation = LinearLayout.HORIZONTAL

            imageView {
                id = R.id.team_badge
            }.lparams(width = dip(50), height = dip(50))

            textView {
                id = R.id.team_name
                textSize = 16f
            }.lparams{
                margin = dip(15)
            }
        }
    }
}