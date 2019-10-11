



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Hobby;

/**
 *
 * @author Ulrik
 */
public class HobbyDTO {
    
    private Long id;
    private String name;
    private String description;

    public HobbyDTO() {
    }

    public HobbyDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    HobbyDTO(Hobby hobby) {
        this.name = hobby.getName();
        this.description = hobby.getDescription();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    
    
}
