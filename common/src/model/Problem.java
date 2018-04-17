package model;

public class Problem extends BaseEntity<Long>{

    private Long id;
    private String description;

    public String getDescription() {
        return description;
    }

    public Problem(Long i, String desc){
        this.id=i;
        this.description=desc;
    }
    public Problem(){

    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id+" "+this.description+"\n";
    }

    public String toFile(){return this.id+"-"+this.description;}
}
