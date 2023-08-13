package serializable;

import java.io.Serializable;

public class People implements Serializable {
    private Long id;

    public People(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "People{" + id + "}";
    }
}

