package steiner.bisley.shellhint;

public class HTool {
    private String name;
    private String description;
    private int imageID;

    private HTool(String n, String d, int i) {
        name = n;
        description = d;
        imageID = i;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageID() {
        return imageID;
    }

    public String toString() {
        return this.name;
    }

    public static final HTool htools[] = {
            new HTool("FPING", "> fping -g 192.168.1.0/24\n" +
                    "> fping -q -a -s -r 1 -g 192.168.0.1-192.168.0.100\n" +
                    "means quiet, show alive, gen stat, retries, ip range", R.drawable.fping),
            new HTool("ARP-SCAN", "> arp-scan --interface=wlan0 --localnet", R.drawable.arpscan),
            new HTool("NMAP", "> nmap -sS -Pn 192.168.1.1\n" +
                    "means Stealth Syn Scan, ignore Ping drop", R.drawable.nmap),
            new HTool("AIRMON-NG", "> airmon-ng check kill\n" +
                    "> airmon-ng start wlan0 [lock-channel]\n" +
                    "> airmon-ng stop wlan0", R.drawable.airmonng)
    };

}