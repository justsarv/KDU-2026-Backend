import java.util.*;
import java.util.stream;


public class Inventory {
    private String name;
    private List<List<String>> palletItemIds; // Nested structure: List of Pallets, where each Pallet is a List of Item IDs

    public Inventory(String name, List<List<String>> palletItemIds) {
        this.name = name;
        this.palletItemIds = palletItemIds;
    }
    
    public List<List<String>> getPalletItemIds() { return palletItemIds; }
    
    // Simulates an expensive database lookup
    public static Inventory findItem(String id) {
        if (id.equals("A100")) {
            return new Inventory("Main Inventory", List.of(
                List.of("P10", "P20"),
                List.of("P30", "P10", "P40")
            ));
        }
        return null; // Search fails
    }

    public static void main(String[] args) {
        String inventory = Optional.ofNullable(Inventory.findItem("lol")).map(inv-> inv.toString()).orElse(new ItemPlaceholder().getInfo());
        System.out.println(inventory);

        Inventory inventory1=Inventory.findItem(inventory);
        Set<String>InventoryItems= inventory1.stream()
                                              .flatMap(List::Stream)
                                              .collect(Collectors.toSet());
        System.out.println(InventoryItems);

    }
}

