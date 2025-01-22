# Documentación Frontend

---

## Usuarios de prueba

- **Usuario Administrador:**
```

Username: admin
Password: admin123

```

- **Usuario Cliente:**
```

Username: client
Password: hola123

```

---

## Rustas de las vistas

---
### Auth

#### `GET` /auth/login

>A esta ruta pude acceder cualquier persona

- **Descripción:** Muestra el formulario de inicio de sesión.
- **Respuesta:** Renderiza la vista `auth/login` con un modelo para la solicitud de autenticación.

---

#### `POST` /auth/login

>A esta ruta pude acceder cualquier persona

- **Descripción:** Autentica al usuario con las credenciales proporcionadas.
- **Parámetros:**
    - `loginRequest` (cuerpo del formulario): Datos de autenticación (usuario y contraseña).
- **Respuesta:**
    - Redirige a `/alumnos` si la autenticación es exitosa.
    - Agrega el token y el rol del usuario a la sesión.
    - Redirige a `/auth/login` con un mensaje de error si las credenciales son inválidas.

---

#### `GET` /auth/logout

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

- **Descripción:** Cierra la sesión del usuario.
- **Respuesta:**
    - Invalida la sesión.
    - Redirige a `/auth/login`.

---

### Alumno

#### `GET` /alumnos

>A esta ruta puden acceder los usuarios con los roles `ROLE_ADMIN` y `ROLE_CLIENT`

- **Descripción:** Obtiene la lista de alumnos paginada.
- **Parámetros:**
    - `page` (opcional, valor por defecto: 0): Número de la página.
    - `size` (opcional, valor por defecto: 15): Tamaño de la página.
- **Requiere Autenticación:** Sí (verificación de token en sesión).
- **Respuesta:** Renderiza la vista `alumnos/index` con la lista de alumnos y el rol del usuario.
- **Errores:** Redirige a `/auth/login` si no hay un token en la sesión o si ocurre un error.
---

#### `GET` /alumnos/create

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

- **Descripción:** Muestra el formulario para crear un nuevo alumno.
- **Requiere Autenticación:** Sí (verificación de token en sesión).
- **Respuesta:** Renderiza la vista `alumnos/crearAlumnos` con un modelo para crear un alumno.
- **Errores:** Redirige a `/auth/login` si no hay un token en la sesión.

---

#### `POST` /alumnos/create

>A esta ruta solo pueden acceder los usuarios con el rol `ROLE_ADMIN`

- **Descripción:** Crea un nuevo alumno con los datos proporcionados.
- **Parámetros:**
    - `createAlumnoRequest` (cuerpo del formulario): Datos del alumno a crear.
- **Requiere Autenticación:** Sí (verificación de token en sesión).
- **Respuesta:** Redirige a `/alumnos` tras crear el alumno.

---

## Notas
- Todas las rutas dependen de la autenticación mediante token almacenado en la sesión.
- Algunas vistas dependen del rol del usuario que inició sesión.