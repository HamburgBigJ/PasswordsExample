package cho.info.passwordsExample;

import cho.info.passwords.api.password.customgui.CustomGui;
import cho.info.passwords.api.password.customgui.PasswordsGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ExampleGui extends PasswordsGui {

    public CustomGui customGui;

    public ExampleGui(CustomGui customGui) {
        this.customGui = customGui;
    }

    @Override
    public void openGui(PlayerJoinEvent playerJoinEvent) {
        customGui.registerChar(playerJoinEvent.getPlayer());
        // Open Gui Logic
    }

    @Override
    public void interactGui(InventoryClickEvent inventoryClickEvent) {
        Player player = (Player) inventoryClickEvent.getWhoClicked();
        if (inventoryClickEvent.getSlot() == 0) {
            ItemStack itemStack = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
            inventoryClickEvent.getInventory().setItem(0, itemStack);
            // Need to log in the player
            customGui.setLogin(true, player);
            // Player close inventory.
            player.closeInventory();
        }

        // Interact Logic
    }

    @Override
    public void closeGui(InventoryCloseEvent inventoryCloseEvent) {
        // Close Logic
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.CRAFTER, "ExampleGui");
        ItemStack itemStack = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        inventory.setItem(0, itemStack);
        // Inventory Logic
        return inventory;
    }
}
