# ProyectoSyone
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


**Status:** 200

Body: Devuelve los datos del automovil.
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


## Borrar opcional u opcionales de un automovil
DELETE: http://45.33.18.43:8080/automovilOpcional

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

**Status:** 200 Si se borro el opcional con exito

**Status:** 500 Si uno de los opcionales ingresados no existe para ese automovil.
Body: El automovil actualizado
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "Uno de los opcionales ingresados no existe para este automovil",
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




