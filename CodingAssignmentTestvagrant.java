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

        List<String> songsUser1 = store.getRecentlyPlayedSongs("user1");
        List<String> songsUser2 = store.getRecentlyPlayedSongs("user2");
        List<String> songsUser3 = store.getRecentlyPlayedSongs("user3");
        System.out.println("User1 recently played songs: " + songsUser1);
        System.out.println("User1 recently played songs: " + songsUser2);
        System.out.println("User1 recently played songs: " + songsUser3);
        

        assert songsUser1.equals(Arrays.asList("S2", "S3", "S4"));
        assert songsUser2.equals(Arrays.asList("S3", "S2", "S4"));
        assert songsUser2.equals(Arrays.asList("S4", "S3", "S2"));
        assert songsUser3.equals(Arrays.asList("S2", "S1", "S3"));
    }
}