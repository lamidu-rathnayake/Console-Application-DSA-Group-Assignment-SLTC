package Module1;

// node class for location tree
class Location {
    String name;
    int height;
    Location left, right;

    Location(String name) {
        this.name = name;
        this.height = 0;
    }
}