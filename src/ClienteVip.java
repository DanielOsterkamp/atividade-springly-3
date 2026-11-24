public class ClienteVip extends Cliente implements TipoCliente {
    @Override
    public double valorDesconto(double valorTotal) {
        return valorTotal*.8;
    }
}
