package servlets;

public class GestionPendu {
    private String aDeviner = null;
    private StringBuilder devine = null;
    private int nbEssaisRestants;

    public GestionPendu() {
    }

    public void setaDeviner(String aDeviner) {
        this.aDeviner = aDeviner;
        this.devine = new StringBuilder("_".repeat(aDeviner.length()));
        this.nbEssaisRestants = 10;
    }

    public StringBuilder getDevine() {
        return this.devine;
    }

    public boolean test(char carac) {
        boolean res = false;
        int last = 0;
        while ((last = aDeviner.indexOf(carac, last)) != -1) {
            res = true;
            devine.setCharAt(last, carac);
            last++;
        }
        if (!res) {
            nbEssaisRestants--;
        }
        return res;
    }

    public int getNbEssaisRestants() {
        return this.nbEssaisRestants;
    }
}
