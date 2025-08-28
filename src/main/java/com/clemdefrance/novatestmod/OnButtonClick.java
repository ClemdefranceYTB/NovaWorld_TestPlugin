package com.clemdefrance.novatestmod;


import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.CChatMessagePacket;

public class OnButtonClick {

    public static void click() {
        Minecraft.getInstance().player.connection.send(
                new CChatMessagePacket("/openwarzone")
        );
    }
}
