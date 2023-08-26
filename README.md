# Proyecto UMovie

[![Estado del Proyecto](https://img.shields.io/badge/Estado-Aplicación%20Terminada-brightgreen)](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa)

Aplicación donde puedes ver información de películas. Creado por Romina Ramirez y Xavier Aguilar.

## Tabla de Contenidos

- [Ingresar a la Página Web](#ingresar-a-la-página-web)
  - [Página de Inicio de Sesión](#página-de-inicio-de-sesión)
  - [Opción de Recuperar Contraseña](#opción-de-recuperar-contraseña)
  - [Botón de Ingresar Mediante Huella Digital](#botón-de-ingresar-mediante-huella-digital)
  - [Pantalla de Registro](#pantalla-de-registro)
- [Activities](#activities)
  - [Second Activity](#second-activity)
    - [Barra de Navegación](#barra-de-navegación)
- [Instalación](#instalación)
- [Librerías Utilizadas](#librerías-utilizadas)
- [Permisos Necesarios](#permisos-necesarios)
- [Uso](#uso)
- [Licencia](#licencia)
- [Autor](#autor)
- [Agradecimientos](#agradecimientos)

## Ingresar a la Página Web

### Página de Inicio de Sesión

La primera ventana que verás es la de inicio de sesión. Si ya tienes una cuenta, puedes acceder. Si no, tienes dos opciones: registrar una cuenta o iniciar sesión con huella, lo que también requerirá registrar tu huella para acceder a la aplicación.

![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/f39415e5-c7ce-4efa-84c1-88dba24cbae6)

### Opción de Recuperar Contraseña

Utilizamos Firebase para almacenar usuarios y contraseñas. Si necesitas recuperar tu cuenta, recibirás un correo en la dirección que proporcionaste. El correo contendrá instrucciones para crear una nueva contraseña.

### Botón de Ingresar Mediante Huella Digital

Para acceder usando huella digital, simplemente coloca tu huella registrada en el dispositivo. Si aún no has registrado una huella, se abrirá un espacio donde puedes registrarla y así poder acceder a UMovie.

### Pantalla de Registro

Para registrar una cuenta, ingresa tu correo y contraseña. También puedes usar tu huella digital para un acceso más rápido.

## Activities

### Second Activity

Aquí podrás observar una interfaz principal amigable a la vista y muy intuitiva. En la parte inferior encontrarás el acceso a la barra de navegación.

#### Barra de Navegación

- Películas de Estreno: Muestra películas actuales en cines con detalles.
- Películas Populares: Presenta películas más populares con sus detalles.
- Mi Perfil: Muestra tu correo registrado y la lista de favoritos.

## Instalación

1. Instala Android Studio siguiendo las instrucciones en su [sitio web](https://developer.android.com/studio).
2. Descarga la versión ZIP del proyecto desde [aquí](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/archive/refs/heads/main.zip).
3. Extrae el archivo ZIP y abre la carpeta en Android Studio.
4. Abre el terminal dentro de Android Studio y ejecuta los siguientes comandos:
   git pull
   git checkout aplicacionMovil
5. Abre el proyecto en Android Studio.

## Librerías Utilizadas

- **JUnit**: Marco de pruebas unitarias para Java. Ayuda a verificar el correcto funcionamiento de las partes individuales del código. Implementación: `implementation 'junit:junit:x.y.z'`.

- **Espresso Core**: Framework de pruebas para la interfaz de usuario de Android. Facilita la automatización de las pruebas de UI. Implementación: `androidTestImplementation 'androidx.test.espresso:espresso-core:x.y.z'`.

- **Picasso**: Biblioteca para cargar y mostrar imágenes de manera eficiente en aplicaciones Android. Implementación: `implementation 'com.squareup.picasso:picasso:x.y.z'`.

- **Retrofit**: Biblioteca para consumir servicios web REST de manera sencilla. Facilita la comunicación con servidores y el manejo de datos. Implementación: `implementation 'com.squareup.retrofit2:retrofit:x.y.z'`.

- **Coroutines**: Biblioteca para facilitar la concurrencia y la programación asíncrona. Ayuda a manejar tareas largas sin bloquear la interfaz de usuario. Implementación: `implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:x.y.z'`.

- **ViewModel**: Componente de arquitectura que permite almacenar y administrar datos relacionados con la interfaz de usuario de manera optimizada. Implementación: `implementation 'androidx.lifecycle:lifecycle-viewmodel:x.y.z'`.

- **Room Runtime**: Biblioteca de persistencia que facilita la creación y gestión de bases de datos SQLite en aplicaciones Android. Implementación: `implementation 'androidx.room:room-runtime:x.y.z'`.

- **DataStore**: Almacén de datos eficiente y moderno que reemplaza a SharedPreferences. Implementación: `implementation 'androidx.datastore:datastore:x.y.z'`.

- **LiveData**: Componente de arquitectura que permite crear objetos observables y mantener la interfaz de usuario actualizada en función de los datos cambiantes. Implementación: `implementation 'androidx.lifecycle:lifecycle-livedata:x.y.z'`.

- **Biometric**: Librería que permite usar características biométricas para autenticar a los usuarios, como huellas dactilares y reconocimiento facial. Implementación: `implementation 'androidx.biometric:biometric:x.y.z'`.

- **Shimmer**: Biblioteca para agregar efectos de brillo tipo "shimmer" a las vistas de tu aplicación mientras se cargan. Implementación: `implementation 'com.facebook.shimmer:shimmer:x.y.z'`.

- **Firebase**: Plataforma de desarrollo de aplicaciones móviles que ofrece diversas herramientas, como autenticación, almacenamiento en la nube y bases de datos en tiempo real. Implementación: Varios módulos de Firebase, por ejemplo: `implementation 'com.google.firebase:firebase-auth:x.y.z'`.


## Permisos Necesarios

Para el correcto funcionamiento de la aplicación, se requieren los siguientes permisos:

1. **Acceso a la Cámara**: Este permiso es necesario para permitir al usuario tomar fotos y establecer una imagen de perfil.
2.  **Acceso al almacenamiento**: Este permiso es esencial para permitir que el usuario elija imágenes de su galería como foto de perfil.
   - Implementación:
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    En el permiso de la camara marca error pero es visual ya que si ter permite el acceso a la camara.

## Uso

1. **Registrarse:**
   - Abre la aplicación UMovie.
   - En la pantalla de inicio de sesión, si aún no tienes una cuenta, toca en "Registrar".
   - Completa el formulario con tu dirección de correo electrónico y una contraseña segura.
   - Toca el botón "Registrar" para crear tu cuenta.
   - Una vez registrado, podrás acceder a la aplicación con tu dirección de correo electrónico y contraseña.

2. **Iniciar Sesión:**
   - En la pantalla de inicio de sesión, ingresa tu dirección de correo electrónico y la contraseña asociada a tu cuenta.
   - Toca el botón "Iniciar Sesión".
   - Si prefieres usar la autenticación biométrica, toca en "Iniciar con Huella" y sigue las instrucciones para registrar tu huella.
   
3. **Recuperar Cuenta:**
   - Si olvidaste tu contraseña, en la pantalla de inicio de sesión toca en "Recuperar Contraseña".
   - Es necesario ingresar la dirección de correo electrónico asociada a tu cuenta.
   - Luego, revisa tu correo electrónico, donde recibirás un enlace para restablecer tu contraseña.
   - Sigue las instrucciones del correo para crear una nueva contraseña y acceder a tu cuenta.

4. **Acceso con Huella:**
   - En la pantalla de inicio de sesión, si ya has registrado una huella en tu dispositivo, toca en "Iniciar con Huella".
   - Coloca tu huella registrada en el lector biométrico.
   - Si aún no has registrado una huella, accede mediante la opción de ingresar con huella y agrega tu huella siguiendo los pasos que se muestran en pantalla, una vez realizado esto accede de la misma manera.

5. **Explorar la Aplicación:**
   - Una vez que hayas iniciado sesión, llegarás a la pantalla principal con la barra de navegación en la parte inferior.
   - Toca "Películas de Estreno" para ver las películas actuales en cines con detalles como nombre, descripción, portada, fecha de estreno e idioma, al entrar en los detalles de una película, puedes darle "Me Gusta" a la misma para agregarla a tus favoritos..
   - Toca "Películas Populares" para explorar las películas más populares junto con sus detalles.
   - Toca "Mi Perfil" para ver tu correo electrónico registrado y para establecer una foto de perfil. Si lo deseas, puedes seleccionar una foto de tu galería o tomar una nueva usando la cámara.

6. **Favoritos en Mi Perfil:**
   - En la sección "Mi Perfil", encontrarás la lista de películas a las que les has dado "Me Gusta".
   
## Licencia

Este proyecto está bajo una Licencia Abierta.

## Autor

Realizado por Romina Ramirez y Xavier Aguilar.

## Agradecimientos

Agradecemos al ingeniero Byron Torres por impartirnos el conocimiento que implementamos en nuestra aplicación. Sus enseñanzas fueron fundamentales para el desarrollo de UMovie.
