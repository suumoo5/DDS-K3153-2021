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

enum Material{}
enum TramaMaterial{}

class Color{
	int nivelRojo;
	int nivelAmarillo;
	int nivelAzul;
}

enum Categoria{
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS
}

/*Elijo la solución basada en composición y Abstract Factory. Si bien en la clase Uniforme, ahora Atuendo,
queda 100% comportamiento y tiene el metodo de clase, no me ato a la herencia y en "gastar
una chance"; Sastre queda mas expresivo sobre lo que está haciendo; 

*/

// Unifrome, Atuendo, Sugerencia --> Misma estructura 

class Atuendo{
    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda calzado;

    static fabricar(Sastre sastre){
       new Atuendo(
          sastre.fabricarParteSuperior(),
          sastre.fabricarParteInferior(),
          sastre.fabricarCalzado())
   }
}

interface Sastre{
    public fabricarParteSuperior()
    public fabricarParteInferior()
    public fabricarCalzado()
}

class SastreSanJuan implements Sastre{
     public fabricarParteSuperior(){
       borrador = new Borrador(CHOMBA)
       borrador.especificarColor(new Color(0,100,))
       borrador.especificarMaterial(PIQUE)
       return borrador.crearPrenda()           
     }
    public fabricarParteInferior(){
      borrador = new Borrador(PANTALON)
      borrador.especificarColor(new Color(....))
      borrador.especificarMaterial(ACETATO)
      return borrador.crearPrenda()   
	}
	public fabricarCalzado(){
		borrador  =new Borrador(ZAPATILLAS)
		borrador.especificarColor(new Color(0,0.0))
		borrador.especificarMaterial()
		return borrador.crearPrenda()
	}
}


//Paso de la entrega 3, uso una interfaz y una clase para asumir el comportamiento
//de la generacion por producto cartesiano de los Atuendos
interface GeneradorDeSugerencias{
	List<Prenda> prendasSuperiores;
    List<Prenda> prendasInferiores;
    List<Prenda> calzados;
    
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas);
}

class GeneradorDeSugerenciaPosta implements GeneradorDeSugerencias{
	List<Atuendo> generarSugerencias(List<Prenda> prendasAptas){
		//Codigo
	}	
}

//En caso de que se necesite, podria crear una interfaz con los distintos criterios que se necesiten
//y hacer polimorfico con un "filtrarPrendasCritrio()"

class SugerenciaDeClima{
	GeneradorDeSugerencias generadorDeSugerencias;

	public List<Atuendo> filtrarPrendasClima(List<Prenda> prendas){
		if(sonPrendasAdecuadasAlClima(prendas))
		return generadorDeSugerencias.generarSugerencias(prendas);
	}

	public boolean sonPrendasAdecuadasAlClima(List<Prenda> prendas){  
		return prendas.filter(prenda -> prenda.esTemperaturaAcorde(temperaturaActual()) == true);
	}

	public double temperaturaActual(){
		List<Map<String, Object>> condicionesClimaticas = AccuWeatherAPI.getInstance().getWeather(“Buenos Aires, Argentina”);
		return condicionesClimaticas.get(0).get("Temperature").get(0); //Creo que asi llamaría a la temperatura
	}
}

public interface ApiClima{
	public abstract getWeather();
}


public final class AccuWeatherAPI implements ApiClima{
	int intentos = 0;

    public final List<Map<String, Object>> getWeather(String ciudad) {
		if(intentos <= 10){
			return Arrays.asList(new HashMap<String, Object>(){{
			put("DateTime", "2019-05-03T01:00:00-03:00");
			put("EpochDateTime", 1556856000);
			put("WeatherIcon", 33);
			put("IconPhrase", "Clear");
			put("IsDaylight", false);
			put("PrecipitationProbability", 0);
			put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
			put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
			put("Temperature", new HashMap<String, Object>(){{
				put("Value", 57);
				put("Unit", "F");
				put("UnitType", 18);
			}});
		}});
			intentos++;
	}else{
		decimoLlamadoDiarioException("Ya has corroborado mas de 10 veces el tiempo. Espera hasta mañana.");
	}
}

/* # Exceptions*/
public PrendaInvalidaException extends RuntimeException {
  	public prendaInvalidaException(String motivo){super("La prenda es invalida. Motivo: " + motivo);}
}

public UniformeInvalidoException extends RuntimeException {
  	public uniformeInvalidaException(String motivo){super("El uniforme es invalido. Motivo: " + motivo);}
}

public DecimoLlamadoDiarioException extends RuntimeException {
  	public decimoLlamadoDiarioException(String motivo){super(motivo);}
}
