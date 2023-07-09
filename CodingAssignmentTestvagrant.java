import java.util.*;

class RecentlyPlayedStore {
    private int capacity;
    private Map<String, LinkedList<String>> store;

    public RecentlyPlayedStore(int initialCapacity) {
        capacity = initialCapacity;
        store = new HashMap<>();
    }

    public void addSong(String user, String song) {
        LinkedList<String> songs = store.get(user);

        if (songs == null) {
            songs = new LinkedList<>();
            store.put(user, songs);
        }

        songs.remove(song);
        songs.addLast(song);

        if (songs.size() > capacity) {
            songs.removeFirst();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        List<String> songs = store.get(user);
        if (songs != null) {
            return new ArrayList<>(songs);
        } else {
            return Collections.emptyList();
        }
    }

    public void printStore() {
        for (Map.Entry<String, LinkedList<String>> entry : store.entrySet()) {
            String user = entry.getKey();
            LinkedList<String> songs = entry.getValue();
            System.out.println("User: " + user + ", Songs: " + songs);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);
        
        store.addSong("user1", "S1");
        store.addSong("user1", "S2");
        store.addSong("user1", "S3");
        store.addSong("user1", "S4");

        store.addSong("user2", "S2");
        store.addSong("user2", "S3");
        store.addSong("user2", "S4");

        store.addSong("user3", "S1");
        store.addSong("user3", "S2");
        store.addSong("user3", "S3");

        store.printStore();

        List<String> songs = store.getRecentlyPlayedSongs("user1");
        System.out.println("User1 recently played songs: " + songs);
    }
}