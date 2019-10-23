package br.com.mateus.estoque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Model padrão de persistência, atributos comuns aos demais modelos.
 *
 * @author Iago Bruno
 * @since 1.0
 */
@MappedSuperclass
@Data
public abstract class BasicModel implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  private Long version;

  @Column(name = "created_date", nullable = false, updatable = false)
  @CreatedDate
  private long createdDate;

  @LastModifiedDate
  private long modifiedDate;


}
