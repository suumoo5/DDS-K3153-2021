class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	double temperaturaAdecuada;
	CriterioDeRopa criterioDeRopa;

	public Prenda(TipoPrenda tipoPrenda, Material material, Color colorPrimario, Color colorSecundario,Trama trama, double temperaturaAdecuada){
		this.tipoPrenda = tipoPrenda;
		this.material = material;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.temperaturaAdecuada = temperaturaAdecuada;
	}

	public getCategoria(){return tipoPrenda.getCategoria();}
	public esTemperaturaAcorde(CondicionesClimaticas condicionesClimaticas){
		return getTemperaturaAdecuada() > condicionesClimaticas.getTemperaturaMinima() 
				&& getTemperaturaAdecuada() < condicionesClimaticas.getTemperaturaMaxima();
	}
}

class Borrador{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama = Trama.LISA;
	double temperaturaAdecuada;

	public void Borrador(tipoDePrenda){
       validateNonNull(tipoDePrenda)
       this.tipoDePrenda = tipoDePrenda
	}

	public void especificarMaterial(material){
       validateNonNull(material) 
       this.validarMaterialConsistenteConTipoDePrenda(material) 
       this.material = material
	}

    public void especificarColorPrincipal(color){
       validateNonNull(color) 
       this.colorPrincipal = color
   }

    public void especificarTrama(trama){
        if(trama == null){
        	this.trama = Trama.LISA;
        }else{
        	this.trama = trama;
		}
	}
	public Prenda crearPrenda(){
		if(esPrendasValida()){
			return new Prenda(tipoPrenda, material, colorPrimario, colorSecundario, tramaMaterial)
		}
	}

	public esPrendaVálida(){ return this.material != null && this.colorPrimario != null && this.trama != null}

	public void setMaterial(Material material){this.material = material}
	public void setColorPrimario(Color colorPrimario){this.colorPrimario = colorPrimario}
	public void setColorSecundario(Color colorSecundario){this.colorSecundario = colorSecundario}
	public void setTrama(Trama trama){this.trama = trama}
	public void setTemperaturaAdecuada(double temperaturaAdecuada){this.temperaturaAdecuada = temperaturaAdecuada}
}

enum TipoPrenda{
	Categoria categoria;
	public TipoPrenda(Categoria categoria);

	public categoria(){
		return this.categoria;
	}

	ZAPATOS(Categoria.CALZADO),
	CAMISADEMANGASCORTAS(Categoria.PARTE_SUPERIOR),
	PANTALON(Categoria.PARTE_INFERIOR)
}

enum Categoria{
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS
}
/*
Borro lo que no sea de la Entrega 5, para mejor entendimiento.

 ###  5Ta ENTREGA   ##
No me gusta del todo implementar un Usuario, por lo que representa, pero creo que ahora es mas notable que es necesario, debido a que tenemos distintas personas con 
distintos guardarropas, Y ADEMAS, se pueden compartir entre ellos; tambien se le suma que podríamos listar las Sugerencias en el Usuario.
(Usuario bien podría llamarse Presona, Modelo, etc.)

Preferí que un Usuario conozca a sus guardarropas compartidos que al revés.
A su vez, siento que hay una abstracción de los Guardarropas propios y los Guardarropa compartidos que no estoy viendo

Me di cuenta que un Guardarropa ya no es una clase que se construye tan facilmente, asique delegue a un pseudo-builder el comportamiento de construcción de la clase y
dejo en Guardarropa cualquier comportamiento que nos ea el de "constuir"

Las Sugerencias las tome como un comportamiento parecido a un Command, mas que nada porque "necesito ver todas las propuestas de modificación (agregar o quitar prendas) 
del guardarropas y poder aceptarlas o rechazarlas..", pero nunca indica en que tiempo, hoy? mañana? en un mes? .
Siguiendo el ejemplo del banco, esto tendría similitud, tengo Sugerencias y cuando yo quiera, las apruebo o rechazo de manera asincronica.

Suposiciones.
Supuse que no tiene sentido que existan dos prendas iguales. Pude haber asumido mal.
Supuse que tiene sentido que haya varias sugerencias iguales, porque quizá un es una prenda que está de moda o que va muy bien con mi guardarropa y podría darle prioridad
o mas atención a la sugerencia de esa prenda.
*/

public class Usuario{
	List<Guardarropa> guardarropas;
	List<Guardarropa> guardarropasCompartidos;

	public List<SugerenciasDeUsuarios> listarPropuestasDeModificación(){
		guardarropas.forEach(guardarropas -> guardarropas.getSugerencias());
	}
}

public class GuardarropaBuilder{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    CriterioDeRopa criterio;

    public GuardarropaBuilder(CriterioDeRopa criterioDeRopa){
    	this.criterioDeRopa = criterioDeRopa;
    }

	public agregarPrendasSuperiores(Prenda prendas){
		corroborarPrenda(prenda, PARTE_SUPERIOR);
		prendasSuperiores.add(prendas);
    }

	public agregarPrendasInferiores(Prenda prenda){
		corroborarPrenda(prenda, PARTE_INFERIOR);
		prendasInferiores.add(prendas);
    }

	public agregarCalzados(Prenda prenda){
		corroborarPrenda(prenda, CALZADO);
		calzados.add(prendas);
    }

	public agregarAccesorios(Prenda prendas){
		corroborarPrenda(prenda, ACCESORIOS)
		accesorios.add(prendas);
	}

	public corroborarPrenda(Prenda prenda, Categoria categoria){
		if(prenda.getCriterioDeRopa() =! this.categoriaDeRopa || prenda.getCategoria() =! categoria)
			throw new PrendaNoSePuedeAgregarAlGuardarropaException("No se pudee agregar la prenda al guardarropa");
	}

    public generarGuardarropa(){
    	return new Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio);
    }
}


public class Guardarropas{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    List<SugerenciasDeUsuarios> sugerenciasDeUsuarios;
    CriterioDeRopa criterio;


    public Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio){
    	this.prendasSuperiores = prendasSuperiores
    	this.prendasInferiores = prendasInferiores
    	this.calzados = calzados
    	this.accesorios = accesorios
    	this.criterio = criterio
    }

    public void agregarPrenda(Prenda prenda){
    	
    }

   	public boolean existe(Prenda prenda){
   		return prendasSuperiores.contains(prenda) 
   			|| prendasInferiores.contains(prenda) 
   			|| calzado.contains(prenda) 
   			|| accesorios.contains(prenda);
   	}

   	public boolean cumpleCriterio(Prenda prenda){
   		if (prenda.getCriterioDeRopa != this.criterio) 
   			throw new CriterioDeRopaDePrendaNoEsIgualAlDeGuardarropaException("El criterio de ropa de la prenda es distinto al del guardarropa");
   	}

   	public agregarSugerencia(SugerenciasDeUsuarios sugerencia){
   		sugerenciasDeUsuarios.add(Sugerencia);
   	}

    public resolverSugerencia(SugerenciasDeUsuarios sugerencia){ sugerencia.ejecutar(this); }
    public deshacerSugerencia(Sugerencia sugerencia){ sugerencia.deshacer(this); }
   	public List<SugerenciasDeUsuarios> getSugerenciasDeUsuarios(){ return sugerenciasDeUsuarios; }
}

public enum CriterioDeRopa{
	ROPA_DE_VIAJE,
	ROPA_DE_ENTRECASA,
	/*etc*/
} 


public interface SugerenciasDeUsuarios{
	public ejecutar(Guardarropa guardarropa);
	public deshacer(Guardarropa guardarropa);
}

public class AgregarPrendaDeSugerencia implements SugerenciasDeUsuarios{
	Prenda prenda;
	int cantidadDeVecesSugeridas;
	boolean ejecutado = false;

	public AgregarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
		this.cantidadDeVecesSugeridas = 0;
	}

	public ejecutar(Guardarropa guardarropa){
		if(guardarropa.existe(this.prenda)
			throw new PrendaYaExisteEnGuardarropaException("Prenda ya existe en guardarropa.");
		guardarropa.cumpleCriterio(Prenda prenda);
		guardarropa.agregarPrenda(this.prenda);
		ejecutado = true;	//horrible
		cantidadDeVecesSugeridas++;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado){
			guardarropa.remove(prenda);
		} else {
			throw new NoSeEjecutoAgregarPrendaDeSugerenciaException("La prenda necesita estar presente en el guardarropa para eliminarla");
		}
	}
}

public class QuitarPrendaDeSugerencia implements SugerenciasDeUsuarios{
	Prenda prenda;
	int cantidadDeVecesSugeridas;
	boolean ejecutado = false;

	public QuitarPrendaDeSugerencia(Prenda prenda){
		this.prenda = prenda;
		this.cantidadDeVecesSugeridas = 0;
	}

	public ejecutar(Guardarropa guardarropa){
		if (guardarropa.existe(prenda)){  
			guardarropa.remove(this.prenda);
		} else {
			PrendaNoExisteEnGuardarropaException("Prenda no existe en guardarropa.");
		}
		ejecutado = true;	//horrible
		cantidadDeVecesSugeridas++;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado)
			guardarropa.add(this.prenda);	
	}
}

