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

        int recipeX = guiLeft + 175;
        int recipeY = guiTop + 5;

        int width = 20;
        int height = 20;

        int x = recipeX + width + 20;
        int y = recipeY;

        Button wButton = new Button(x, y, width, height, new StringTextComponent("W"), btn -> {
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.connection.send(
                        new CChatMessagePacket("/openwarzone")
                );
            }
        });

        event.addWidget(wButton);
    }
}
