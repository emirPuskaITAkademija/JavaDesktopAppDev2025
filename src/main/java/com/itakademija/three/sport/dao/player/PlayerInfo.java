package com.itakademija.three.sport.dao.player;

import java.awt.*;
import java.io.Serializable;
import java.util.StringJoiner;

//Entity(PlayerInfo) <-> tabela(player_info)
public class PlayerInfo implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String sport;
    private Integer years;
    private boolean vegetarian;
    private Color color;

    public PlayerInfo() {
    }

    public PlayerInfo(Integer id, String firstName, String lastName, String sport, Integer years, boolean vegetarian, Color color) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sport = sport;
        this.years = years;
        this.vegetarian = vegetarian;
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PlayerInfo.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("sport='" + sport + "'")
                .add("years=" + years)
                .add("vegetarian=" + vegetarian)
                .add("color=" + color)
                .toString();
    }
}
