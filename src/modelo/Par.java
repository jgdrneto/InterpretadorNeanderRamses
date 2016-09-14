package modelo;

public class Par<P,S> {
    private P primeiro;
    private S segundo;

    /*	Construtor da classe
     *
     * 	@params P Primeiro termo do par
     * 	@params S Segundo termo do par
     *
     */
    public Par(P nPrimeiro, S nSegundo) {
        super();
         this.primeiro = nPrimeiro;
         this.segundo = nSegundo;
    }

    @Override
    public boolean equals(Object obj){
        //Verifica se os objetos s�o iguais
        if(obj instanceof Par){

            Par<?,?> outroPar = (Par<?,?>)obj;

            //Verifica se os tamplates sao do mesmo tipo
            if(this.getPrimeiro().getClass()==outroPar.getPrimeiro().getClass() && this.getSegundo().getClass()==outroPar.getSegundo().getClass()){
                //Verifica se o conteudo deles sao iguais
                return (( this.primeiro == outroPar.primeiro || ( this.primeiro != null && outroPar.primeiro != null && this.primeiro.equals(outroPar.primeiro)))
                    && (this.segundo == outroPar.segundo || (this.segundo != null && outroPar.segundo != null && this.segundo.equals(outroPar.segundo))));
            }

            return false;
        }
        return false;
    }

    @Override
    public String toString(){
               return "(" + primeiro + ", " + segundo + ")";
    }

    /*	Metodo para retornar o primeiro item do par
     *
     *  @return P Primeiro item do par
     */
    public P getPrimeiro() {
        return primeiro;
    }

    /*	Metodo para adicionar um novo objeto no primeiro item do par
     *
     *  @params P Novo objeto para o primeiro item do par
     */
    public void setPrimeiro(P primeiro) {
        this.primeiro = primeiro;
    }

    /*	Metodo para retornar o segundo item do par
     *
     *  @return S Segundo item do par
     */
    public S getSegundo() {
        return segundo;
    }

    /*	Metodo para adicionar um novo objeto no segundo item do par
     *
     *  @params S Novo objeto para o segundo item do par
     */
    public void setSegundo(S segundo) {
        this.segundo = segundo;
    }


}
