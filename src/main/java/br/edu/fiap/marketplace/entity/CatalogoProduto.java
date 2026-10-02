package br.edu.fiap.marketplace.entity;

import br.edu.fiap.marketplace.exception.CatalogoInativoException;
import br.edu.fiap.marketplace.exception.NovoPrecoInvalidoException;
import br.edu.fiap.marketplace.exception.ProdutoNaoPodeSerAtivoException;
import br.edu.fiap.marketplace.exception.QuantidadeInvalidaException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/** Produto anunciado no catálogo do marketplace. */
@Entity
@Table(name = "catalogo_produtos")
public class CatalogoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String nome;

    @Column(nullable = false, length = 300)
    private String descricao;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private boolean ativo;

    protected CatalogoProduto() {
    }

    public CatalogoProduto(
            String nome,
            String descricao,
            BigDecimal preco,
            Integer estoque,
            boolean ativo) {

        validarNovoPreco( preco );
        validarEstoque( estoque );

        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.estoque = estoque;
        this.ativo = ativo;
    }

    /** TODO rejeitar preço nulo, zero ou negativo. */
    public void alterarPreco(BigDecimal novoPreco) {
        validaCatalogoAtivo();
        validarNovoPreco( novoPreco );
        preco = novoPreco;
    }

    /** TODO diminuir o estoque sem permitir saldo negativo. */
    public void baixarEstoque(int quantidade) {
        if( quantidade <= 0|| quantidade > estoque ){
            throw new QuantidadeInvalidaException();
        }
        estoque -= quantidade;

    }

    /** TODO aceitar somente reposição positiva. */
    public void reporEstoque(int quantidade) {
        if( quantidade < 0 ) {
            throw new QuantidadeInvalidaException();
        }
        estoque += quantidade;
    }

    /** TODO disponibilizar o produto para compra. */
    public void ativar() {
        if( estoque <= 0 ) {
            throw new ProdutoNaoPodeSerAtivoException( id );
        }
        ativo = true;
    }

    /** TODO retirar o produto das novas compras. */
    public void desativar() {
        ativo = false;
    }

    private void validaCatalogoAtivo() {
        if( !ativo ) {
            throw  new CatalogoInativoException( id );
        }
    }

    private void validarNovoPreco( BigDecimal valor ) {
        if( valor == null || valor.compareTo( BigDecimal.ZERO ) <= 0 ) {
            throw new NovoPrecoInvalidoException();
        }
    }

    private void validarEstoque( Integer estoque ) {
        if( estoque == null || estoque < 0 ){
            throw new QuantidadeInvalidaException();
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public Integer getEstoque() { return estoque; }
    public boolean isAtivo() { return ativo; }
}
