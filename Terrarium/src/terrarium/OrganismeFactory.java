package terrarium;

public class OrganismeFactory {

   

    public Organisme createOrganisme(Type type) {
        Organisme organisme= null;
        if (type.equals(Type.PLANT))
            organisme = new Plant();
        else if (type.equals(Type.CARNIVOOR))
            organisme = new Carnivoor();
        else if (type.equals(Type.HERBIVOOR))
            organisme = new Herbivoor();
        return organisme;

    }

}
