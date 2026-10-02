package br.edu.fiap.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Representa uma escolha simples de produto, quantidade e usuário.
 * Cada registro corresponde a um produto no carrinho do desafio.
 */
@Entity
@Table(name = "carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private CatalogoProduto produto;

    @Column(nullable = false)
    @Min(0)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusCarrinho status;

    @Column(name = "criado_em", nullable = false)
    private Instant criadoEm;

    protected Carrinho() {
    }

    public Carrinho(Usuario usuario, CatalogoProduto produto, Integer quantidade) {
        this.usuario = usuario;
        this.produto = produto;
        this.quantidade = quantidade;
        this.status = StatusCarrinho.ABERTO;
        this.criadoEm = Instant.now();
    }

    public void alterarQuantidade(int novaQuantidade) {
        if (!status.equals(StatusCarrinho.ABERTO)) {
            throw new IllegalStateException("Não é possível alterar quantidade em carrinho que não esteja aberto.");
        }

        if (novaQuantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser menor que zero.");
        }

        this.quantidade = novaQuantidade;
    }

    public BigDecimal calcularTotal() {
        return produto.getPreco().multiply(new BigDecimal(quantidade));
    }

    public void finalizar() {
        if (!status.equals(StatusCarrinho.ABERTO)) {
            throw new IllegalStateException("Não é possível finalizar um carrinho que não esteja aberto.");
        }
        this.status = StatusCarrinho.FINALIZADO;
    }

    public void cancelar() {
        if (!status.equals(StatusCarrinho.ABERTO)) {
            throw new IllegalStateException("Somente carrinho aberto pode ser cancelado.");
        }
        this.status = StatusCarrinho.CANCELADO;
    }

    public Long getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public CatalogoProduto getProduto() { return produto; }
    public Integer getQuantidade() { return quantidade; }
    public StatusCarrinho getStatus() { return status; }
    public Instant getCriadoEm() { return criadoEm; }
}
