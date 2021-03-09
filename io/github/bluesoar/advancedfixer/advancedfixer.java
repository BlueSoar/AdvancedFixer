package io.github.bluesoar.advancedfixer;

import catserver.api.bukkit.event.ForgeEvent;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BeatWildPixelmonEvent;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.mc9y.pokemonapi.PokemonAPI;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.api.events.BattleStartedEvent;
import com.pixelmonmod.pixelmon.storage.PlayerPartyStorage;
import org.bukkit.entity.Player;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;



public class advancedfixer extends JavaPlugin implements Listener {
    public void onEnable() {
	    getLogger().info("§b================================");
	    getLogger().info("§e正在启用AdvancedFixer...");
	    getLogger().info("开始校验...");
	    getLogger().info("§e开始读取配置");
	    loadConfig();
	    getLogger().info("§e成功注册指令  √");
        if (PokemonAPI.getInstance().hasClass("moe.plushie.armourers_workshop.ArmourersWorkshop"))
	    getLogger().info("§e成功注册监听器  √");
	    getLogger().info("§e感谢购买AdvancedFixer");
	    getLogger().info("§b================================");
    }
	@EventHandler
	 public void onPlayerInteractEvent(PlayerInteractEvent playerInteractEvent) {
		if(playerInteractEvent.getClickedBlock() == null) {
			return;
	}
	 String block = String.valueOf(playerInteractEvent.getClickedBlock().getType());
	 if(block.equalsIgnoreCase("ARMOURERSWORKSHOP_BLOCKMANNEQUIN")) {
		 if(!(playerInteractEvent.getPlayer().isOp())) {
			 playerInteractEvent.setCancelled(true);
		 }
	 }
	  public void onBeatPokemon(ForgeEvent e) {
		    if (!(e.getForgeEvent() instanceof com.pixelmonmod.pixelmon.api.events.CaptureEvent.SuccessfulCapture))
		      return; 
		    BeatWildPixelmonEvent event = (BeatWildPixelmonEvent)e.getForgeEvent();
		    Pokemon pokemon = ((PixelmonWrapper)event.wpp.controlledPokemon.get(0)).pokemon;
		    Player player = Bukkit.getPlayer(event.player.func_110124_au());
		    if (pokemon.getHeldItem() != null) {
		      pokemon.setHeldItem(null);
		      player.sendMessage(getConfig().getString("bugmessage").replace("&", "));
		    } 
		  }
	}
    @Override
    public void onDisable() {
	    getLogger().info("§b================================");
	    getLogger().info("§e正在卸载AdvancedFixer...");
	    getLogger().info("§e卸载完成  √");
	    getLogger().info("§e感谢购买AdvancedFixer");
	    getLogger().info("§b================================");
    }
    public void loadConfig() {
        File file = new File(getDataFolder(), "config.yml");
        if (!getDataFolder().exists())
          getDataFolder().mkdir(); 
        if (!file.exists())
          saveDefaultConfig(); 
        reloadConfig();
    }
}