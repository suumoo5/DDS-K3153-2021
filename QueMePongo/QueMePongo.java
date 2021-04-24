class Prenda{
	TipoPrenda tipoPrenda;
	Material material;
	ColorPrimario colorPrimario;
	ColorSecundario colorSecundario;

	public Prenda(Tipo t, Material m, ColorPrimario cp, ColorSecundario cs){
		this.tipoPrenda = t;
		this.material = m;
		this.colorPrimario = cp;
		this.colorSecundario = cs;

		if(!prendaValida()) {
			throw new PrendaInvalidaException();
  		}
	}

	public prendaValida(){
		return (getTipo() != null &&
				getMaterial() != null && 
				getColorPrimario() != null)
	}

	public categoria(){
		return tipoPrenda.categoria();
	}

}

public PrendaInvalidaException extends RuntimeException {
  	public PrendaInvalidaException(){super("La prenda es invalida");}
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
enum ColorPrimario{}
enum ColorSecundario{}
enum Categoria{
	PARTE_SUPERIOR,
	CALZADO,
	PARTE_INFERIOR,
	ACCESORIOS
}