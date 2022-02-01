package fr.leroideskiwis.minesweeper;

public class Location {

    private final int x;
    private final int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Location add(int x, int y){
        return new Location(this.x+x, this.y+y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (x != location.x) return false;
        return y == location.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
