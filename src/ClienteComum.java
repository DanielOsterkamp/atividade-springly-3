public class ClienteComum extends Cliente implements TipoCliente {
    @Override
    public double valorDesconto(double valorTotal) {
        return 0.95;
    }
}
