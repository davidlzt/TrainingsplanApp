package backend.models;

public class Exercises {

    private String name;
    private String difficulty;
    private String image;
    private String targetMuscle;
    private String description;

    public Exercises(String name, String difficulty, String image, String targetMuscle, String description) {
        this.name = name;
        this.difficulty = difficulty;
        this.image = image;
        this.targetMuscle = targetMuscle;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTargetMuscle() {
        return targetMuscle;
    }

    public void setTargetMuscle(String targetMuscle) {
        this.targetMuscle = targetMuscle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Exercises{" +
                "name='" + name + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", image='" + image + '\'' +
                ", targetMuscle='" + targetMuscle + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
