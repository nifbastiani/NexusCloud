package br.com.nexuscloud.model;

public class Container {
    private String id;
    private String nomeApp;
    private double ramNecessaria;
    
    public Container(String id, double ramNecessaria) {
        this.id = id;
        this.ramNecessaria = ramNecessaria;
    }

    public String getId() {
        return id;
    }

    public double getRamNecessaria() {
        return ramNecessaria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Container other = (Container) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
