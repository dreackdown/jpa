package br.com.alura.loja.dao;

import br.com.alura.loja.dto.RelatorioDeVendasDto;
import br.com.alura.loja.dto.VendasPorDiaDto;
import br.com.alura.loja.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {

    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }

    public List<RelatorioDeVendasDto> relatorioDeVendas() {
        String jpql = "SELECT new br.com.alura.loja.dto.RelatorioDeVendasDto("
                + "produto.nome, "
                + "SUM(item.quantidade), "
                + "MAX(pedido.data)) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens item "
                + "JOIN item.produto produto "
                + "GROUP BY produto.nome "
                + "ORDER BY item.quantidade DESC";
        return em.createQuery(jpql, RelatorioDeVendasDto.class)
                .getResultList();
    }

    public Pedido buscarPedidoComCliente(Long id) {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = :id", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<VendasPorDiaDto> vendasPorDia() {
        String jpql = "SELECT new br.com.alura.loja.dto.VendasPorDiaDto("
                + "produto.nome, "
                + "sum(pedido.valorTotal), "
                + "pedido.data) "
                + "FROM Pedido pedido "
                + "JOIN pedido.itens itens "
                + "JOIN itens.produto produto "
                + "GROUP BY produto.nome, pedido.data";
        return em.createQuery(jpql, VendasPorDiaDto.class)
                .getResultList();
    }
}
