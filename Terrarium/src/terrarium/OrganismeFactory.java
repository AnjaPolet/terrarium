package terrarium;

public class OrganismeFactory {  
    
    private static final OrganismeFactory UNIQUE_INSTANCE = new OrganismeFactory();
    
    private OrganismeFactory(){
        
    }
    
    public static OrganismeFactory getInstance(){
        return UNIQUE_INSTANCE;
    }

   

    public Organisme createOrganisme(Type type,int rij, int kolom) {
        Organisme organisme= null;
        switch(type){
                case PLANT:   
                    organisme = new Plant(rij,kolom);
                    break;
                case  CARNIVOOR:
                    organisme = new Carnivoor(rij,kolom);
                    break;
                case HERBIVOOR:
                    organisme = new Herbivoor(rij,kolom);
                    break;
                case MENS:
                    organisme = new Mens(rij,kolom);
                    break;
        }
        return organisme;

    }

}
