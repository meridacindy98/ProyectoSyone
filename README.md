# ProyectoSyone

~~~
Una fábrica de automóviles produce uno de sus modelos en tres variantes, llamadas sedán, coupé y familiar. Cada una tiene un precio de venta básico sin opcionales. 
A su vez, a cada variante se le pueden agregar opciones como techo corredizo, aire acondicionado, sistema de frenos ABS, airbag y llantas de aleación. Cada uno de estos opcionales tiene un precio que se suma al básico. 
En este caso, cada auto vendrá caracterizado por su variante y podrá tener ninguno, uno o más opcionales. Asumiendo los siguientes costos:

Autos
• Básico sedán 230.000
• Básico familiar 245.000
• Básico coupé 270.000
Opcionales:
• Techo corredizo (TC) 12.000
• Aire acondicionado (AA) 20.000
• Sistemas de frenos ABS (ABS) 14.000
• Airbag (AB) 7.000
• Llantas de aleación (LL) 12.000

~~~

Para este proyecto se crearon 4 tablas

- **TipoAuto:** Posee los 3 modelos de autos disponibles
- **Opcional:** Posee los 5 opcionales que pueden agregarse a un automovil
- **Automovil:** Persiste el automovil
- **AutomovilOpcional:** Persiste los opcionales del automovil si este los posee



## Crear un automovil 
POST: http://45.33.18.43:8080/automovil

**PARAMETROS:** 
- **tipoAutoId:** Id del tipo del autp
- **opcionalList:** Lista que contiene el opcional o los opcionales para el autmovil.No puede ingresarse opcionales repetidos.

Ejemplo RequestBody:
~~~
{
    "tipoAutoId": "1",
    "opcionalList":[
        1,
        2
    ]
}
~~~

**RESPONSE:** 

**Status:** 201 Si se creo el automovil con exito.

Body: Devuelve los datos del nuevo automovil creado
~~~
{
    "automovil": {
        "automovilId": 5,
        "tipoAuto": {
            "tipoAutoId": 2,
            "nombre": "familiar",
            "precio": 245000
        },
        "precioFinal": 265000
    },
    "opcionalList": [
        {
            "opcionalId": 2,
            "codigo": "AA",
            "nombre": "Aire acondicionado",
            "precio": 20000
        }
    ]
}
~~~

**Status:** 500 Si se ingresan opcionales repetidos

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "No se aceptan opcionales duplicados",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

**Status:** 500 Si se ingresa un tipo de auto que no existe

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El tipo auto no existe.",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

**Status:** 500 Si se ingresa un opcional que no existe

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Uno de los opcionales ingresados no existe.",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~


## Obtener un automovil
GET: http://45.33.18.43:8080/automovil/{automovilId}

**PARAMETRO:** 
- **automovilId:** Id del automovil que deseo obtener.

**Status:** 200 Si se obtuvo el automovil conexito.

Body: Devuelve los datos del automovil.

~~~
{
    "automovil": {
        "automovilId": 2,
        "tipoAuto": {
            "tipoAutoId": 1,
            "nombre": "sedan",
            "precio": 230000
        },
        "precioFinal": 242000
    },
    "opcionalList": [
        {
            "opcionalId": 1,
            "codigo": "TC",
            "nombre": "Techo corredizo",
            "precio": 12000
        }
    ]
}
~~~

**Status:** 500 Si el automovil no existe.

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~


## Obtener todos los automoviles
GET: http://45.33.18.43:8080/automoviles


**Status:** 200 OK

Body: Devuelve los datos de los Automoviles
~~~
[
    {
        "automovilId": 3,
        "tipoAuto": {
            "tipoAutoId": 3,
            "nombre": "coupe",
            "precio": 270000
        },
        "precioFinal": 290000
    },
    {
        "automovilId": 4,
        "tipoAuto": {
            "tipoAutoId": 2,
            "nombre": "familiar",
            "precio": 245000
        },
        "precioFinal": 265000
    },
    {
        "automovilId": 5,
        "tipoAuto": {
            "tipoAutoId": 2,
            "nombre": "familiar",
            "precio": 245000
        },
        "precioFinal": 265000
    }
]
~~~

**Status:** 204 No Contetent Si no hay automoviles

## Borrar un automovil
DELETE: http://45.33.18.43:8080/automovil/{automovilId}

**PARAMETRO:** 
- **automovilId:** Id del automovil

**RESPONSE:** 

**Status:** 200 Si se borro el automovil con exito.

**Status:** 500 Si el automovil ingresado no existe

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

## Modificar tipo auto de un automovil
PUT: http://45.33.18.43:8080/automovil/{automovilId}/{tipoAutoId}

**PARAMETROS:** 
- **automovilId:** Id del automovil
- **tipoAutoId:** Id del nuevo tipo de auto

**RESPONSE:** 

**Status:** 200 Si se modifico con exito el tipo de auto

Body: El automovil actualizado
~~~
{
    "automovilId": 3,
    "tipoAuto": {
        "tipoAutoId": 3,
        "nombre": "coupe",
        "precio": 270000
    },
    "precioFinal": 290000
}
~~~

**Status:** 500 Si el automovil ingresado no existe

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

**Status:** 500 Si el tipo de auto ingresado no existe

Body: 
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El tipo de auto ingresado no existe.",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~


## Borrar opcional u opcionales de un automovil
DELETE: http://45.33.18.43:8080/automovilOpcionalDelete

**PARAMETROS:** 
- **automovilId:** Id del automovil
- **opcionalIdList:** Opcional u opcionales que se desean quitar del automovil

Ejemplo RequestBody:
~~~
{
    "automovilId":"1",
    "opcionalIdList":[
        1
    ]
}
~~~

**RESPONSE:** 

**Status:** 200 Si se borro el opcional u opcionales con exito

**Status:** 500 Si el automovil ingresado no existe
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

**Status:** 500 Si uno de los opcionales ingresados no existe para ese automovil.
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Uno de los opcionales ingresados no existe para este automovil",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

## Agregar opcional u opcionales de un automovil
POST: http://45.33.18.43:8080/automovilOpcional

**PARAMETROS:** 
- **automovilId:** Id del automovil
- **opcionalIdList:** Opcional u opcionales que se desean agregar del automovil

Ejemplo RequestBody:
~~~
{
    "automovilId":"1",
    "opcionalIdList":[
        1
    ]
}
~~~

**RESPONSE:** 

**Status:** 200 Si se agrego el opcional u opcionales con exito

**Status:** 500 Si el automovil ingresado no existe
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~

**Status:** 500 Si uno de los opcionales ingresados ya existe para ese automovil.
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Uno de los opcionales ingresados ya existe para este automovil",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~


## Obtener las estadisticas
GET: http://45.33.18.43:8080/stats

Obtiene una estadistica de la cantidad y porcentaje de cada tipo de auto y opcional que se utilizo para crear automoviles.

##### RESPONSE: 

Status: 200
~~~
{
    "count_car": 4,
    "cars": [
        {
            "model": "sedan",
            "count": 1,
            "percent": 25.0
        },
        {
            "model": "familiar",
            "count": 2,
            "percent": 50.0
        },
        {
            "model": "coupe",
            "count": 1,
            "percent": 25.0
        }
    ],
    "optionals": [
        {
            "optional": "Techo corredizo",
            "count": 1,
            "percent": 25.0
        },
        {
            "optional": "Aire acondicionado",
            "count": 3,
            "percent": 75.0
        },
        {
            "optional": "Sistemas de frenos",
            "count": 0,
            "percent": 0.0
        },
        {
            "optional": "Airbag",
            "count": 0,
            "percent": 0.0
        },
        {
            "optional": "Llantas de aleación",
            "count": 0,
            "percent": 0.0
        }
    ]
}
~~~




