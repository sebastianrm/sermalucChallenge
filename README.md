# Challenge
## Evaluación: JAVA


    Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
    Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.


    Todos los mensajes deben seguir el formato:

	`{"mensaje": "mensaje de error"}`
	
#### Tecnologias:

- java 17
- Spring boot 3.2.2
- h2
- Jpa Hibernate
- jwt: io.jsonwebtoken
- Log4j 2
- Validator
- OpenAI 3.0, swagger

#### Instrucciones de ejecucion:

1.- `git clone git@github.com:sebastianrm/sermalucChallenge.git`

2.- `cd sermalucChallenge`

3.- `mvn spring-boot:run` 

or:

4.1- `mvn install`

4.2- `java -jar target/sermalucChallenge-0.1.1-SNAPSHOT.jar`


#### endPoint

Method Post


http://localhost:8080/api/v1/users/add

Ejemplo body:


`
{
  "name": "string",
  "email": "aaaaaaa@dominio.cl",
  "password": "aaaaa@dominio.cl",
  "phones": [
    {
      "number": "123456",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
`
#### Requerimientos

### Errores.

Se implemento la clase `CustomControllerAdvice` que maneja las excepciones

## Correo

  1. Debe ser unico.
  encaso de repetir el correo la clase `CustomControllerAdvice` responde `"El correo ya registrado"`. http status code 409 `Conflict`.
  2.  debe respertar la expresion regular definida en la clase `ParentUser` y esta es :  
   
  ^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@dominio.com*$
  
  
## Password

segir una expresion regular:
  	la validacion de la expresion regular la realiza la clase `RegExPatterValidatorUtils` y la expresion regular se encuentra en un archivo de propiedades llamado `resources/patterns.properties`


## JWT

Se utilizo `jjwt` version `0.11.5`


#### Base de datos

Esta se crea atraves del diagrama de clases
```mermaid
erDiagram
    USER ||--o{ USER_PHONES : have
    PHONE ||--o{ USER_PHONES : have

    USER_LOG ||--o{ USER_LOG_PHONES : have
    PHONE_LOG ||--o{ USER_LOG_PHONES : have
```

### Flujo de la aplicacion
```mermaid
flowchart TD
    A[End Point] 
    A --> B{Valida entradas}
    B -->|OnError| C[Errormessage]
    B -->|OnSuccess| D{Usuario Existe}
    D -->|OnError Duplicated| C
    D -->|Usuario No existe| E{Genera Token} --> |OnError| C
    E --> F{Registra Nuevo Usuario} --> |OnError| C
    F --> G{Registra Telefonos} --> |OnError| C
    G --> H{Registra Usuario Log} --> |OnError| C
    H -->|Onsucces| I[OnsuccessMessageResponce]
```

    El EndPoint expuesto con metodo Post recibe en su payload un usuraio. Si este usuario cumple con las validaciones del Payload, encripta el password
    Y Este RequestPayload pasa a clases de servicios


    Se verifica si el mail existe, si ya existe se lansa excepcion, en caso contrario, Registra el nuevo usuario.
 
  
    Inserta en una tabla de log el insert del nuevo usuarios.

#### Patrones de diseño

Patron Factory:

    como clase padre para los objentos DTO json utilizados por la API y dto entidades de la base de datos. Entoces por ejemplo:

    se recive en el payloads un objeto json, este objeto json es persistido en la base de datos y en caso de exito se responce un objeto json son datos del objeto insertados.

    para este trabajo se tienen las clases ParentUser, UserRequest y UserEntity

    Para el log del ususario y la respuesta al cliente:
    ParentUserLog, OnSuccessUserResgister, LogUserEntity

##### Diagrama de clases:

```mermaid
classDiagram
    ParentUser <|-- UserRequest
    ParentUser <|-- UserEntity
    ParentUser : +UUID id
    ParentUser : +String email
    ParentUser : +String name
    ParentUser : +String password
    ParentUser : +Boolean isActive
    ParentUser: +factoryUserRequest()
    ParentUser : +factoryUserEntity()
    UserRequest : +factoryUserRequest()
    UserRequest : +factoryUserEntity()
    UserEntity : +factoryUserRequest()
    UserEntity : +factoryUserEntity()

    ParentUserLog <|-- OnSuccessUserResgister
    ParentUserLog <|-- LogUserEntity
    ParentUserLog : +UUID logId
    ParentUserLog : +UUID userId
    ParentUserLog : +Timestamp created
    ParentUserLog : +Timestamp modified
    ParentUserLog : +Timestamp lastLogin
    ParentUserLog : +String token
    ParentUserLog : +String isActive 
    ParentUserLog: +factoryGetLogUserEntity()
    ParentUserLog : +factorygetOnSuccessUserResgister()
    OnSuccessUserResgister : +factoryGetLogUserEntity()
    OnSuccessUserResgister : +factorygetOnSuccessUserResgister()
    LogUserEntity : +factoryGetLogUserEntity()
    LogUserEntity : +factorygetOnSuccessUserResgister()
```

#### Validaciones y mensajes.

    Las validaciones y mensajes de los DTO json y entidades de base dedatos fueron extrenalizadas a los archivos:

- validator-messages.properties
- patterns.properties
- error-messages.properties


#### GitFlow

    Se utilizo Gitfloy y se crearon las ramas:

- master
- develop
- release
- feature:
		- /configTecnologies
		- /registerUser

		
#### SWAGGER

  URL:
  http://localhost:8080/swagger-ui/index.html#/
