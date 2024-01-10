/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author asus
 */
public class AboardPrograms implements Serializable{
    String id;
    String name;
    String time;
    String fromRegistrationDate;
    String endRegistrationDate;
    int days;
    List<String> location;
    int cost;
    String content;

    public AboardPrograms(String id, String name, String time, String fromRegistrationDate, String endRegistrationDate, int days, List<String> location, int cost, String content) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.fromRegistrationDate = fromRegistrationDate;
        this.endRegistrationDate = endRegistrationDate;
        this.days = days;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFromRegistrationDate() {
        return fromRegistrationDate;
    }

    public void setFromRegistrationDate(String fromRegistrationDate) {
        this.fromRegistrationDate = fromRegistrationDate;
    }

    public String getEndRegistrationDate() {
        return endRegistrationDate;
    }

    public void setEndRegistrationDate(String endRegistrationDate) {
        this.endRegistrationDate = endRegistrationDate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

   
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AboardPrograms{" + "id=" + id + ", name=" + name + ", time=" + time + ", fromRegistrationDate=" + fromRegistrationDate + ", endRegistrationDate=" + endRegistrationDate + ", days=" + days + ", location=" + location + ", cost=" + cost + ", content=" + content + '}';
    }
    
}
