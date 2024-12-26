import java.util.*;

class NetworkInterface {
    String name;
    String type;

    public NetworkInterface(String type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        NetworkInterface that = (NetworkInterface) obj;
        return Objects.equals(name, that.name) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

@Override
public String toString() {
    return type + " " + name;
}
}

class BondInterface extends NetworkInterface {
    Set<String> childInterfaces;

    public BondInterface(String name, Set<String> childInterfaces) {
        super("Bond", name);
        this.childInterfaces = childInterfaces;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        BondInterface that = (BondInterface) obj;
        return Objects.equals(childInterfaces, that.childInterfaces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), childInterfaces);
    }

    @Override
    public String toString() {
        return type + " " + name + " " + childInterfaces.size() + " " + String.join(" ", childInterfaces);
    }
}

class VLANInterface extends NetworkInterface {
    String parentInterface;

    public VLANInterface(String name, String parentInterface) {
        super("VLAN", name);
        this.parentInterface = parentInterface;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        VLANInterface that = (VLANInterface) obj;
        return Objects.equals(parentInterface, that.parentInterface);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentInterface);
    }

    @Override
    public String toString() {
        return type + " " + name + " " + parentInterface;
    }
}

public class NetworkConfigChanger {

    public static int getMinChanges(List<NetworkInterface> currentConfig, List<NetworkInterface> targetConfig) {
        Set<NetworkInterface> currentSet = new HashSet<>(currentConfig);
        Set<NetworkInterface> targetSet = new HashSet<>(targetConfig);

        Set<NetworkInterface> toAdd = new HashSet<>(targetSet);
        toAdd.removeAll(currentSet);

        Set<NetworkInterface> toRemove = new HashSet<>(currentSet);
        toRemove.removeAll(targetSet);

        return toAdd.size() + toRemove.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read current configuration
        int N = Integer.parseInt(scanner.nextLine().trim());
        List<NetworkInterface> currentConfig = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String type = parts[0];
            String name = parts[1];

            if (type.equals("Physical")) {
                currentConfig.add(new NetworkInterface(type, name));
            } else if (type.equals("Bond")) {
                Set<String> children = new HashSet<>();
                for (int j = 2; j < parts.length; j++) {
                    children.add(parts[j]);
                }
                currentConfig.add(new BondInterface(name, children));
            } else if (type.equals("VLAN")) {
                String parent = parts[2];
                currentConfig.add(new VLANInterface(name, parent));
            }
        }

        // Read target configuration
        int M = Integer.parseInt(scanner.nextLine().trim());
        List<NetworkInterface> targetConfig = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String type = parts[0];
            String name = parts[1];

            if (type.equals("Physical")) {
                targetConfig.add(new NetworkInterface(type, name));
            } else if (type.equals("Bond")) {
                Set<String> children = new HashSet<>();
                for (int j = 2; j < parts.length; j++) {
                    children.add(parts[j]);
                }
                targetConfig.add(new BondInterface(name, children));
            } else if (type.equals("VLAN")) {
                String parent = parts[2];
                targetConfig.add(new VLANInterface(name, parent));
            }
        }

        // Calculate minimum changes
        int minChanges = getMinChanges(currentConfig, targetConfig);
        System.out.println(minChanges);
    }
}
