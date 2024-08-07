package da.maximov.emotionslideranimation

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.animation.AccelerateInterpolator
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import da.maximov.emotionslideranimation.databinding.ActivityMainBinding

private const val BACKGROUND_COLOR_FADE_IN_DURATION_MILS = 300L
private const val ANIMATION_FACE_FADE_IN_DURATION_MILS = 300L
private const val INITIAL_ALPHA = 0f
private const val FINAL_ALPHA = 1f

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                with(binding) {
                    when (seekBar.progress) {
                        0 -> {
                            fadeInBack(R.color.blue)
                            fadeInAnim(R.raw.animation_face_1)
                        }

                        1 -> {
                            fadeInBack(R.color.yellow)
                            fadeInAnim(R.raw.animation_face_2)
                        }

                        2 -> {
                            fadeInBack(R.color.violet)
                            fadeInAnim(R.raw.animation_face_3)
                        }

                        3 -> {
                            fadeInBack(R.color.green)
                            fadeInAnim(R.raw.animation_face_4)
                        }

                        4 -> {
                            fadeInBack(R.color.red)
                            fadeInAnim(R.raw.animation_face_5)
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.button.setOnClickListener { Toast.makeText(this, "☆☆☆☆☆", Toast.LENGTH_SHORT).show() }

        applySystemWindowInsets()
    }

    private fun fadeInBack(changeColor: Int) {
        // Получаем фоновый Drawable у binding.main
        val startColorDrawable = binding.main.background
        // Проверяем, является ли Drawable объектом ColorDrawable и извлекаем цвет
        val startColor = if (startColorDrawable is ColorDrawable) {
            startColorDrawable.color
        } else {
            Color.TRANSPARENT // Или другой цвет по умолчанию
        }
        val endColor = getColor(changeColor)
        println(">>> changeColor - $changeColor")
        println(">>> startColor - $startColor")
        println(">>> endColor - $endColor")


        val colorAnimator =
            ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        colorAnimator.duration =
            BACKGROUND_COLOR_FADE_IN_DURATION_MILS // Продолжительность анимации в миллисекундах
        colorAnimator.addUpdateListener { animator ->
            val color = animator.animatedValue as Int
            binding.main.setBackgroundColor(color)
        }

        colorAnimator.start()
    }

    private fun fadeInAnim(animationFace: Int) {
        // Сначала устанавливаем начальное значение прозрачности на 0 (невидимо)
        binding.animationView.alpha = INITIAL_ALPHA

        // Загружаем анимацию
        binding.animationView.setAnimation(animationFace)

        // Создаем анимацию плавного появления (fade in)
        val fadeIn = ObjectAnimator.ofFloat(
            binding.animationView, "alpha", INITIAL_ALPHA,
            FINAL_ALPHA
        ).apply {
            duration =
                ANIMATION_FACE_FADE_IN_DURATION_MILS // Продолжительность анимации в миллисекундах
            interpolator = AccelerateInterpolator() // Интерполяция для более естественного эффекта
        }

        // Воспроизводим fade in и затем запускаем анимацию
        fadeIn.start()

        fadeIn.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                // Запускаем анимацию после того, как завершится fade in
                binding.animationView.playAnimation()
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }
        })
    }

    private fun applySystemWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}