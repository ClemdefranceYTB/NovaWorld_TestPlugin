package com.clemdefrance.novatestmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.network.play.client.CChatMessagePacket;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class InventoryButton {

    @SubscribeEvent
    public static void onGuiInit(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!(event.getGui() instanceof InventoryScreen)) return;

        InventoryScreen screen = (InventoryScreen) event.getGui();

        int guiLeft = screen.getGuiLeft();
        int guiTop = screen.getGuiTop();

        int shieldX = guiLeft - 20;
        int shieldY = guiTop + 62;

        int wButtonX = shieldX + 150;
        int wButtonY = shieldY - 1;

        Button wButton = new Button(wButtonX, wButtonY, 20, 20, new StringTextComponent("W"), btn -> {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.connection.send(
                        new CChatMessagePacket("/openwarzone")
                );
            }
        });

        event.addWidget(wButton);
    }
}
