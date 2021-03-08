package gameobjects;

public class CustomCollection<T> {

    private GameObject[] collection;
    private int pointer = 0;

    public CustomCollection() {
        this.collection = new GameObject[31];
    }

    /**
     * Метод, с който добавяме нови елементи към колекцията.
     */
    public void add(GameObject object) {
        this.collection[this.pointer++] = object;
    }

    public T get(int index) {
        return (T)this.collection[index];
    }
}