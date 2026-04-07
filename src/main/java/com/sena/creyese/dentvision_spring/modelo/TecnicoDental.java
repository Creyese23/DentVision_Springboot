package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "tecnico_dental")
public class TecnicoDental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTecnico;

    @NotBlank(message = "Es obligatorio diligenciar tipo de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipoDocumento;

    @NotBlank(message = "Es obligatorio diligenciar el numero de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String documento;

    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String telefono;

    @NotBlank(message = "Es obligatorio diligenciar telefono")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String genero;

    @NotBlank(message = "Es obligatorio diligenciar la especialidad")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String especialidad;

    @NotBlank(message = "")
    @Column(nullable = false)
    private Date fecha_nacimiento;

    @OneToOne
    @JoinColumn(name ="idUsuario")
    private Usuario usuario;

    public Long getIdTecnico() {
        return idTecnico;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setIdTecnico(Long idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
