package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "Es obligatorio diligenciar tipo de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(nullable = false, length = 50)
    private String tipoDocumento;

    @NotBlank(message = "Es obligatorio diligenciar el numero de documento")
    @Size(max=50, message = "Maximo 50 caracteres")
    @Column(unique = true, nullable = false, length = 50)
    private String documento;

    @NotBlank(message = "Es obligatorio diligenciar los nombre")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombres;

    @NotBlank(message = "Es obligatorio diligenciar los apellidos")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String apellidos;

    @NotBlank(message = "Es obligatorio diligenciar el email")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Es obligatorio diligenciar la confirmacion del email")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String confirmar_email;

    @NotBlank(message = "Es obligatorio diligenciar la contraseña")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String password;

    @NotBlank(message = "Es obligatorio diligenciar la confirmacion de la contraseña")
    @Size(max=100, message = "Maximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String confirmar_password;

    @NotBlank(message = "")
    @Column(nullable = true)
    private boolean estado;

    @NotBlank(message = "")
    @Column(nullable = false)
    private LocalDate fecha_registro;

    @ManyToOne
    @JoinColumn(name ="idRol")
    private Roles roles;


    public Long getId() {
        return idUsuario;
    }

    public void setId(Long id) {
        this.idUsuario = idUsuario;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmar_email() {
        return confirmar_email;
    }

    public void setConfirmar_email(String confirmar_email) {
        this.confirmar_email = confirmar_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmar_password() {
        return confirmar_password;
    }

    public void setConfirmar_password(String confirmar_password) {
        this.confirmar_password = confirmar_password;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(LocalDate fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Roles getRol() {
        return roles;
    }

    public void setRol(Roles roles) {
        this.roles = roles;
    }
}
