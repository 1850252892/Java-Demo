package entity;

import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

public class User implements Serializable{
    private static final long serialVersionUID = -6011241820070393952L;
    private Integer id;

    private String pwd;

    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}