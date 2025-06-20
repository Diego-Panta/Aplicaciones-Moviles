# README - Aplicación de Registro para el Desfile San Pedrito

## Descripción
Aplicación Android para registrar la asistencia de docentes y alumnos al desfile de San Pedrito (26 de junio). Desarrollada en Kotlin con arquitectura MVVM.

## Funcionalidades principales
1. FORMULARIO DE REGISTRO:
   - Campos para nombres, apellidos, código, DNI, correo y teléfono
   - Validación en tiempo real de todos los campos
   - Selección de tipo (docente/alumno) mediante spinner

2. INFORMACIÓN DEL EVENTO:
   - Fecha, hora y lugar del desfile
   - Requisitos para participantes
   - Datos de contacto de la organización

3. PROGRAMA DEL DESFILE:
   - Cronograma detallado de actividades
   - Horarios y eventos importantes

4. TÉRMINOS Y CONDICIONES:
   - Visualización en WebView
   - Barra de progreso durante la carga

## Tecnologías utilizadas
- Lenguaje: Kotlin
- Arquitectura: MVVM (Model-View-ViewModel)
- Componentes:
  * Navigation Component
  * ViewModel
  * LiveData
  * Fragments
- Diseño: Material Design 3

## Configuración requerida
- Android Studio Flamingo o superior
- SDK mínimo: API 26 (Android 8.0)
- JDK 17

## Estructura del proyecto
app/
├── model/
│   ├── Participante.kt
│   └── SanPedritoRepository.kt
├── view/
│   ├── fragments/
│   │   ├── FormFragment.kt
│   │   ├── InfoFragment.kt
│   │   ├── ProgramFragment.kt
│   │   └── TermsFragment.kt
│   └── activities/
│       └── MainActivity.kt
├── viewmodel/
│   └── SanPedritoViewModel.kt
└── utils/
    ├── Validator.kt
    └── Extensions.kt

## Dependencias principales
androidx.core:core-ktx:1.10.1
androidx.appcompat:appcompat:1.6.1
com.google.android.material:material:1.9.0
androidx.navigation:navigation-fragment-ktx:2.6.0
androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1

## Instrucciones de instalación
1. Clonar el repositorio:
   git clone https://github.com/tu-usuario/san-pedrito-app.git

2. Abrir en Android Studio:
   File > Open > Seleccionar carpeta del proyecto

3. Ejecutar la aplicación:
   - Seleccionar dispositivo/emulador
   - Click en "Run"

## Capturas de pantalla (insertar imágenes)
[Formulario de Registro]

![image](https://github.com/user-attachments/assets/e4909005-7dac-4979-9840-a7be88d11593)

[Información del Evento]

![image](https://github.com/user-attachments/assets/93f7d1d4-6337-469c-bfa2-37eec975dcc8)


[Programa del Desfile]

![image](https://github.com/user-attachments/assets/e0b27dc3-97c5-40cc-b8ac-860ae17b3a3a)


[Términos y Condiciones] 

![image](https://github.com/user-attachments/assets/42f0a455-e359-40a6-847b-38d61c4dc360)



## Licencia
Este proyecto está bajo la licencia de Diego Panta.

## Contacto
Para soporte o contribuciones:
josepanta507@gmail.com
https://github.com/Diego-Panta
