import java.util.ArrayList;
import java.util.List;

class ProcessadorDePedidos {

    private BancoDeDadosMySQL banco;
    private ServicoEmailSMTP email;

    public ProcessadorDePedidos(ServicoEmailSMTP email, BancoDeDadosMySQL banco) {
        this.email = email;
        this.banco = banco;
    }

    public void processar(Pedido pedido) {
        double valorTotal = pedido.calcularTotal();

        valorTotal = pedido.tipoCliente.valorDesconto(valorTotal);

        pedido.valorFinal = valorTotal;

        banco.salvar(pedido);

        email.enviarConfirmacao(pedido.emailCliente, "Pedido confirmado no valor de R$ " + valorTotal);
    }
}

abstract class Pedido {
    Cliente tipoCliente;
    String emailCliente;
    List<Double> itens = new ArrayList<>();
    double valorFinal;

    public double calcularTotal(){
        double soma = 0;
        for (Double preco : itens) {
            soma += preco;
        }
        return soma + calculaFrete();
    }

    public abstract double calculaFrete();

}

class PedidoFisico extends Pedido {

    @Override
    public double calculaFrete() {
        return 25.0;
    }

}





class PedidoDigital extends Pedido {

    @Override
    public double calculaFrete() {
        return 0;
    }
}


interface processaCartao{
    void processarCartaoCredito(String numeroCartao, int parcelas);
}

interface geraBoleto{
    void gerarBoletoBancario(String cpf);
}

interface geraQr{
    void gerarQrCodePix();
}

interface TipoCliente {
    double valorDesconto(double valorTotal);
}

class PagamentoPix implements geraBoleto, geraQr {
    @Override
    public void gerarQrCodePix() {
        System.out.println("QR Code PIX gerado com sucesso.");
    }

    @Override
    public void gerarBoletoBancario(String cpf) {
        throw new UnsupportedOperationException("PIX não gera boleto!");
    }
}

class BancoDeDadosMySQL {
    public void salvar(Pedido p) {
        System.out.println("Salvando pedido no MySQL...");
    }
}

class ServicoEmailSMTP {
    public void enviarConfirmacao(String destinatario, String mensagem) {
        System.out.println("Enviando e-mail SMTP para " + destinatario + ": " + mensagem);
    }
}

