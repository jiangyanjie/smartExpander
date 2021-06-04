package FindCasualNames.relation;

public class Identifier extends IdentifierWithoutType {


    public Identifier(int line, String content){
        super(line, content);

    }

    public String getName() {return getContent();}

    @Override
    public String toString(){
        return getName();
    }

}
