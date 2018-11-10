package steiner.bisley.shellhint;

public class PlatformData {

    public String getName() {
        return name;
    }

    private String name;

    public int getImageID() {
        return imageID;
    }

    private int imageID;

    static public final PlatformData[] platformArray = {
        new PlatformData("Wireshark", R.drawable.card_wireshark),
        new PlatformData("Maltego", R.drawable.card_maltego),
        new PlatformData("Armitage", R.drawable.card_armitage)
    };

    private PlatformData(String n, int id) {
        name = n;
        imageID = id;
    }
}
