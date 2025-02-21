package com.geraldSara.tarea7dwesGeraldSara.modelo;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "credenciales")
public class Credenciales implements Serializable, UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 50)
	private String usuario;

	@Column(name = "password", length = 255)
	private String password;
	
	 @Enumerated(EnumType.STRING)
	 private Rol rol; // Agregar un campo para el rol

	@OneToOne
	@JoinColumn(name = "idPersona", unique = true, nullable = true)
	private Persona persona;
	
	@OneToOne
	@JoinColumn(name = "idCliente", unique = true, nullable = true)
	private Cliente cliente;

	public Credenciales() {
	}

	public Credenciales(Long id, String usuario, String password) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.password = password;
	}

	public Credenciales(String usuario, String password, Rol rol, Cliente c) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
		this.cliente = c;
	}
	
	public Credenciales(String usuario, String password, Rol rol, Persona p) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
		this.persona = p;
	}

	public Credenciales(String usuario, String password, Persona persona) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.persona = persona;
	}
	
	public Credenciales(String usuario, String password, Cliente cliente) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credenciales other = (Credenciales) obj;
		return Objects.equals(usuario, other.usuario);
	}

	public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
	

}
