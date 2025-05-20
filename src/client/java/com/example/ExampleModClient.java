package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class ExampleModClient implements ClientModInitializer {

    private static final Map<Item, TooltipData> SMITHING_TEMPLATE_TOOLTIPS = new HashMap<>();
    
    // Class to hold tooltip data for each smithing template
    private static class TooltipData {
        public final String title;
        public final String location;
        public final String duplication;
        public final String usage;
        public final String lore;
        
        public TooltipData(String title, String location, String duplication, String usage, String lore) {
            this.title = title;
            this.location = location;
            this.duplication = duplication;
            this.usage = usage;
            this.lore = lore;
        }
    }
    
    @Override
    public void onInitializeClient() {
        ItemTooltipCallback.EVENT.register((stack, context, tooltipType, lines) -> {
            Item item = stack.getItem();
            
            // Only process smithing templates
            if (SMITHING_TEMPLATE_TOOLTIPS.containsKey(item)) {
                // Clear the default tooltip lines
                lines.clear();
                
                // Get tooltip data for this template
                TooltipData data = SMITHING_TEMPLATE_TOOLTIPS.get(item);
                
                // Add title (gold color)
                lines.add(Text.literal(data.title).setStyle(Style.EMPTY.withColor(Formatting.GOLD)));
                
                // Add Found section (yellow)
                lines.add(Text.literal("Found: ").setStyle(Style.EMPTY.withColor(Formatting.YELLOW))
                        .append(Text.literal(data.location).setStyle(Style.EMPTY.withColor(Formatting.WHITE))));
                
                // Add Duplicate section (aqua) 
                lines.add(Text.literal("Duplicate: ").setStyle(Style.EMPTY.withColor(Formatting.AQUA))
                        .append(Text.literal(data.duplication).setStyle(Style.EMPTY.withColor(Formatting.WHITE))));
                
                // Add Use section (green)
                lines.add(Text.literal("Use: ").setStyle(Style.EMPTY.withColor(Formatting.GREEN))
                        .append(Text.literal(data.usage).setStyle(Style.EMPTY.withColor(Formatting.WHITE))));
                
                // Add Lore section (light purple with italics)
                lines.add(Text.literal("Lore: ").setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE))
                        .append(Text.literal("\"" + data.lore + "\"")
                                .setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE).withItalic(true))));
                
                // Add Vanilla Minecraft information
                lines.add(Text.literal("Items").setStyle(Style.EMPTY.withColor(Formatting.BLUE)));
                lines.add(Text.literal("Smithing Template"));
                lines.add(Text.literal("Applies to:"));
                lines.add(Text.literal("  Armor").setStyle(Style.EMPTY.withColor(Formatting.BLUE)));
                lines.add(Text.literal("Ingredients:"));
                lines.add(Text.literal("  Ingots & Crystals").setStyle(Style.EMPTY.withColor(Formatting.BLUE)));
            }
        });
        
        // Initialize all smithing template tooltips
        initializeTooltipData();
        
        ExampleMod.LOGGER.info("Smithing Template Tooltips client initialized!");
    }
    
    private void initializeTooltipData() {
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, new TooltipData(
            "Netherite Upgrade", 
            "Bastion remnants treasure chests", 
            "7 Diamonds + Netherrack", 
            "Use with Diamond equipment and Netherite Ingot to upgrade to Netherite", 
            "Forged in flames eternal, it carries the heat of the Nether itself."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.COAST_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Coast Armor Trim", 
            "Shipwreck treasure chests", 
            "7 Diamonds + Cobblestone", 
            "Apply with Prismarine Shard for a barnacle-like nautical accent (you can use any)", 
            "Grains of sand and barnacles seem embedded in the pattern, as if drifted ashore from the deep sea."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Dune Armor Trim", 
            "Desert pyramids", 
            "7 Diamonds + Sandstone", 
            "Apply with Gold Ingot for sandy wave-like patterns (you can use any)", 
            "Ancient windblown carvings that seem to shift like the desert itself."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.EYE_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Eye Armor Trim", 
            "Stronghold libraries", 
            "7 Diamonds + End Stone", 
            "Apply with any material for mysterious watching eye patterns", 
            "The unblinking watchers of the void that never sleep, always vigilant."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.HOST_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Host Armor Trim", 
            "Trail ruins", 
            "7 Diamonds + Terracotta", 
            "Apply with any material for noble decorative accents", 
            "Echoes of lost feasts and hospitality from a forgotten civilization."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.RAISER_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Raiser Armor Trim", 
            "Trail ruins", 
            "7 Diamonds + Terracotta", 
            "Apply with any material for proud, upward lifting patterns", 
            "Worn by those who built high and reached for the heavens."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.RIB_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Rib Armor Trim", 
            "Nether fortress chests", 
            "7 Diamonds + Netherrack", 
            "Apply with any material for bone-like ribcage patterns", 
            "Forged from the ribs of the blaze-born, still warm to the touch."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Sentry Armor Trim", 
            "Pillager outposts", 
            "7 Diamonds + Cobblestone", 
            "Apply with any material for vigilant, military-style markings", 
            "Eyes always watching, standing guard against the coming storm."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Shaper Armor Trim", 
            "Trail ruins", 
            "7 Diamonds + Terracotta", 
            "Apply with any material for intricate crafted armor detail", 
            "The hands that molded this legacy left their mark for eternity."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Silence Armor Trim", 
            "Ancient city loot chests", 
            "7 Diamonds + Deepslate", 
            "Apply with any material for hushed, mysterious markings", 
            "Whispers of deep time that quiet your footsteps in the darkness."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Snout Armor Trim", 
            "Bastion remnants", 
            "7 Diamonds + Blackstone", 
            "Apply with any material for fierce tusk-like accents", 
            "Styled by brute hands, marked with the tusks of conquering warriors."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Spire Armor Trim", 
            "End City treasure chests", 
            "7 Diamonds + Purpur", 
            "Apply with any material for elegant, arcane spire patterns", 
            "Stylized to reach the stars, it whispers secrets of the void."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Tide Armor Trim", 
            "Ocean monument loot", 
            "7 Diamonds + Prismarine", 
            "Apply with any material for flowing wave-like patterns", 
            "It harnesses the current's grace and flows like water with every move."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.VEX_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Vex Armor Trim", 
            "Woodland mansion treasure rooms", 
            "7 Diamonds + Cobblestone", 
            "Apply with any material for ethereal, ghostly patterns", 
            "Spiteful spirits endure within this pattern, their vengeance never sated."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.WARD_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Ward Armor Trim", 
            "Ancient city hidden rooms", 
            "7 Diamonds + Deepslate", 
            "Apply with any material for protective warding sigils", 
            "Runes of the Deep Dark that ward against unknown terrors."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Wayfinder Armor Trim", 
            "Trail ruins", 
            "7 Diamonds + Terracotta", 
            "Apply with any material for explorer-themed compass patterns", 
            "Guided by ancient paths, it always leads you to adventure."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.WILD_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Wild Armor Trim", 
            "Jungle temples", 
            "7 Diamonds + Mossy Cobblestone", 
            "Apply with any material for untamed, naturalistic patterns", 
            "Tamed by no one, it grows wild like the jungle itself."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.BOLT_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Bolt Armor Trim", 
            "Trial Chamber vaults (requires a Vault key)", 
            "7 Diamonds + 1 Block of Copper", 
            "Apply with Copper Ingot for a circuitry trim (you can use any)", 
            "Lightning crackles along the edges of this trim, evoking the ancient power hidden in the Vault."
        ));
        
        SMITHING_TEMPLATE_TOOLTIPS.put(Items.FLOW_ARMOR_TRIM_SMITHING_TEMPLATE, new TooltipData(
            "Flow Armor Trim", 
            "Trail ruins", 
            "7 Diamonds + Terracotta", 
            "Apply with any material for elegant flowing water patterns", 
            "Movement preserved in time, like rivers flowing through ancient stone."
        ));
    }
}