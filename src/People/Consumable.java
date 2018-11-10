package People;

public interface Consumable{
    void getItem(String stuff);
    int getHp(Person person);
    void use(String item);
}