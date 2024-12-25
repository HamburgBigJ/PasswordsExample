package cho.info.passwordsExample;

import cho.info.passwords.Passwords;
import cho.info.passwords.api.PasswordsApi;
import cho.info.passwords.utls.ConfigManager;
import cho.info.passwords.utls.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public final class PasswordsExample extends JavaPlugin implements Listener {

    public Messages messages;



    @Override
    public void onEnable() {
        // Plugin startup logic
        Passwords passwords = new Passwords();
        PasswordsApi passwordsApi = passwords.getPasswordsApi();

        // Set Player password
        passwordsApi.setPassword().setPlayerPassword(Bukkit.getPlayer("ExamplePlayer"), 1203);

        // Register Events
        passwordsApi.behavior().registerEvents(this);

        // Set Server Password
        passwordsApi.setPassword().setServerPassword(1203, "ExampleReason");

        // Set Admin Password
        passwordsApi.setPassword().setAdminPassword(1203);

        // Set Admin Op Login
        passwordsApi.config().setAdminOpLogin(false);

        // Get Player Password
        String password = passwordsApi.setPassword().getPlayerPassword(Bukkit.getPlayer("ExamplePlayer"));

        // Get Server Password
        String serverPassword = passwordsApi.setPassword().getServerPassword();

        // Get Admin Password
        String adminPassword = passwordsApi.setPassword().getAdminPassword();

        // Reset Config
        passwordsApi.behavior().resetConfig("Reset Config");

        // Messages system
        messages.sendMessage(Bukkit.getPlayer("ExamplePlayer"), "Example Message");
        messages.sendTitel(Bukkit.getPlayer("ExamplePlayer"), "Example Title", "Example Subtitle");
        messages.sendActonBar(Bukkit.getPlayer("ExamplePlayer"), "Example Actionbar");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Passwords passwords = new Passwords();
        PasswordsApi passwordsApi = passwords.getPasswordsApi();

        // Login Screen
        if (passwordsApi.behavior().isLoginScreen(event.getPlayer())) {
            passwordsApi.getPlugin().getLogger().info("Player " + event.getPlayer().getName() + " is on the login screen");
        }

        // Set Password Length
        passwordsApi.config().setPasswordLength(20);

        // Set Login Gamemode
        passwordsApi.config().setLoginGamemode("survival");

        // Password GUI ( these are Dispenser GUIs )
        Inventory passwordGui = passwordsApi.behavior().getInventory();

        // First Join GUI ( The first time a player joins the server )
        Inventory passwordFirstJoinGui = passwordsApi.behavior().getFirstLoginInventory();


    }

}
