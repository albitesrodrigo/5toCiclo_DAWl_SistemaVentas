package edu.cibrertec.sistemaVentas.model;

public class Rol {
    private Integer id;
    private String value;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rol(Integer id, String value) {
        this.id = id;
        this.value = value;
    }
}
