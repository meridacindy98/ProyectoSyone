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


## Obtener un automovil
GET: http://45.33.18.43:8080/automovil/{automovilId}

Ingresar el Id de automovil que se desea eleminar.

## Obtener todos los automoviles
GET: http://45.33.18.43:8080/automoviles

Obtener todos los automoviles.

## Borrar un automovil
DELETE: http://45.33.18.43:8080/automovil/{automovilId}

**PARAMETRO:** automovilId

**RESPONSE**

Status: 200 Si se borro con exito.

Status: 500 Si se ingreso un automovil que no existe.

Body
~~~
{
    "status": "INTERNAL_SERVER_ERROR",
    "message": "El automovil ingresado no existe",
    "errors": [
        "Ocurrio un error"
    ]
}
~~~



## Modificar un automovil
PUT: http://45.33.18.43:8080/automovil/{automovilId}/{tipoAutoId}


## Borrar opcional u opcionales de un automovil
DELETE: http://45.33.18.43:8080/automovilOpcional


## Obtener las estadisticas
GET: http://45.33.18.43:8080/stats
##### RESPONSE: 
Status: 500




