package backend.academia.fortaleza.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Usuario_TR")
@Data
@NoArgsConstructor
public class UsuarioTR {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTR;
    @OneToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;
    @Column(columnDefinition = "TEXT")
    private String jwt;
    private String mobileToken;

    public UsuarioTR(Usuario usuario, String jwt, String mobileToken) {
        this.usuario = usuario;
        this.jwt = jwt;
        this.mobileToken = mobileToken;
    }

    public Integer getIdTR() {
        return idTR;
    }

    public void setIdTR(Integer idTR) {
        this.idTR = idTR;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMobileToken() {
        return mobileToken;
    }

    public void setMobileToken(String mobileToken) {
        this.mobileToken = mobileToken;
    }
}
