package com.tech.com.dto;

/**
 * Employee Data Transfer Object (DTO)
 * Represents employee information for the TechM Asset Management system
 */
public class Employee {
    
    private Long e_id;
    private String e_name;
    private String G_id;
    private String swissre_id;
    private String contact_num;
    private String address;
    private String techM_mail_id;
    private String swissre_mail_id;
    private String Team;
    private String Manager_name;
    
    /**
     * Default constructor
     */
    public Employee() {
    }
    
    /**
     * Constructor with all fields
     */
    public Employee(Long e_id, String e_name, String G_id, String swissre_id, 
                   String contact_num, String address, String techM_mail_id, 
                   String swissre_mail_id, String Team, String Manager_name) {
        this.e_id = e_id;
        this.e_name = e_name;
        this.G_id = G_id;
        this.swissre_id = swissre_id;
        this.contact_num = contact_num;
        this.address = address;
        this.techM_mail_id = techM_mail_id;
        this.swissre_mail_id = swissre_mail_id;
        this.Team = Team;
        this.Manager_name = Manager_name;
    }
    
    // Getters and Setters
    
    public Long getE_id() {
        return e_id;
    }
    
    public void setE_id(Long e_id) {
        this.e_id = e_id;
    }
    
    public String getE_name() {
        return e_name;
    }
    
    public void setE_name(String e_name) {
        this.e_name = e_name;
    }
    
    public String getG_id() {
        return G_id;
    }
    
    public void setG_id(String G_id) {
        this.G_id = G_id;
    }
    
    public String getSwissre_id() {
        return swissre_id;
    }
    
    public void setSwissre_id(String swissre_id) {
        this.swissre_id = swissre_id;
    }
    
    public String getContact_num() {
        return contact_num;
    }
    
    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTechM_mail_id() {
        return techM_mail_id;
    }
    
    public void setTechM_mail_id(String techM_mail_id) {
        this.techM_mail_id = techM_mail_id;
    }
    
    public String getSwissre_mail_id() {
        return swissre_mail_id;
    }
    
    public void setSwissre_mail_id(String swissre_mail_id) {
        this.swissre_mail_id = swissre_mail_id;
    }
    
    public String getTeam() {
        return Team;
    }
    
    public void setTeam(String Team) {
        this.Team = Team;
    }
    
    public String getManager_name() {
        return Manager_name;
    }
    
    public void setManager_name(String Manager_name) {
        this.Manager_name = Manager_name;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "e_id=" + e_id +
                ", e_name='" + e_name + '\'' +
                ", G_id='" + G_id + '\'' +
                ", swissre_id='" + swissre_id + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", address='" + address + '\'' +
                ", techM_mail_id='" + techM_mail_id + '\'' +
                ", swissre_mail_id='" + swissre_mail_id + '\'' +
                ", Team='" + Team + '\'' +
                ", Manager_name='" + Manager_name + '\'' +
                '}';
    }
}
