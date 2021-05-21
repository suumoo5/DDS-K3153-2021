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


/*Elijo la solución basada en composición y Abstract Factory. Si bien en la clase Uniforme
queda 100% comportamiento y tiene el metodo de clase, no me ato a la herencia y en "gastar
una chance". Tambien, Sastre, queda mas expresivo sobre lo que está haciendo.

*/
class Uniforme{
    Prenda prendaSuperior;
    Prenda prendaInferior;
    Prenda calzado;

    static fabricar(Sastre sastre){
       new Uniforme(
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

// ### CUARTA ENTREGA ###

//Quiero poder recibir sugerencias de atuendos que tengan una prenda para cada
//categoría, aunque a futuro podrán tener más

class Atuendo{
	List<Prenda> prendas; 

	public Atuendo(List<Prenda> ... unasPrendas){
		categoríasDePrenda = unasPrendas.forEach(prenda -> prenda.getCategoria);
		unasPrendas.forEach(prenda -> prenda.getCategoria().)
	}

	public sonPrendasQueNoRepitenCategoria(List<Prenda> ... unasPrendas){
		
	}
}

class Sugerencia{
	Atuendo atuendo;

	public generarSugerencia(List<Prenda> prendas){
		if(esAtuendoAdecuado(prendas))
		return new Atuendo(prendas);
	}

	public boolean esAtuendoAdecuado(List<Prenda> prendas){  
		return prendas.forEach(prenda -> prenda.esTemperaturaAcorde(temperaturaActual()) == true);
	}

	public double temperaturaActual(){
		List<Map<String, Object>> condicionesClimaticas = AccuWeatherAPI.getInstance().getWeather(“Buenos Aires, Argentina”);
		return condicionesClimaticas.get(0).get("Temperature").get(0);
	}
}

public interface ApiClima{
	public abstract getWeather();
}


public final class AccuWeatherAPI implements ApiClima{

    public final List<Map<String, Object>> getWeather(String ciudad) {
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
	}
}

/* # Exceptions*/
public PrendaInvalidaException extends RuntimeException {
  	public prendaInvalidaException(String motivo){super("La prenda es invalida. Motivo: " + motivo);}
}

public UniformeInvalidoException extends RuntimeException {
  	public uniformeInvalidaException(String motivo){super("El uniforme es invalido. Motivo: " + motivo);}
}
