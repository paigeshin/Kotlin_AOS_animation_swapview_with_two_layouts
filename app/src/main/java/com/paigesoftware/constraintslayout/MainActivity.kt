package com.paigesoftware.constraintslayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var constraintSetOld = ConstraintSet()
    private var constraintSetNew = ConstraintSet()

    private var currentLayoutIsAlt = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*** Cloning each other ***/
        constraintSetOld.clone(constraintLayout_main)
        constraintSetNew.clone(this, R.layout.activity_main_alt)

    }

    /* Swaping old => new, new => old */
    fun swapView(view: View) {

        /* Animation Applied with change Bounds */
        val changeBounds: Transition = ChangeBounds()
        changeBounds.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(constraintLayout_main, changeBounds)
        if(!currentLayoutIsAlt) {
            constraintSetNew.applyTo(constraintLayout_main)
            currentLayoutIsAlt = true
        } else {
            constraintSetOld.applyTo(constraintLayout_main)
            currentLayoutIsAlt = false
        }

        /* Animation Applied without change Bounds */
//        TransitionManager.beginDelayedTransition(constraintLayout_main)
//        if(!currentLayoutIsAlt) {
//            constraintSetNew.applyTo(constraintLayout_main)
//            currentLayoutIsAlt = true
//        } else {
//            constraintSetOld.applyTo(constraintLayout_main)
//            currentLayoutIsAlt = false
//        }

    }

}