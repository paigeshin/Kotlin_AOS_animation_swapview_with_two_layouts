# Notion Document

[android swapview with constraints](https://www.notion.so/android-swapview-with-constraints-3218020179d14e5d83102baa0cf98b5d)

# 사용법

- layout file을 두 개를 만들어서 다르게 포지셔닝 한다.

### activity_main.xml

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68bbeedc-c6e4-47c8-b47b-893caf0a0348/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/68bbeedc-c6e4-47c8-b47b-893caf0a0348/Untitled.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:onClick="swapView"
    tools:context="com.paigesoftware.constraintslayout.MainActivity">
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="150dp"
        android:layout_marginStart="8dp"
        android:layout_marginBottom="8dp"
        android:scaleType="centerCrop"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/imageView2"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintHorizontal_chainStyle="packed"
        app:layout_constraintStart_toStartOf="parent"
        app:srcCompat="@drawable/sky" />
    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="0dp"
        android:layout_height="150dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        android:scaleType="centerCrop"
        android:src="@drawable/balloon"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.5"
        app:layout_constraintStart_toEndOf="@+id/imageView" />
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginBottom="8dp"
        android:gravity="center_horizontal"
        android:text="Headline"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"
        app:layout_constraintBottom_toTopOf="@+id/imageView"
        app:layout_constraintStart_toStartOf="parent" />
    <TextView
        android:id="@+id/textView2"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginStart="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        android:text="@string/dummy_text"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

### activity_main_alt.xml

![https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6735e7bd-ac41-4a0b-94bf-97b52df833fb/Untitled.png](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6735e7bd-ac41-4a0b-94bf-97b52df833fb/Untitled.png)

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:onClick="swapView"
    tools:context="com.paigesoftware.constraintslayout.MainActivity">
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:scaleType="centerCrop"
        app:layout_constraintDimensionRatio="16:9"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="1.0"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:srcCompat="@drawable/sky" />
    <ImageView
        android:id="@+id/imageView2"
        android:layout_width="200dp"
        android:layout_height="200dp"
        android:scaleType="centerCrop"
        android:src="@drawable/balloon"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />
    <TextView
        android:id="@+id/textView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:gravity="center_horizontal"
        android:text="Headline"
        android:textAppearance="@style/TextAppearance.AppCompat.Large"
        app:layout_constraintEnd_toStartOf="@+id/imageView2"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/imageView" />
    <TextView
        android:id="@+id/textView2"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginStart="8dp"
        android:layout_marginTop="8dp"
        android:layout_marginEnd="8dp"
        android:layout_marginBottom="8dp"
        android:text="@string/dummy_text"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/imageView2"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@+id/textView" />
</androidx.constraintlayout.widget.ConstraintLayout>
```

### Code

```kotlin
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
```