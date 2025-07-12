Calculadora Android con Material Design
Descripción
Una calculadora básica para Android desarrollada con Kotlin que implementa las mejores prácticas de desarrollo móvil moderno.

Características Principales
🏗️ Arquitectura
MVVM (Model-View-ViewModel) - Separación clara de responsabilidades
ViewModel - Manejo del estado y lógica de negocio
LiveData - Observación reactiva de cambios en la UI
Data Binding - Conexión automática entre UI y datos
🎨 Diseño
Material Design 3 - Componentes modernos y elegantes
ConstraintLayout - Diseño responsivo y eficiente
Tema oscuro - Interfaz moderna y cómoda para la vista
Animaciones - Efectos de ripple y transiciones suaves
🔧 Funcionalidades
✅ Operaciones básicas: suma, resta, multiplicación, división
✅ Números decimales
✅ Función de limpiar (C)
✅ Función de borrar (DEL)
✅ Manejo de errores (división por cero)
✅ Persistencia de datos durante rotaciones
✅ Formato automático de números
Estructura del Proyecto
app/
├── src/main/java/com/example/calculadora/
│   ├── MainActivity.kt           # Actividad principal
│   └── CalculatorViewModel.kt    # Lógica de negocio
├── src/main/res/
│   ├── layout/
│   │   └── activity_main.xml     # Diseño principal
│   ├── values/
│   │   ├── colors.xml           # Colores del tema
│   │   ├── strings.xml          # Strings localizables
│   │   └── styles.xml           # Estilos personalizados
│   └── AndroidManifest.xml      # Configuración de la app
└── build.gradle                 # Dependencias
Tecnologías Utilizadas
📱 Android Framework
Kotlin - Lenguaje principal
Android SDK - Plataforma de desarrollo
Material Components - Componentes de UI
🏛️ Arquitectura
ViewModel - androidx.lifecycle:lifecycle-viewmodel-ktx
LiveData - androidx.lifecycle:lifecycle-livedata-ktx
View Binding - Conexión segura con vistas
🎨 UI/UX
Material Design - com.google.android.material:material
ConstraintLayout - androidx.constraintlayout:constraintlayout
Tema personalizado - Colores y estilos coherentes
Instalación y Configuración
Prerequisitos
Android Studio Arctic Fox o superior
SDK mínimo: API 24 (Android 7.0)
SDK objetivo: API 34 (Android 14)
Pasos de instalación
Crear nuevo proyecto
bash
# En Android Studio: File > New > New Project
# Seleccionar "Empty Activity"
Configurar dependencias
Copiar el contenido de build.gradle en el archivo del módulo app
Sincronizar el proyecto
Agregar archivos
Copiar todos los archivos .kt en src/main/java/com/example/calculadora/
Copiar todos los archivos .xml en sus respectivas carpetas
Reemplazar el AndroidManifest.xml
Ejecutar la aplicación
bash
# Conectar dispositivo Android o iniciar emulador
# Presionar Run (Ctrl+R)
Arquitectura Detallada
ViewModel Pattern
kotlin
class CalculatorViewModel : ViewModel() {
    private val _display = MutableLiveData<String>()
    val display: LiveData<String> = _display
    
    // Lógica de negocio separada de la UI
}
LiveData Observer
kotlin
viewModel.display.observe(this, Observer { display ->
    binding.tvDisplay.text = display
})
Material Design Components
xml
<com.google.android.material.button.MaterialButton
    style="@style/CalculatorButtonNumber"
    android:backgroundTint="@color/number_button_color"
    app:cornerRadius="16dp" />
Características Avanzadas
🔄 Persistencia de Estado
Los datos se mantienen durante rotaciones de pantalla
El ViewModel sobrevive a cambios de configuración
No se pierden cálculos en progreso
🎨 Diseño Responsivo
ConstraintLayout adapta el diseño a diferentes tamaños
Botones con relación de aspecto 1:1
Margenes y espaciado consistentes
🛡️ Manejo de Errores
División por cero controlada
Validación de entrada
Mensajes de error claros
🔢 Precisión Matemática
Uso de BigDecimal para cálculos precisos
Formateo automático de resultados
Manejo de números decimales
Personalización
Cambiar Colores
xml
<!-- En colors.xml -->
<color name="number_button_color">#FF2D2D2D</color>
<color name="operator_button_color">#FFFF9500</color>
Modificar Estilos
xml
<!-- En styles.xml -->
<style name="CalculatorButtonNumber">
    <item name="android:textSize">24sp</item>
    <item name="cornerRadius">16dp</item>
</style>
Testing
Pruebas Unitarias
kotlin
@Test
fun testAddition() {
    val viewModel = CalculatorViewModel()
    viewModel.onNumberClick("5")
    viewModel.onOperatorClick("+")
    viewModel.onNumberClick("3")
    viewModel.onEqualsClick()
    
    assertEquals("8", viewModel.display.value)
}
Mejoras Futuras
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
Contribuir
Fork el repositorio
Crear una rama para la nueva característica
Implementar los cambios
Escribir tests
Crear un Pull Request
Licencia
Este proyecto está bajo la licencia MIT. Ver el archivo LICENSE para más detalles.

Desarrollado con ❤️ usando las mejores prácticas de Android Development

