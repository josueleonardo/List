package list;

public interface List <G>{
    void add(G data);
    G get(int index);
    void delete(int index);
    int getSize();
}
