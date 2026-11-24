public class Funcionario extends Cliente implements TipoCliente {
    @Override
    public double valorDesconto(double valorTotal) {
        return valorTotal*0.5;
    }
}
