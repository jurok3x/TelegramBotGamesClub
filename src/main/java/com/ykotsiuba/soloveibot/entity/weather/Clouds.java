package com.ykotsiuba.soloveibot.entity.weather;

public class Clouds {
    
    @Override
    public String toString() {
        return "Clouds [all=" + all + "]";
    }

    private Integer all;

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    public Clouds() {
        
    }

}
