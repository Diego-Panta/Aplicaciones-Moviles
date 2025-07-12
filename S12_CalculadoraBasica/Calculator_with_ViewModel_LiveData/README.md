# 📱 Calculadora Android con Material Design

## 📄 Descripción
Una calculadora básica para Android desarrollada con **Kotlin** que implementa las mejores prácticas de desarrollo móvil moderno.

---

## ✨ Características Principales

### 🏗️ Arquitectura
- **MVVM (Model-View-ViewModel)** – Separación clara de responsabilidades
- **ViewModel** – Manejo del estado y lógica de negocio
- **LiveData** – Observación reactiva de cambios en la UI
- **Data Binding** – Conexión automática entre UI y datos

### 🎨 Diseño
- **Material Design 3** – Componentes modernos y elegantes
- **ConstraintLayout** – Diseño responsivo y eficiente
- **Tema oscuro** – Interfaz moderna y cómoda para la vista
- **Animaciones** – Efectos de *ripple* y transiciones suaves

- App Funcionado

<img width="372" height="832" alt="image" src="https://github.com/user-attachments/assets/197d765f-5f34-45db-8934-20531768fc53" />
<img width="375" height="830" alt="image" src="https://github.com/user-attachments/assets/e2edf17b-14ba-4fdc-9ded-8717f474c643" /> --> <img width="367" height="831" alt="image" src="https://github.com/user-attachments/assets/ee729dca-4a87-45b7-8539-b552792a7a02" />




### 🔧 Funcionalidades
- ✅ Operaciones básicas: suma, resta, multiplicación, división  
- ✅ Números decimales  
- ✅ Botón de limpiar (`C`)  
- ✅ Botón de borrar (`DEL`)  
- ✅ Manejo de errores (división por cero)  
- ✅ Persistencia de datos durante rotaciones  
- ✅ Formato automático de números  

---

## 📂 Estructura del Proyecto

app/
├── src/main/java/com/example/calculadora/
│ ├── MainActivity.kt # Actividad principal
│ └── CalculatorViewModel.kt # Lógica de negocio
├── src/main/res/
│ ├── layout/
│ │ └── activity_main.xml # Diseño principal
│ ├── values/
│ │ ├── colors.xml # Colores del tema
│ │ ├── strings.xml # Strings localizables
│ │ └── styles.xml # Estilos personalizados
│ └── AndroidManifest.xml # Configuración de la app
└── build.gradle # Dependencias

---

## 🔧 Tecnologías Utilizadas

### 📱 Android Framework
- **Kotlin** – Lenguaje principal
- **Android SDK** – Plataforma de desarrollo
- **Material Components** – Componentes de UI

### 🏛️ Arquitectura
- `androidx.lifecycle:lifecycle-viewmodel-ktx`
- `androidx.lifecycle:lifecycle-livedata-ktx`
- **View Binding**

### 🎨 UI/UX
- `com.google.android.material:material`
- `androidx.constraintlayout:constraintlayout`
- Temas personalizados (colores, estilos)

---

## ⚙️ Instalación y Configuración

### 📋 Prerequisitos
- Android Studio **Arctic Fox** o superior  
- SDK mínimo: **API 24** (Android 7.0)  
- SDK objetivo: **API 34** (Android 14)

### 📥 Pasos de instalación

1. Crear nuevo proyecto:
    ```bash
    # En Android Studio:
    File > New > New Project
    # Seleccionar "Empty Activity"
    ```

2. Configurar dependencias:
    - Copiar el contenido de `build.gradle` en el archivo del módulo `app`
    - Sincronizar el proyecto

3. Agregar archivos:
    - Copiar los archivos `.kt` en `src/main/java/com/example/calculadora/`
    - Copiar los archivos `.xml` en sus respectivas carpetas
    - Reemplazar `AndroidManifest.xml`

4. Ejecutar la aplicación:
    ```bash
    # Conectar dispositivo Android o iniciar emulador
    # Presionar Run (Ctrl+R)
    ```

---

## 🧱 Arquitectura Detallada

### ViewModel Pattern
```kotlin
class CalculatorViewModel : ViewModel() {
    private val _display = MutableLiveData<String>()
    val display: LiveData<String> = _display
    
    // Lógica de negocio separada de la UI
}

LiveData Observer

viewModel.display.observe(this, Observer { display ->
    binding.tvDisplay.text = display
})

Material Design Component

<com.google.android.material.button.MaterialButton
    style="@style/CalculatorButtonNumber"
    android:backgroundTint="@color/number_button_color"
    app:cornerRadius="16dp" />
```
🧠 Características Avanzadas
🔄 Persistencia de Estado
ViewModel sobrevive a cambios de configuración

Datos no se pierden durante rotaciones

🎨 Diseño Responsivo
Layout adaptable con ConstraintLayout

Botones proporcionales (1:1)

Márgenes y espaciado consistentes

🛡️ Manejo de Errores
División por cero controlada

Validación de entrada

Mensajes de error claros

🔢 Precisión Matemática
Uso de BigDecimal para mayor precisión

Formato automático de resultados

Manejo de números decimales

🌱 Mejoras Futuras
🔮 Próximas Características
Historial de operaciones

Más operaciones matemáticas (potencia, raíz)

Modo científico

Temas personalizables

Soporte para tablets

🚀 Optimizaciones
Animaciones más fluidas

Mejor accesibilidad

Soporte para gestos

Modo paisaje mejorado

🤝 Contribuir
Haz fork del repositorio

Crea una nueva rama (feature/nueva-funcionalidad)

Implementa tus cambios

Escribe pruebas

Haz un Pull Request

