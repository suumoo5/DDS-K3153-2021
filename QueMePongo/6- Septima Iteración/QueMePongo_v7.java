// ########### Ver todas las prendas que tengo en mi guardarropas desde el navegador para poder administrarlas ###########

#Ruta
GET guardarropa/prendas/

#BodyEnvio
No debería tener.

#CodigoRespuesta
200 - Solicitud exitosa, se muestra la prenda
400 - En caso de que el servidor no hubiera podido interpretar la solucitud
si hubiera una mala sintaxis o algo por el estilo
403 - Falta de permisos, en caso de que no sea su guardarropas por ejemplo
404 - No se pudo encontrar el contenido solicitado

#BodyRespuesta
Una lista de prendas
[
  {
    "id": 1,
    "Categoria": "UnaCategoria",
    "Color Primario": "UnColor",
    "Color Secundario": "OtroColor",
    "Material": "UnMaterial",
    "Trama": "UnaTrama",
  },
  .........
  El resto de las prendas
  .........
  {
  	Ultima prenda
  },
]


// ########### Crear una prenda desde el navegador ###########

#Ruta
POST /prendas/

#BodyEnvio

El id no debería estar, porque asumo que es autogenerado
{
	"Categoria": "UnaCategoria",
	"Color Primario": "UnColor",
	"Color Secundario": "OtroColor",
	"Material": "UnMaterial",
	"Trama": "UnaTrama",
}

#CodigoRespuesta
201 - En caso de que pueda crearse
400 - En caso de que el servidor no hubiera podido interpretar la solucitud
si hubiera una mala sintaxis o algo por el estilo

#BodyRespuesta

{
	"id": n,
	"Categoria": "UnaCategoria",
	"Color Primario": "UnColor",
	"Color Secundario": "OtroColor",
	"Material": "UnMaterial",
	"Trama": "UnaTrama",
}

// ########### Ver una prenda en particular que tengo en mi guardarropas para poder editarla ###########

#Ruta
PUT /guardarropa/prendas/1

#BodyEnvio
[
	{
		"CampoAEditar": "NuevoValor",
	  	....,
	  	"UltimoCampoAEditar":"Ultimo Nuevo Valor"
	}
]

#CodigoRespuesta
200 - Se logro actualizar la prenda
400 - El servidor no pudo interpretar la solicitud dada una sintaxis inválida.
403 - Falta de permisos, en caso de que no sea su guardarropas por ejemplo

#BodyRespuesta
Devuelve la prenda actualizada
[
	{
		"CampoAEditar": "NuevoValor",
	  	....,
	  	"UltimoCampoAEditar":"Ultimo Nuevo Valor"
	}
]

// ########### Eliminar una prenda de mi guardarropas ###########

#Ruta
DELETE guardarropa/prendas/1

#BodyEnvio
{
	id=n
}

#CodigoRespuesta
200 - En caso de borrarla
400 - El servidor no pudo interpretar la solicitud dada una sintaxis inválida.
403 - Falta de permisos, en caso de que no sea su guardarropas por ejemplo

#BodyRespuesta


// ########### Ver mis eventos para administrarlos ###########

#Ruta
GET /eventos/

#BodyGetPost
No tiene

#CodigoRespuesta
200 - En caso de devolver los eventos
400 - En caso de que el servidor no hubiera podido interpretar la solucitud
si hubiera una mala sintaxis o algo por el estilo
403 - Falta de permisos, en caso de que no pertenezca a ese evento, por ejemplo
404 - No se pudo encontrar el contenido solicitado, el evento en este caso

#BodyRespuesta
Tendría la información de los Eventos. En mi diagrama no los tengo diagramados,
asique no se que información contiene.

// ########### Abrir las sugerencias de prendas para un evento en mi navegador ########### 

#Ruta
GET /evento/sugerencias/

#BodyEnvio
No hay

#CodigoRespuesta
200 - Solicitud exitosa, se muestran las sugerencias
400 - En caso de que el servidor no hubiera podido interpretar la solucitud
si hubiera una mala sintaxis o algo por el estilo
403 - Falta de permisos, en caso de que no este permitido en el evento, por ejemplo
404 - No se pudo encontrar el contenido solicitado, en este caso, la sugerencia o el evento.

#BodyRespuesta
[
	{
		idSugerencia1: 1,
		idPrenda1: n1,
		idPrenda2: n2,
		...
		idPrendaN: nN
	}
	...
	{
		idSugenciaN: N,
		idPrenda1: nN1,
		idPrenda2: nN2,
		...
		idPrendaNN: nNN
	}
]



