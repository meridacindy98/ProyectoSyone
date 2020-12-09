# ProyectoSyone
## Crear un automovil 
POST: http://45.33.18.43:8080/automovil

**PARAMETROS:** 
- **tipoAutoId:** Id del tipo del autp
- **opcionalList:** Lista que contiene el opcional o los opcionales para el autmovil.No puede ingresarse opcionales repetidos.

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

Obtiene todos los automoviles.

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


## Borrar opcional u opcionales de un automovil
DELETE: http://45.33.18.43:8080/automovilOpcional


## Obtener las estadisticas
GET: http://45.33.18.43:8080/stats
##### RESPONSE: 
Status: 500




