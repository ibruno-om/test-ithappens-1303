-- QUANTIDADE DE PRODUTOS MAIOR OU IGUAL A 100

SELECT F.NOME AS NOME_FILIAL, P.NOME, I.QUANTIDADE
  FROM ESTOQUE E 
  	INNER JOIN ITEM_ESTOQUE I 
  		ON I.ESTOQUE_ID = E.ID
  	INNER JOIN PRODUTO P
  		ON P.ID = I.PRODUTO_ID
  	INNER JOIN FILIAL F
  		ON F.ID = E.FILIAL_ID
 WHERE I.QUANTIDADE >= 100
 ORDER BY F.NOME, P.NOME
 
-- TODOS OS PRODUTOS DE FILIAL CODIGO 60 QUE POSSUEM ESTOQUE

  SELECT P.* 
  FROM ESTOQUE E 
  	INNER JOIN ITEM_ESTOQUE I 
  		ON I.ESTOQUE_ID = E.ID
  	INNER JOIN PRODUTO P
  		ON P.ID = I.PRODUTO_ID
  	INNER JOIN FILIAL F
  		ON F.ID = E.FILIAL_ID
 WHERE I.QUANTIDADE > 0
   AND F.ID = 60
 ORDER BY P.NOME

-- DADOS DO PEDIDO E ITENS DO PEDIDO PARA O PRODUTO DE CÓDIGO 7993

SELECT * 
  FROM PEDIDO_ESTOQUE PE
  	INNER JOIN ITEM_PEDIDO I 
  		ON I.PEDIDO_ESTOQUE_ID = PE.ID
 WHERE I.PRODUTO_ID = 7993
 ORDER BY PE.ID

-- DADOS DO PEDIDO COM FORMA DE PAGAMENTO

 SELECT P.*, C.FORMA_PAGAMENTO
   FROM PEDIDO_ESTOQUE P
   	INNER JOIN COMPRA C
   		ON C.PEDIDO_ESTOQUE_ID = P.ID
  ORDER BY P.ID

-- CÓDIGO DO PEDIDO QUE TEVE MAIS DE 10 ITENS

SELECT P.ID, SUM(I.QUANTIDADE) TOTAL_ITENS
   FROM PEDIDO_ESTOQUE P
   	INNER JOIN ITEM_PEDIDO I
   		ON P.ID = I.PEDIDO_ESTOQUE_ID
   	GROUP BY P.ID
    HAVING SUM(I.QUANTIDADE) > 10
  ORDER BY P.ID

 /* A CONSULTA DO TOTAL DA "CAPA" DO PEDIDO NÃO ENTENDI O QUE DE FATO ELA DEVERIA TRAZER */