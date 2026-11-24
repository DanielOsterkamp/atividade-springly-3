public class SistemaPedidosRuim {
    public static void main(String[] args) {
        ProcessadorDePedidos processador = new ProcessadorDePedidos(new ServicoEmailSMTP(),new BancoDeDadosMySQL());

        Pedido pedido1 = new PedidoFisico();
        pedido1.tipoCliente = new ClienteVip();
        pedido1.emailCliente = "ana@email.com";
        pedido1.itens.add(100.0);
        processador.processar(pedido1);

        Pedido pedido2 = new PedidoDigital();
        pedido2.tipoCliente = new ClienteComum();
        pedido2.emailCliente = "carlos@email.com";
        pedido2.itens.add(50.0);
        processador.processar(pedido2);
    }
}