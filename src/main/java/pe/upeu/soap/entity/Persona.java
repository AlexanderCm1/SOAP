package pe.upeu.soap.entity;

import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "persona")
public class Persona implements Serializable {
    private static final long seriaLVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "persona_id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellido;
    @Column(name = "dni")
    private String dni;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "email")
    private String email;

}
