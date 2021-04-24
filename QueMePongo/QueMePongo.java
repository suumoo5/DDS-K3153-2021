Class Prenda{
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

Enum TipoPrenda{
	Categoria categoria;
	public TipoPrenda(Categoria categoria);

	public categoria(){
		return this.categoria;
	}

	ZAPATOS(CALZADO)
	CAMISADEMANGASCORTAS(PARTE_SUPERIOR)
	PANTALON(PARTE_INFERIOR)
}

Enum Material{}
Enum ColorPrimario{}
Enum ColorSecundario{}
Enum Categoria{
	PARTE_SUPERIOR;
	CALZADO;
	PARTE_INFERIOR;
	ACCESORIOS
}