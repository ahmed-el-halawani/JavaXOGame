/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author A H M E D
 */
public class BaseEntity {
    protected String id;

    public BaseEntity(String id) {
        this.id = id;
    }
    
    
    public BaseEntity() {
    id = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
