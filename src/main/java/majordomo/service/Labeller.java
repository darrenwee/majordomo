package majordomo.service;

public class Labeller  {

    private String label;

    public Labeller(String[] s) {
        label = s[0].split(" ")[0];
    }



    public String getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        Labeller object = (Labeller) o;
        return object.getLabel() != null &&
                label.equals(object.getLabel());
    }
}
