package com.sena.creyese.dentvision_spring.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "imagenes_diseño")
public class ImagenesDiseno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImagenes;

    @Column(nullable = false, length = 255)
    private String imagen;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "idProcedimiento")
    private Procedimiento procedimiento;

    public Long getIdImagenes() {
        return idImagenes;
    }

    public void setIdImagenes(Long idImagenes) {
        this.idImagenes = idImagenes;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Procedimiento getProcedimiento() {
        return procedimiento;
    }

    public void setProcedimiento(Procedimiento procedimiento) {
        this.procedimiento = procedimiento;
    }
}
