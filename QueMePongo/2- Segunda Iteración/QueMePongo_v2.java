class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	ColorPrimario colorPrimario;
	ColorSecundario colorSecundario;

	public Prenda(TipoPrenda tipoPrenda){
		if(tipoPrenda == NULL){
			throw new PrendaInvalidaException("El tipo es inválido");
		}
		this.tipoPrenda = tipoPrenda;
		GUARDARROPA.guardarBorrador(this);
	}

	public aspectosDelMaterial(Material material, TramaMaterial tramaMaterial, ColorPrimario colorPrimario,ColorSecundario colorSecundario){
		if(material == null || colorPrimario == null){
			throw new PrendaInvalidaException("El material, su trama o el color primario es inválido.");
		}
		this.material = Material(tramaMaterial);
		setColorPrimario(colorPrimario);

		if(colorSecundario != null){setColorSecundario(colorSecundario)}

		if(tramaMaterial != null){
			this.material = Material(tramaMaterial);
		}else{
			this.material = Material(tramaMaterial.LISA);
		}
		GUARDARROPA.guardarBorrador(this);
	}

	public categoria(){ return tipoPrenda.categoria(); }

	public guardarPrenda(){
		if(esPrendaVálida()){
			GUARDARROPA.agregarPrenda(this);
		}
	}

	public esPrendaVálida(){
		return this.material != null && this.colorPrimario != null
	}
}

Object GUARDARROPA{
	List<Prenda> prendasValidas = new ArrayList<>();
	List<Prenda> prendasBorradores = new ArrayList<>();

	public void agregarPrendaValida(Prenda prenda){
		prendasValidas.add(prenda);
	}

	public void guardarBorrador(Prenda prenda){
		prendasBorradores.add(prenda);
	}
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

enum Material{
	TramaMaterial tramaMaterial;

	public Material(TramaMaterial trama){
		this.trama = trama;
	}
	
	public tramaMaterial(){
		return this.tramaMaterial;
	}

}

enum TramaMaterial{}
enum ColorPrimario{}
enum ColorSecundario{}
enum Categoria{
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS
}

enum Colegio{
	SANJUAN,
	JOHNSON
}

class Uniforme{
	Colegio colegio;
	Prenda parteSuperior;
	Prenda parteInferior;
	Prenda calzado;
	List<Strings> sugerencias;

	Uniforme(Prenda parteSuperior, Prenda parteInferior, Prenda calzado){
		if(parteSuperior.categoria() != PARTE_SUPERIOR){
			throw new UniformeInvalidoException("Parte Superior inválida");
		}
		if(parteInferior.categoria() != PARTE_INFERIOR){
			throw new UniformeInvalidoException("Parte Inferior inválida");
		}
		if(calzado.categoria()) != CALZADO{
			throw new UniformeInvalidoException("Calzado inválido");
		}

		this.parteSuperior = parteSuperior;
		this.parteInferior = parteInferior;
		this.calzado = calzado;
	}

	public agregarSugerencia(String sugerencia){
		sugerencias.add(sugerencia);
	}
}

/* # Exceptions*/
public PrendaInvalidaException extends RuntimeException {
  	public prendaInvalidaException(String motivo){super("La prenda es invalida. Motivo: " + motivo);}
}

public UniformeInvalidoException extends RuntimeException {
  	public uniformeInvalidaException(String motivo){super("El uniforme es invalido. Motivo: " + motivo);}
}
