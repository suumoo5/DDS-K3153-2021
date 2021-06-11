class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	Color colorPrimario;
	Color colorSecundario;
	Trama trama;
	double temperaturaAdecuada;

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



public class Usuario{
	List<Guardarropa> guardarropas;
	String mail;

	public List<SugerenciasDeUsuarios> listarPropuestasDeModificación(){
		guardarropas.forEach(guardarropas -> guardarropas.getSugerencias());
	}

	public String getMail(){return mail;}
}

public class Guardarropas{
    List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    List<Prenda> accesorios;
    List<PropuestaDeUsuarios> propuestasDeUsuarios;

    public Guardarropas(prendasSuperiores, prendasInferiores, calzados, accesorios, criterio){
    	this.prendasSuperiores = prendasSuperiores
    	this.prendasInferiores = prendasInferiores
    	this.calzados = calzados
    	this.accesorios = accesorios
    	this.criterio = criterio
    }

	public rechazarPropuesta(PropuestaDeUsuarios propuesta){ propuestasDeUsuarios.remove(propuesta);}
   	public agregarPropuesta(PropuestaDeUsuarios propuesta){ propuestasDeUsuarios.add(propuesta);}
    public resolverPropuesta(PropuestaDeUsuarios propuesta){ propuesta.ejecutar(this); }
    public deshacerPropuesta(PropuestaDeUsuarios propuesta){ propuesta.deshacer(this); }
   	public List<PropuestaDeUsuarios> getPropuestasDeUsuarios(){ return propuestasDeUsuarios; }

   	public List<Prenda> getTodasLasPrendas(){
   		//devuelve todas las prendas en una lista List<Prenda>
   	}
}

public interface PropuestaDeUsuarios{
	public ejecutar(Guardarropa guardarropa);
	public deshacer(Guardarropa guardarropa);
}

public class AgregarPrendaDePropuesta implements PropuestaDeUsuarios{
	Prenda prenda;
	boolean ejecutado;

	public AgregarPrendaDePropuesta(Prenda prenda){ 
		this.prenda = prenda;
		this.ejecutado = false;
	}

	public ejecutar(Guardarropa guardarropa){
		guardarropa.add(this.prenda);
		this.ejecutado = true;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado)
			guardarropa.remove(this.prenda);
	}
}

public class QuitarPrendaDePropuesta implements PropuestaDeUsuarios{
	Prenda prenda;

	public QuitarPrendaDePropuesta(Prenda prenda){ this.prenda = prenda; }

	public ejecutar(Guardarropa guardarropa){
		guardarropa.remove(this.prenda);
		this.ejecutado = true;
	}

	public deshacer(Guardarropa guardarropa){
		if(ejecutado)
			guardarropa.add(this.prenda);
	}
}

// ############################################################################
//Reminder: Este es nuestra sugerencia.
public class Atuendo{
	Prenda parteSuperior;
	Prenda parteInferior;
	Prenda calzado;
	Prenda accesorio;

	public boolean aptaParaTemperatura(CondicionesClimaticas temp){
		return parteSuperior.esTemperaturaAcorde(temp)
			&& parteInferior.esTemperaturaAcorde(temp)
			&& calzado.esTemperaturaAcorde(temp)
			&& accesorios.esTemperaturaAcorde(temp)
	}
}

public interface GeneradorDeSugerencias{
	List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas);
}

//Hace el producto cartesiano entre todas las perndas para formar los atuendos. No lo implemente porque es de la entrega 3.
public class GeneradorDeSugerenciaPosta implements GeneradorDeSugerencias{
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas){}	
}

//Tiene la logica de generera un atuendo conforme al estado del tiempo, por ahora
public class AsesorDeImagen{
	private GeneradorDeSugerencias generadorDeSugerencias;
	private ServicioMeteorologico servicioMeteorologico;
	private Guardarropa guardarropa;

	public Atuendo sugerirAtuendo(String direccion) {
		EstadoDelTiempo estadoDelTiempo = servicioMeteorologico.obtenerCondicionesClimaticas(direccion);
		List<Atuendo> atuendoCombinacion = generadorDeSugerencias.todasLasPosiblesCombinaciones(todasLasPrendasDelGuardarropa())
		return atuendoCombinacion
			.filter(atuendo -> atuendo.aptaParaTemperatura(estadoDelTiempo.temperatura))
			.filter(atuendo -> atuendo.aptaParaHumedad(estadoDelTiempo.humedad))
			.first();
	}

	public List<Prenda> todasLasPrendasDelGuardarropa(){
		return guardarropa.getTodasLasPrendas();
	}
}

public interface ServicioMeteorologico {
   EstadoDelTiempo obtenerCondicionesClimaticas(String direccion);
}

public class ServicioMeteorologicoAccuWeather {
	private AccuWeatherAPI api;
	private List<EstadoDelTiempo> ultimasRespuestas;
  	private LocalDateTime proximaExpiracion;
  
	public void ServicioMeteorologico(AccuWeatherAPI api, LocalDateTime proximaExpiracion) { 
		this.api = api;                
		this.proximaExpiracion = proximaExpiracion;
		this.ultimasRespuestas = new ArrayList<>();
	}

	public EstadoDelTiempo obtenerCondicionesClimaticas(String direccion) {
		if (getUltimaRespuesta() == null || this.expiro()) {
			EstadoDelTiempo nuevaUltimaRespuesta = consultarApi(direccion);

			if(nuevaUltimaRespuesta.getAlert() == getUltimaRespuesta().getAlert()){
				//Generar nueva sugerencia (Atuendo)
			}

			this.proximaExpiracion = LocalDateTime.now().plus(this.proximaExpiracion);
			ultimasRespuestas.add(this.ultimaRespuesta); 
		}
		
		return this.ultimaRespuesta;
	}

	private boolean expiro() { return !proximaExpiracion.isAfter(DateTime.now); }
	
	private EstadoDelTiempo consultarApi(String direccion) {
	    EstadoDelTiempo respuesta = this.api.getWeather(direccion).get(0);
	    String alerta = this.api.getAlert(direccion).get(0);
	    int temperatura = (int) respuesta.get(...); 
	    Humedad humedad = (double) respuesta.get(...) > 0.8 ? LLUVIOSO : SECO;
	    return new EstadoDelTiempo(temperatura, humedad, alerta);
  }

  public EstadoDelTiempo getUltimaRespuesta(){return ultimasRespuestas.last()} //no se si se llama asi, solo quiero denotar que busco el ultimo agregado
  public List<EstadoDelTiempo> getUltimasRespuestas(){return ultimasRespuestas;}
}

public class EstadoDelTiempo{
	DateTime fechaDeRegistro;
	int temperatura;
	String alertaMeteorológica;
	List<Notificador> notificadores;
	Humedad humedad;

	public EstadoDelTiempo(int temp, Humedad hum, String alert){
		this.fechaDeRegistro = Datetime.now();
		this.temperatura = temp;
		this.hum = humedad;
		this.alerta = alert;
		this.notificadores = new arrayList<>();
	}

	public void notificar(){
		notificadores.clone()
				.forEach(notificador -> notificador.notificar());
	}

	public void agregarNotificador(Notificador notificador){ notificadores.add(notificador);}
	public void eliminarNotificador(Notificador notificador){ notificadores.remove(notificador); }
}

public interface Notificador{
	public void notificar();
}

public class MailSender implements Notificador{
	String mail;
	String mensaje;

	public void notificar(){
		send(this.mail, this.mensaje);
	}
}

public class NotificationService implements Notificador{
	String mensaje;

	public void notificar(){
		notify(mensaje);
	}
}

public class ActualizadorDePrenda implements Notificador{
	AsesorDeImagen asesorDeImagen;
	String direccion;

	//pasamanos, pero quizá tenga sentido si lo pienso como algo que activa o funciona de trigger.
	public void notificar(){
		asesorDeImagen.sugerirAtuendo(direccion);
	}
}
