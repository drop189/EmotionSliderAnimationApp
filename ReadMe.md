# Emotion Slider Animation App

Это приложение предназначено для демонстрации работы анимаций Lottie и Kotlin Animator. 
Оно не имеет практической пользы, но служит отличным примером использования этих технологий для создания визуальных эффектов в Android-приложениях.

## Особенности

- **Интерактивный SeekBar**: Изменяет цвет фона и запускает соответствующую анимацию при изменении значения SeekBar.
- **Плавное изменение цвета фона**: Использует анимацию для изменения цвета фона с эффектом плавного перехода.
- **Анимации**: Использует Lottie для отображения анимаций, которые появляются с эффектом плавного появления (fade in).

## Установка

1. Клонируйте репозиторий:
    ```bash
    git clone https://github.com/drop189/EmotionSliderAnimationApp.git
    ```

2. Перейдите в каталог проекта:
    ```bash
    cd EmotionSliderAnimationApp
    ```

3. Откройте проект в [Android Studio](https://developer.android.com/studio) и синхронизируйте Gradle.

4. Убедитесь, что все зависимости установлены и проект скомпилирован без ошибок.

## Использование

1. Запустите приложение на эмуляторе или реальном устройстве.

2. Используйте SeekBar для выбора различных значений и наблюдайте за изменениями цвета фона и анимациями.

## Примеры кода

### Плавное изменение цвета фона

```kotlin
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

    val colorAnimator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
    colorAnimator.duration = BACKGROUND_COLOR_FADE_IN_DURATION_MILS
    colorAnimator.addUpdateListener { animator ->
        val color = animator.animatedValue as Int
        binding.main.setBackgroundColor(color)
    }
    colorAnimator.start()
}
```
## Автор

- [drop189](https://github.com/drop189)

## Особая благодарность

Особая благодарность [Di Smolskii](https://smolskii.com/tutorial/01/) за вдохновение и предоставленный дизайн.