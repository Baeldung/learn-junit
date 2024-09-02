package com.baeldung.lju.domain.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Campaign {

    private Long id;

    private String code;

    private String name;

    private String description;

    private Set<Task> tasks = new HashSet<>();

    private boolean closed;

    public Campaign(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public Campaign() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Campaign other = (Campaign) obj;
        return Objects.equals(code, other.code);
    }

    @Override
    public String toString() {
        return "Campaign [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", closed=" + closed + "]";
    }

}