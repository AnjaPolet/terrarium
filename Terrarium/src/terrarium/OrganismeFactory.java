package terrarium;

public class OrganismeFactory {

   

    public Organisme createOrganisme(Type type,int rij, int kolom) {
        Organisme organisme= null;
        if (type.equals(Type.PLANT))   // TODO switch
            organisme = new Plant(rij,kolom);
        else if (type.equals(Type.CARNIVOOR))
            organisme = new Carnivoor(rij,kolom);
        else if (type.equals(Type.HERBIVOOR))
            organisme = new Herbivoor(rij,kolom);
        return organisme;

    }

}
