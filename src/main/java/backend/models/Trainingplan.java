package backend.models;

import java.util.List;

public class Trainingplan {
    private List<Exercise> trainingsplan;
    private String name;
    private String beschreibung;
    private int dauerInWochen;
    private String ziel;

    public Trainingplan(String name, String beschreibung, int dauerInWochen, String ziel, List<Exercise> trainingsplan) {
        this.name = name;
        this.beschreibung = beschreibung;
        this.dauerInWochen = dauerInWochen;
        this.ziel = ziel;
        this.trainingsplan = trainingsplan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public int getDauerInWochen() {
        return dauerInWochen;
    }

    public void setDauerInWochen(int dauerInWochen) {
        this.dauerInWochen = dauerInWochen;
    }

    public String getZiel() {
        return ziel;
    }

    public void setZiel(String ziel) {
        this.ziel = ziel;
    }

    public List<Exercise> getTrainingsplan() {
        return trainingsplan;
    }

    public void setTrainingsplan(List<Exercise> trainingsplan) {
        this.trainingsplan = trainingsplan;
    }

    public void printTrainingsplan() {
        System.out.println("Trainingsplan: " + name);
        System.out.println("Ziel: " + ziel);
        System.out.println("Beschreibung: " + beschreibung);
        System.out.println("Dauer: " + dauerInWochen + " Wochen");
        System.out.println("Übungen:");
        for (Exercise exercise : trainingsplan) {
            System.out.println(exercise.getName());
        }
    }
    public void searchExercise(String name) {
        for (Exercise exercise : trainingsplan) {
            if (exercise.getName().equalsIgnoreCase(name)) {
                System.out.println("Gefundene Übung: " + exercise);
                return;
            }
        }
        System.out.println("Übung mit dem Namen '" + name + "' wurde nicht gefunden.");
    }
}
