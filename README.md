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
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/25049737-ca33-4d16-88a4-5ac73f2d0876)



### Opción de Recuperar Contraseña

Utilizamos Firebase para almacenar usuarios y contraseñas. Si necesitas recuperar tu cuenta, recibirás un correo en la dirección que proporcionaste. El correo contendrá instrucciones para crear una nueva contraseña.

![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/2d8e23c2-f166-4af8-b9f0-5109e8ad6dda)


### Botón de Ingresar Mediante Huella Digital

Para acceder usando huella digital, simplemente coloca tu huella registrada en el dispositivo. Si aún no has registrado una huella, se abrirá un espacio donde puedes registrarla y así poder acceder a UMovie.
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/7b29166e-08f4-4d85-bd51-cb1add9ae133)

En el caso de no tener huella te saldrá lo siguiente:
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/0fcf53a4-8c8e-48bc-8a0a-d325307d8074)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/4f8cb09d-878c-47a9-a90f-6dc20eb49bb6)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/a3e5702d-dd41-4b4a-9e4f-c729eb1c4aba)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/eacca71e-136e-4736-a373-1d2d22b02e2c)

Para poder activar la huella digital le das en los 3 puntos en la parte superior del celular y en la venta que te aparece le dar en Fingerprint
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/87c8fa9b-39ac-48a0-a6f1-2f9fdf56a598)

Y presionas dos veces el botón touch senssor
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/5b4700e2-b12a-4313-b20d-8f31fcd17cc7)
Y se agregará la huella
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/169b4589-db44-4d61-ab24-31bda95ba252)
A continuacón selecciona el incono de la huella y se abrirá la pestaña donde puedes seleccionar touch sensor nuevamente para acceder a la interfaz principal.
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/4fdb189b-5431-4e82-9471-548efbd75906)

### Pantalla de Registro

Para registrar una cuenta, ingresa tu correo y contraseña. También puedes usar tu huella digital para un acceso más rápido.
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/09a03c3a-f0b1-4d99-9033-47dd1c3e2ffc)

## Activities

### Second Activity

Aquí podrás observar una interfaz principal amigable a la vista y muy intuitiva. En la parte inferior encontrarás el acceso a la barra de navegación.

#### Barra de Navegación
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/77044281-f489-4b75-8823-257142b7f150)

- Películas de Estreno o inicio: Muestra películas actuales en cines con detalles.
- Películas Populares o buscar: Presenta películas más populares con sus detalles.
- Mi Perfil: Muestra tu correo registrado y la lista de favoritos.

## Instalación

1. Instala Android Studio siguiendo las instrucciones en su [sitio web](https://developer.android.com/studio).![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/f3488b97-4cbb-4cb1-a2ab-18b8930ada10)

2. Descarga la versión ZIP del proyecto desde [aquí](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa.git).![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/8e950364-b657-4768-a6dd-62248af19522)

3. Extrae el archivo ZIP y abre la carpeta en Android Studio.![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/519e9808-fd80-4afa-94a5-1edec3b2d9ea)

4. Abre el terminal dentro de Android Studio y ejecuta los siguientes comandos:
   -git checkout aplicacionMovil
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

![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/a07da629-3137-4247-bea7-0f4143d4171c)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/20007fa5-8926-4eb4-b663-48f7f2d2e15b)

## Permisos Necesarios

Para el correcto funcionamiento de la aplicación, se requieren los siguientes permisos:

1. **Acceso a la Cámara**: Este permiso es necesario para permitir al usuario tomar fotos y establecer una imagen de perfil.
2.  **Acceso al almacenamiento**: Este permiso es esencial para permitir que el usuario elija imágenes de su galería como foto de perfil.
- Implementación:

![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/8a470d70-d4e9-4c52-8a07-df08761395a3)


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

![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/09a03c3a-f0b1-4d99-9033-47dd1c3e2ffc)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/09a03c3a-f0b1-4d99-9033-47dd1c3e2ffc)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/09a03c3a-f0b1-4d99-9033-47dd1c3e2ffc)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/a197faf8-61d0-4913-ad79-092a64660bd6)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/4543c696-0361-4d4e-ab87-8e35dcb1b8d6)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/51906554-cd90-4386-9ef1-697b6ad5102e)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/c32d1a32-d0c6-40ea-baea-58aa72c19556)
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/9e2d91e0-32dd-4118-89b1-a0ebfbb18f6b)


6. **Favoritos en Mi Perfil:**
    - En la sección "Mi Perfil", encontrarás la lista de películas a las que les has dado "Me Gusta".
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686263/09a03c3a-f0b1-4d99-9033-47dd1c3e2ffc)

## Licencia

Este proyecto está bajo una Licencia Abierta.

## Autor

Realizado por Romina Ramirez y Xavier Aguilar.

## Agradecimientos
![image](https://github.com/Romina-Ramirez/dispositivos_moviles_rr_xa/assets/105686398/75a0eb80-9f35-4dc7-9379-f30619299a51)

Agradecemos al Ingeniero Byron Torres por impartirnos el conocimiento que implementamos en nuestra aplicación. Sus enseñanzas fueron fundamentales para el desarrollo de UMovie.
