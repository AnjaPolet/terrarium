package terrarium;

public class Mens extends Organisme {

    private final Geslacht geslacht;

    public Mens(int rij, int kolom) {
        super(rij, kolom);
        int g = randomGetal.nextInt(2);
        if (g == 0) {
            geslacht = Geslacht.MAN;
        } else {
            geslacht = Geslacht.VROUW;
        }
    }

    public Geslacht getGeslacht() {
        return geslacht;
    }

    @Override
    public void actie(Organisme organisme) {
        if (organisme instanceof Herbivoor || organisme instanceof Plant) {
            this.levenskracht += organisme.getLevenskracht();
            Matrix.getInstance().verwijderDodeOrganisme(organisme.getCoordinaat());
        } else if (organisme instanceof Carnivoor) {
             
                if (this.levenskracht > organisme.getLevenskracht()) {
                    this.levenskracht += organisme.getLevenskracht();
                    Matrix.getInstance().verwijderDodeOrganisme(organisme.getCoordinaat());
                } else if (this.levenskracht < organisme.getLevenskracht()) {
                    organisme.setLevenskracht(organisme.getLevenskracht() + this.levenskracht);
                    Matrix.getInstance().verwijderDodeOrganisme(this.getCoordinaat());
                }
            

        } else if (organisme instanceof Mens) {
            Mens andereMens = (Mens) organisme;
            if (geslacht != andereMens.getGeslacht()) {
                if (Matrix.getInstance().getVrijePlaatsen() > 0) {
                    Matrix.getInstance().addOrganisme(Type.MENS);
                }
            }
        }
        actionPerformed = true;

    }

    @Override
    public String toString() {
        if (geslacht == Geslacht.MAN) {
            return "M";
        } else {
            return "V";
        }
    }

}
